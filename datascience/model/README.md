# ✈️ FlightOnTime - Serviço de Predição

Este diretório contém a inteligência do projeto FlightOnTime, incluindo o modelo preditivo e a API de serviço.

## 🔗 Downloads Obrigatórios (Arquivos Pesados)

Para garantir a performance do repositório, os arquivos binários estão hospedados no Google Drive. Baixe ambos e coloque-os na pasta `/model`:

* 📦 [**Baixar: modelo_atraso_voo.joblib**](https://drive.google.com/file/d/1jwScHPdoveOBGXMXaugEnDdGgqQtursp/view?usp=sharing)
* 📑 [**Baixar: encoders_voo.joblib**](https://drive.google.com/file/d/10I1svImKYBN_PSE-OgoKIYEqD8jWQdpa/view?usp=sharing)

---

## 📋 Contrato de Dados (JSON esperado pela API)

O serviço `/predict-model` espera receber um objeto JSON com a estrutura abaixo. **Atenção:** Os campos `estado_origem` e `estado_destino` são obrigatórios para a precisão do modelo.

| Campo | Descrição | Exemplo |
| :--- | :--- | :--- |
| `companhia` | Sigla da linha aérea | "AA" |
| `origem` | Código IATA do aeroporto de origem | "JFK" |
| `estado_origem` | Sigla do estado (UF) de origem | "NY" |
| `destino` | Código IATA do aeroporto de destino | "LAX" |
| `estado_destino` | Sigla do estado (UF) de destino | "CA" |
| `distancia` | Distância total do voo (numérico) | 3977.0 |
| `hora_partida_prevista` | Horário (formato HHMM) | 1430 |

---

## 🛠️ Exemplo de Implementação (Python)

Abaixo, o exemplo de como o serviço processa os dados usando o modelo e o tradutor (encoder) com a lógica de resiliência:

```python
import joblib
import pandas as pd

# Carregar os artefatos
modelo = joblib.load('model/modelo_atraso_voo.joblib')
encoders = joblib.load('model/encoders_voo.joblib')

def predict(dados):
    df = pd.DataFrame([dados])
    # Mágica da Tradução (Blindagem contra dados novos)
    for col, le in encoders.items():
        valor = str(df[col].values[0])
        df[col] = le.transform([valor]) if valor in le.classes_ else -1
    return modelo.predict(df)
