# Modelo de Predição Treinado - FlightOnTime

Este diretório contém os artefatos de inteligência artificial do projeto, incluindo o modelo preditivo e os transformadores de dados necessários para a inferência.

## 🔗 Downloads Obrigatórios (Arquivos no Google Drive)

Devido ao tamanho dos arquivos binários, eles devem ser baixados nos links abaixo e colocados nesta pasta (`/datascience/model/`):

* 📦 [**Baixar: modelo_atraso_voo.joblib**](https://drive.google.com/file/d/1jwScHPdoveOBGXMXaugEnDdGgqQtursp/view?usp=sharing)
* 📑 [**Baixar: encoders_voo.joblib**](https://drive.google.com/file/d/10I1svImKYBN_PSE-OgoKIYEqD8jWQdpa/view?usp=sharing)

---

## 📋 Contrato de Dados (Integração com a API)

Para realizar predições utilizando estes artefatos, a API (`/datascience/service/app.py`) espera o seguinte esquema de dados, garantindo a integridade da comunicação com o Backend:

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

## 🛠️ Lógica de Resiliência (Blindagem OOV)

O pipeline de inferência implementa um sistema de blindagem contra dados desconhecidos. Caso receba uma categoria que não constava no treinamento original, o sistema utiliza o valor padrão `-1`. Isso garante a robustez do microserviço, evitando falhas críticas e permitindo a continuidade da operação.