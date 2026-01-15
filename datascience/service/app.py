from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from joblib import load
import pandas as pd
import json
from datetime import datetime
from pathlib import Path

app = FastAPI()

BASE_DIR = Path(__file__).resolve().parent  
MODEL_DIR = BASE_DIR.parent / "model"            

MODEL_PATH = MODEL_DIR / "modelo_atraso_voo_v1.joblib"
MAPPING_PATH = MODEL_DIR / "mapeamento_categorias_v1.json"

try:
    model = load(MODEL_PATH)
except Exception as e:
    raise RuntimeError(f"Falha ao carregar modelo em {MODEL_PATH}: {e}")

try:
    with open(MAPPING_PATH, "r", encoding="utf-8") as f:
        mapping = json.load(f)
except Exception as e:
    raise RuntimeError(f"Falha ao carregar mapeamento em {MAPPING_PATH}: {e}")

# JSON esperado
class Voo(BaseModel):
    companhia: str
    origem: str
    destino: str
    estado_origem: str
    estado_destino: str
    distancia_km: int
    data_partida: datetime


def preprocess(voo: Voo) -> pd.DataFrame:
    # dt = datetime.fromisoformat(voo.data_partida)
    dt = voo.data_partida

    data = {
        "month": dt.month,
        "day_of_week": dt.weekday(),  # segunda=0, domingo = 6
        "op_unique_carrier": mapping["op_unique_carrier"].get(voo.companhia, -1),
        "origin": mapping["origin"].get(voo.origem, -1),
        "origin_state_nm": mapping["origin_state_nm"].get(voo.estado_origem, -1),
        "dest": mapping["dest"].get(voo.destino, -1),
        "dest_state_nm": mapping["dest_state_nm"].get(voo.estado_destino, -1),
        "crs_dep_time": dt.hour * 100 + dt.minute,  # formato HHMM
        "distance": voo.distancia_km,
    }

    col_order = list(model.feature_names_in_)
    df = pd.DataFrame([[data[c] for c in col_order]], columns=col_order)

    print("Features esperadas pelo modelo:", model.feature_names_in_)
    print("Colunas do DF:", df.columns.tolist())
    print("Valores do DF:", df.iloc[0].to_dict())

    return df


@app.post("/predict-model")
def predict(voo: Voo) -> float:
    try:
        X = preprocess(voo)
        if hasattr(model, "predict_proba"):
            prob = float(model.predict_proba(X)[:, 1][0])
            return prob
        y = float(model.predict(X)[0])
        return y
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erro na predição: {e}")
