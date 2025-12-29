# 📄 Sprint 1 — Pauta / Ata da Reunião

**Reunião:** Quinta-feira — 18/12/2025 (21h)  
**Participantes:** Sueli, Márcio, Bruno, Tiago  
**Registros complementares (assíncrono):** Eduardo, Manuel, Darlei, Antonio  

---

## 1️⃣ Acesso à reunião e contexto inicial

**Relatado por:** Sueli  

- Parte do alinhamento ocorreu de forma assíncrona via WhatsApp e NoCountry.
- Decisão de consolidar as definições por escrito para evitar perda de contexto.

---

## 2️⃣ Papel da área de Data Science no projeto

**Trazido por:** Sueli  

- O papel inicial do DS é destravar o Back-End, definindo:
  - Contrato de dados (JSON);
  - Critérios do modelo;
  - Formato de entrega do artefato de ML.
- O foco da Sprint 1 é um **MVP funcional e integrável**, não performance máxima.

---

## 3️⃣ Definição do Contrato JSON (DS ↔ BE)

**Condução:** Sueli  
**Validação:** Eduardo e Manuel (assíncrono)  

**Pontos discutidos:**
- Necessidade de congelar os campos do JSON para evitar retrabalho no Back-End.
- O modelo deve ser treinado exatamente com os campos definidos no contrato.

**Campos propostos:**
- `companhia`
- `origem`
- `destino`
- `data_partida`
- `distancia`

**Encaminhamento:**
- Sueli e Eduardo ficou responsável por alinhar e fechar os campos do JSON com Eduardo e Manuel.

---

## 4️⃣ Regra do Alvo do Modelo (Target)

**Proposto por:** Sueli  
**Aceito pelo grupo:** sem objeções  

- Tipo de modelo: **Classificação binária**
  - Pontual
  - Atrasado
- Critério:
  - `atraso ≥ 15 minutos` → **Atrasado**

**Colunas discutidas:**
- `DEP_DELAY` como alvo principal
- `ARR_DELAY` como alternativa futura

**Decisão:**
- Regra de atraso ≥ 15 minutos adotada como padrão do projeto.

---

## 5️⃣ Campos do Dataset (Features do Modelo)

**Proposto por:** Sueli  
**Complementado por:** Márcio  

**Campos mínimos acordados:**
- `op_unique_carrier`
- `origin`
- `dest`
- `fl_date`
- `crs_dep_time`
- `distance`
- `dep_delay` (apenas como alvo, não feature)

**Campos adicionais sugeridos:**
- `origin_state_nm`
- `dest_state_nm`

**Encaminhamento:**
- Iniciar com conjunto enxuto.
- Caso a qualidade do modelo seja insuficiente, novas colunas poderão ser reincorporadas.

---

## 6️⃣ Fonte de Dados

**Discussão conduzida por:** Sueli  

- Dataset principal: **Kaggle – Flight Delay Dataset 2024**
- Dataset ANAC (VRA):
  - Citado como alternativa ou expansão;
  - Não bloqueante para a Sprint 1.

**Decisão:**
- Sprint 1 segue com Kaggle.
- ANAC entra como backlog técnico / evolução futura.

---

## 7️⃣ Estratégia de Processamento de Dados

**Proposto por:** Sueli  
**Debatido com:** Márcio e Darlei  

- Dataset com aproximadamente **5GB**.
- Opções avaliadas:
  - Pandas/Polars no Colab (com recorte de colunas);
  - BigQuery para limpeza via SQL (Free Tier).

**Pontos levantados:**
- BigQuery facilitaria limpeza e filtragem.
- Dúvida se o treino ocorreria no BigQuery ML ou se os dados retornariam ao Colab.

**Status:**
- Sugestão registrada, decisão não fechada.
- Sprint 1 segue com Colab.
- BigQuery fica como otimização futura.

---

## 8️⃣ Entrega Técnica do DS

**Proposto por:** Sueli  

- DS entregará ao Back-End:
  - Modelo serializado (`.joblib` ou `.pkl`).

**Alinhamento com Trello do BE:**
- Card: “Receber modelo serializado”.

**Decisão:**
- Formato de entrega do DS definido.

---

## 9️⃣ Back-End — Alinhamento com DS

**Contribuições:** Márcio e Darlei  

- BE deve avançar desde já com:
  - Endpoint `/predict`;
  - Contrato JSON definido;
  - Resposta mockada.
- BE não depende do modelo para avançar na Sprint 1.
- Integração futura substituirá o mock pelo modelo real.

---

## 🔟 Comunicação e Organização


- Decisões devem ser registradas formalmente (Trello / Docs / NoCountry).

---

## 📌 Resumo da Sprint 1

- Contrato JSON definido
- Regra de atraso fechada (≥ 15 min)
- Features iniciais acordadas
- Kaggle como fonte principal
- DS treinando no Colab
- BE avançando com mock
- BigQuery e ANAC como backlog
