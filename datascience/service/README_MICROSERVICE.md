# ✈️ Microserviço de Previsão — flight-on-time

Microserviço FastAPI responsável por:

* carregar o modelo `.joblib` treinado
* receber dados de voo via POST
* processar features
* gerar previsão (0 = Pontual, 1 = Atrasado)
* retornar probabilidade

O backend Java consultará **esse serviço** para fazer previsões.

---

# 📘 1. Estrutura do Microserviço

```
service/
│── app.py                 → código principal FastAPI
│── requirements.txt       → dependências Python
│── modelo_voo.joblib      → arquivo exportado (gerado após treinamento)
│── README_MICROSERVICE.md → este documento
```

---

# 🔧 2. Como Rodar Localmente

### Passo 1 — Instalar dependências

```
pip install -r requirements.txt
```

### Passo 2 — Rodar o servidor

```
uvicorn app:app --reload --host 0.0.0.0 --port 8000
```

### Acessar documentação automática:

```
http://localhost:8000/docs
```

---

# 🧠 3. Estrutura Base do `app.py`

```python
from fastapi import FastAPI
import joblib
import pandas as pd

app = FastAPI()

# Carregamento do modelo (ajustado após treinamento)
# modelo = joblib.load("../model/modelo_voo.joblib")

@app.get("/")
def root():
    return {"status": "microserviço funcionando"}

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

    # TODO: converter entrada em features corretas
    # exemplo_x = converter_para_features(data)

    # TODO: usar modelo
    # pred = modelo.predict([exemplo_x])[0]
    # prob = modelo.predict_proba([exemplo_x])[0][1]

    return {
        "previsao": "pendente",
        "probabilidade": 0.0
    }
```

Depois vamos substituir os **TODOs** pelo processamento real.

---

# 🧩 4. Formato esperado da entrada

O backend Java envia:

```json
{
  "companhia": "AZ",
  "origem": "GIG",
  "destino": "GRU",
  "data_partida": "2025-11-10T14:30:00",
  "distancia_km": 350
}
```

---

# 🧪 5. Formato esperado da resposta

```json
{
  "previsao": "Atrasado",
  "probabilidade": 0.78
}
```

---

# 🔌 6. Integração com o Backend Java

O backend chamará o endpoint via HTTP:

```
POST http://localhost:8000/predict-model
```

Ou, no ambiente de produção (OCI):

```
POST http://IP_DA_VM_OCI:8000/predict-model
```

---

# 🐳 7. Rodando com Docker

O microserviço será incluído no `docker-compose.yml`:

```yaml
microservice:
  build: ./datascience/service
  container_name: flight_microservice
  ports:
    - "8000:8000"
```

Exemplo de Dockerfile:

```dockerfile
FROM python:3.10

WORKDIR /app
COPY . .
RUN pip install --no-cache-dir -r requirements.txt

CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
```

---

# ☁️ 8. Deploy na OCI (resumo)

1. Criar VM
2. Instalar Docker + Docker Compose
3. Copiar o microserviço para a VM
4. Rodar:

```
docker-compose up -d microservice
```

5. Liberar porta 8000 no firewall da OCI

---

# ✔️ 9. Checklist para produção

* modelo `.joblib` está dentro da pasta `model`
* app.py faz o carregamento correto
* conversão de features está implementada
* porta está liberada
* docker-compose configurado

---

# 👨‍💻 10. Responsável

Time de Data Science / MLOps
Acompanhamento: **Darlei**
