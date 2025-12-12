from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def root():
    return {"status": "microservico funcionando"}

@app.post("/predict-model")
def predict(data: dict):
    """
    data esperado:
    {
      "companhia": "AZ",
      "origem": "GIG",
      "destino": "GRU",
      "data_partida": "2025-11-10T14:30:00",
      "distancia_km": 350
    }
    """
    # Time de DS troca essa lógica dummy pelo modelo real
    return {
        "previsao": "pendente",
        "probabilidade": 0.0
    }