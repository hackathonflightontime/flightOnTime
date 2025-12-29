
# 🚀 Sprint 2 — Planejamento e Direcionamento

## 📅 Contexto

Sprint 2 dá início à **fase operacional do projeto**, após a consolidação conceitual da Sprint 1.
O foco deixa de ser definição e passa a ser **organização, padronização e integração real** entre Data Science e Back-End.

---

## 🎯 Objetivo Geral da Sprint 2

Organizar o projeto para escalar com segurança, garantindo:

* Repositório único e oficial
* Acessos corretos para o time
* Processo de contribuição claro
* Integração DS ↔ BE sem bloqueios
* Microserviço FastAPI pronto para consumo pelo Back-End

---

## 🗂️ Escopo da Sprint 2

### 1️⃣ Migração para Repositório Oficial

**Objetivo**
Centralizar todo o projeto em um único repositório oficial no GitHub.

**Atividades**

* Definir o novo repositório oficial
* Migrar todo o código-fonte existente
* Organizar a estrutura por módulos (DS / BE / Docs)
* Validar pós-migração:

  * build
  * estrutura
  * leitura do README

📌 A migração tem foco organizacional, sem mudança de escopo técnico.

---

### 2️⃣ Gestão de Permissões e Acessos

**Papéis definidos**

* **Admin**: gestão do repositório e decisões finais
* **Write**: desenvolvimento ativo (DS e BE)
* **Read**: acompanhamento e revisão

---

### 3️⃣ Documentação do Processo

**Conteúdo a documentar**

* Passo a passo da migração
* Padrão de branches
* Fluxo de Pull Request
* Boas práticas de contribuição
* Comunicação DS ↔ BE

---

## 🔁 Revisão da Sprint 1

**Itens revisados**

* Contrato JSON (DS ↔ BE)
* Regra do alvo do modelo (atraso ≥ 15 min)
* Features iniciais
* Estado atual do MVP

Após essa revisão, a Sprint 1 é considerada **encerrada**.

---

## ⚙️ Estrutura do Microserviço FastAPI (Data Science)

Esta Sprint define oficialmente a **estrutura padrão do FastAPI** que será usada pelo projeto.

### 📁 Estrutura de Pastas (DS)

```
datascience/
│
├── service/                 # Microserviço FastAPI
│   ├── app/
│   │   ├── main.py           # Inicialização da API e rotas
│   │   ├── schemas.py        # Contratos (entrada e saída)
│   │   ├── model_loader.py   # Carregamento do modelo .joblib
│   │   └── predict.py        # Lógica de inferência (RAW)
│   │
│   ├── model/
│   │   └── modelo_atraso_voo.joblib
│   │
│   ├── requirements.txt
│   └── README.md
│
├── notebooks/               # EDA e treinamento (Sprint 1)
├── sql/                     # Scripts BigQuery
├── docs/                    # Diagramas e documentação
└── README_DATASCIENCE.md
```

---

## 🔌 Contrato do Endpoint FastAPI

### Endpoint

```
POST /predict-model
```

### Entrada (JSON)

```json
{
  "companhia": "AZ",
  "origem": "GIG",
  "destino": "GRU",
  "data_partida": "2025-11-10T14:30:00",
  "distancia": 350
}
```

### Saída — **RAW (sem regra de negócio)**

```json
{
  "prediction": 1,
  "probabilities": [0.22, 0.78]
}
```

📌 **Importante**

* FastAPI **não interpreta** o resultado
* FastAPI **não aplica threshold**
* FastAPI **não retorna texto**
* A decisão final é responsabilidade do **Back-End**

---

## 🚧 Responsabilidades na Sprint 2

### Data Science

* Garantir que o FastAPI:

  * Carrega o modelo corretamente
  * Retorna saída RAW
  * Segue o contrato acordado

### Back-End

* Consumir o endpoint FastAPI
* Interpretar `prediction` e `probabilities`
* Aplicar regra de negócio
* Expor endpoint final ao consumidor

---

## 📦 Entregáveis Esperados da Sprint 2

* Repositório oficial organizado
* FastAPI estruturado e funcional
* README atualizado
* Documentação da migração concluída
* Integração DS ↔ BE avançando sem bloqueios

---

## 📌 Diretrizes Gerais

* Decisões sempre registradas
* Organização acima de velocidade
* MVP funcional antes de otimizações
* DS calcula, BE decide

---

## 🧭 Mensagem Final

> Sprint 2 é sobre **organizar o terreno**
> para a integração fluir sem retrabalho.


