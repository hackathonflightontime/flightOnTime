from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from joblib import load
import pandas as pd
import json
from datetime import datetime
from pathlib import Path
from typing import Optional

app = FastAPI(title="FlightOnTime – Data Science API")

BASE_DIR = Path(__file__).resolve().parent
MODEL_DIR = BASE_DIR.parent / "model"

MODEL_PATH = MODEL_DIR / "modelo_atraso_voo_v1.joblib"
MAPPING_PATH = MODEL_DIR / "mapeamento_categorias_v1.json"

model = load(MODEL_PATH)

with open(MAPPING_PATH, "r", encoding="utf-8") as f:
    mapping = json.load(f)


class PredictRequest(BaseModel):
    companhia: str
    origem: str
    destino: str
    data_partida: datetime
    distancia_km: int = Field(..., gt=0)
    estado_origem: Optional[str] = None
    estado_destino: Optional[str] = None


class PredictResponse(BaseModel):
    previsao: str
    probabilidade: float


def _safe_map(group: str, key: Optional[str]) -> int:
    if key is None:
        return -1
    return mapping.get(group, {}).get(key, -1)


def preprocess(voo: PredictRequest) -> pd.DataFrame:
    dt = voo.data_partida

    data = {
        "month": dt.month,
        "day_of_week": dt.weekday(),
        "op_unique_carrier": _safe_map("op_unique_carrier", voo.companhia),
        "origin": _safe_map("origin", voo.origem),
        "origin_state_nm": _safe_map("origin_state_nm", voo.estado_origem),
        "dest": _safe_map("dest", voo.destino),
        "dest_state_nm": _safe_map("dest_state_nm", voo.estado_destino),
        "crs_dep_time": dt.hour * 100 + dt.minute,
        "distance": voo.distancia_km * 0.621371,
    }

    col_order = list(getattr(model, "feature_names_in_", data.keys()))
    return pd.DataFrame([[data[c] for c in col_order]], columns=col_order)


@app.post("/predict-model", response_model=PredictResponse)
def predict(voo: PredictRequest):
    try:
        X = preprocess(voo)

        if hasattr(model, "predict_proba"):
            prob = float(model.predict_proba(X)[:, 1][0])
        else:
            prob = float(model.predict(X)[0])
            prob = max(0.0, min(1.0, prob))

        previsao = "Atrasado" if prob >= 0.5 else "Pontual"
        return PredictResponse(previsao=previsao, probabilidade=prob)

    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erro na predição: {e}")
