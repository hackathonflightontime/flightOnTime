from fastapi import FastAPI
from pydantic import BaseModel
from datetime import datetime

app = FastAPI(title="FlightOnTime – Data Science API")

# ====== REQUEST DTO ======
class PredictRequest(BaseModel):
    companhia: str
    origem: str
    destino: str
    data_partida: datetime
    distancia: int

# ====== RESPONSE DTO ======
class PredictResponse(BaseModel):
    previsao: bool
    probabilidade: float

# ====== ENDPOINT MOCK OFICIAL ======
@app.post("/predict-model", response_model=PredictResponse)
def predict(request: PredictRequest):
    """
    Endpoint mock conforme contrato oficial do projeto.
    Modelo real será integrado futuramente.
    """
    return PredictResponse(
        previsao=True,
        probabilidade=0.85
    )
