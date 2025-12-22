# ✈️ FlightOnTime - Serviço de Predição

Este diretório contém a inteligência do projeto, incluindo o modelo preditivo e a API de serviço.

## 🔗 Downloads Obrigatórios (Ficheiros no Google Drive)

Devido ao tamanho dos ficheiros binários, estes devem ser descarregados nos links abaixo e colocados na pasta `/datascience/model/`:

* 📦 [**Descarregar: modelo_atraso_voo.joblib**](https://drive.google.com/file/d/1jwScHPdoveOBGXMXaugEnDdGgqQtursp/view?usp=sharing)
* 📑 [**Descarregar: encoders_voo.joblib**](https://drive.google.com/file/d/10I1svImKYBN_PSE-OgoKIYEqD8jWQdpa/view?usp=sharing)

---

## 📋 Contrato de Dados (JSON esperado pela API)

A API espera receber os seguintes campos para realizar a predição. [cite_start]Esta definição evita erros de integração com o Back-End:

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

## 🛠️ Lógica de Resiliência

O serviço utiliza um sistema de "blindagem": caso receba uma sigla ou estado que não constava no treino original, o sistema utiliza o valor padrão `-1`. Isto garante que o sistema não falhe e continue a operação mesmo com dados novos.
