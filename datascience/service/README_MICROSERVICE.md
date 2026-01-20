# ✈️ Microserviço de Predição — FlightOnTime

Este microserviço é responsável por realizar inferências em tempo real sobre o atraso de voos, utilizando artefatos de Machine Learning (Scikit-Learn/Joblib) integrados a uma interface de alto desempenho com **FastAPI**.

## 📋 1. Contrato de Dados (Interface de Comunicação)

A API utiliza **Pydantic v2** para validação rigorosa no *entrypoint*. Isso garante que os dados cheguem ao modelo no formato correto, evitando falhas de processamento.

### Definição dos Campos
| Campo | Tipo | Descrição | Exemplo |
| :--- | :--- | :--- | :--- |
| `companhia` | `string` | Sigla da linha aérea (IATA) | "AZL" |
| `origem` | `string` | Código IATA do aeroporto de origem | "VCP" |
| `estado_origem` | `string` | **UF (Exatamente 2 caracteres)** | "SP" |
| `destino` | `string` | Código IATA do aeroporto de destino | "GIG" |
| `estado_destino` | `string` | **UF (Exatamente 2 caracteres)** | "RJ" |
| `distancia` | `float` | Distância entre aeroportos | 450.0 |
| `hora_partida_prevista` | `int` | Horário militar (HHMM) | 1430 |

### Exemplo de Requisição (POST `/predict-model`)
```json
{
  "companhia": "AZL",
  "origem": "VCP",
  "estado_origem": "SP",
  "destino": "GIG",
  "estado_destino": "RJ",
  "distancia": 450.0,
  "hora_partida_prevista": 1430
}
```
---
## 🛠️ 2. Lógica de Resiliência (Blindagem OOV)
Implementamos uma lógica de Out-of-Vocabulary (OOV) para garantir a estabilidade do sistema:
Funcionamento: Se a API receber uma categoria (aeroporto, companhia ou estado) que não constava no treinamento, o sistema atribui o valor -1.
Benefício: Evita falhas críticas (Erro 500) e permite que o modelo realize a predição baseada nas demais variáveis numéricas.

---
## 📂 3. Estrutura do Projeto
.
├── model/
│   ├── modelo_atraso_voo.joblib  # Modelo Preditivo
│   └── encoders_voo.joblib       # Label Encoders
└── service/
    ├── app.py                    # Core da API
    ├── requirements.txt          # Dependências
    └── README.md                 # Esta documentação

---
## 🐍 4. Código-Fonte Otimizado (app.py)
O serviço utiliza o gerenciador de ciclo de vida (lifespan) para garantir que os modelos sejam carregados apenas uma vez na inicialização, otimizando o uso de memória.

```python
# Trecho principal do entrypoint
@app.post("/predict-model")
async def predict(data: FlightInput):
    # Conversão para DataFrame (Necessário para o Scikit-Learn)
    df = pd.DataFrame([data.model_dump()])
    
    # Lógica de Blindagem OOV
    for col, le in encoders.items():
        valor = str(df[col].values[0])
        df[col] = le.transform([valor]) if valor in le.classes_ else -1
```
##📦 5. Dependências (requirements.txt)

```plaintext
fastapi==0.109.0
uvicorn==0.27.0
pandas==2.2.0
joblib==1.3.2
pydantic==2.6.0
scikit-learn
```
