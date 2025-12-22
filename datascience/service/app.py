from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
import joblib
import pandas as pd
import os
from contextlib import asynccontextmanager

# 1. Definição do Esquema de Dados (Contrato da API com Validação)
class FlightInput(BaseModel):
    companhia: str
    origem: str
    estado_origem: str = Field(..., max_length=2)
    destino: str
    estado_destino: str = Field(..., max_length=2)
    distancia: float
    hora_partida_prevista: int

# 2. Gerenciador de Ciclo de Vida para carregamento dos Modelos
assets = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Lógica de Inicialização: Define caminhos e carrega arquivos joblib
    base_path = os.path.dirname(__file__)
    model_path = os.path.abspath(os.path.join(base_path, "..", "model", "modelo_atraso_voo.joblib"))
    encoder_path = os.path.abspath(os.path.join(base_path, "..", "model", "encoders_voo.joblib"))
    
    try:
        assets["modelo"] = joblib.load(model_path)
        assets["encoders"] = joblib.load(encoder_path)
        print("INFO: Artefatos de ML carregados com sucesso.")
    except Exception as e:
        print(f"CRITICAL: Falha ao carregar modelos: {e}")
        raise RuntimeError("O servidor não pode iniciar sem os modelos.")
    
    yield
    # Limpeza ao desligar o servidor
    assets.clear()

# Inicialização da aplicação com metadados profissionais
app = FastAPI(
    title="FlightOnTime - Predict Service",
    description="API de predição de atrasos de voos - Projeto Lighthouse",
    version="1.0.0",
    lifespan=lifespan
)

@app.post("/predict-model", status_code=200)
async def predict(data: FlightInput):
    """
    Realiza a predição de atraso com base em dados validados.
    Implementa lógica de resiliência (OOV) para novas categorias.
    """
    modelo = assets.get("modelo")
    encoders = assets.get("encoders")

    try:
        # Converte a entrada Pydantic em DataFrame
        input_data = data.model_dump()
        df = pd.DataFrame([input_data])

        # Processamento de Encoders com tratamento de categorias desconhecidas (Blindagem)
        for col, le in encoders.items():
            valor = str(df[col].values[0])
            if valor in le.classes_:
                df[col] = le.transform([valor])
            else:
                # Caso a categoria seja nova (Ex: aeroporto novo), usa o marcador padrão
                df[col] = -1

        # Inferência do Modelo
        prediction = int(modelo.predict(df)[0])
        probability = float(modelo.predict_proba(df)[0][1])

        return {
            "status": "sucesso",
            "resultado": {
                "previsao": "Atrasado" if prediction == 1 else "Pontual",
                "probabilidade_atraso": round(probability, 4),
                "classe_id": prediction
            }
        }

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Erro interno na inferência: {str(e)}")
