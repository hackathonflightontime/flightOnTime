# 🗓️ **README_TIMELINE.md — Cronograma & Sprints**

# ✈️ flight-on-time — Planejamento Oficial do Projeto

Este documento define o **cronograma completo** do projeto, dividido em **Sprints**, entregáveis, prioridades e data final de entrega.

Ele é a principal referência para garantir que o time trabalhe organizado, focado e dentro dos prazos.

---

# 📅 1. Datas importantes

| Descrição                          | Data           |
| ---------------------------------- | -------------- |
| Início do projeto                  | **08/12/2025** |
| Entrega técnica (testes e ajustes) | **13/01/2026** |
| Prazo final do hackathon           | **20/01/2026** |

Entre 13/01 e 20/01 o foco é **refinar, testar, documentar e preparar a apresentação**.

---

# 🚀 2. Estrutura Geral das Sprints

O projeto é dividido em **3 sprints principais** + **sprint final**.

```
Sprint 1 → Base técnica e EDA
Sprint 2 → Modelo + integrações
Sprint 3 → Ajustes, Docker e documentação
Sprint Final → Testes, deploy OCI e apresentação
```

---

# 🟦 **Sprint 1 — (08/12/2025 a 20/12/2025)**

### 🎯 Objetivo: BASE DO PROJETO + EDA + ESTRUTURA

#### 🧱 Entregas obrigatórias:

* Estrutura completa do projeto no VS Code
* Todas as pastas e READMEs criados
* Ambiente DS configurado
* Notebook EDA iniciado
* Modelo baseline simples testado
* Backend criado com endpoint `/predict` vazio
* Definição do contrato JSON BE ↔ DS
* Diagrama arquitetural inicial (rascunho)

#### 🛠️ Tech Tasks:

* Criar esqueleto do backend
* Criar esqueleto do microserviço FastAPI
* Criar banco local (H2)
* Preparar dataset
* Subir primeiros commits (estrutura)

#### ✔ Critério de DONE:

* Repositório organizado
* Time entendendo suas responsabilidades
* API e microserviço rodando “Hello World”

---

# 🟧 **Sprint 2 — (21/12/2025 a 03/01/2026)**

### 🎯 Objetivo: MODELO + INTEGRAÇÃO

#### 🧠 Entregas DS:

* Feature engineering final
* Modelo treinado e avaliado
* Exportação `.joblib`
* Microserviço FastAPI lendo o modelo real

#### 🔌 Entregas BE:

* Implementar chamada HTTP para o microserviço
* Implementar DTOs e validações
* Criar Model + Repository + Service do log das previsões
* Criar endpoint `/predict` funcional

#### 🐳 Extra recomendado:

* Início do docker-compose.yml
* Dockerfile do microserviço

#### ✔ Critério de DONE:

* Previsão funcionando **localmente**, ponta a ponta
* Modelo respondendo
* Backend processando e retornando previsões

---

# 🟨 **Sprint 3 — (04/01/2026 a 13/01/2026)**

### 🎯 Objetivo: DOCKER, BANCO E DOCUMENTAÇÃO

#### ⚙️ Entregas técnicas:

* docker-compose completo:

  * backend
  * microserviço
  * postgres
* Conexão BE ↔ PG funcionando
* Query `/stats` implementada
* H2 apenas para dev, Postgres em homologação
* Documentação completa:

  * backend
  * datascience
  * microserviço
  * OCI
  * briefing
  * timeline
  * dailies
* Coleção Postman pronta
* Testes manuais finais

#### ✔ Critério de DONE:

* Todo o projeto rodando com `docker-compose up -d`
* Time apto a explicar o fluxo completo

---

# 🟥 **Sprint Final — (14/01/2026 a 20/01/2026)**

### 🎯 Objetivo: DEPLOY OCI + DEMO + APRESENTAÇÃO

#### 🌐 Entregas:

* Deploy na VM OCI
* Liberação de portas
* Backend acessível externamente
* Microserviço funcionando na web
* Banco Postgres persistente
* Testes externos (Postman, cURL)
* Ajustes finais no docker-compose
* Preparação do pitch:

  * problema
  * solução
  * arquitetura
  * demo ao vivo
  * próximos passos

#### ✔ Critério de DONE:

* Demonstração perfeita
* API respondendo na OCI
* Justificativa técnica sólida
* Documentação entregue

---

# 🏁 4. Linha do Tempo Visual

```
08/12 ──────────────────── 20/12  
        Sprint 1  
21/12 ──────────────────── 03/01  
        Sprint 2  
04/01 ──────────────────── 13/01  
        Sprint 3  
14/01 ──────────────────── 20/01  
        Sprint Final  
```

---

# 🧭 5. Responsabilidades

* **Tech Lead (Darlei)**: guia, decisões, bloqueios, revisões
* **DS Team**: EDA, modelo, microserviço
* **Back-End Team**: API, banco, integrações
* **OCI / DevOps**: deploy, docker, infraestrutura
* **Docs**: READMEs, diagramas, briefing, pitch

---

# 📝 6. Definição de finalização (geral)

Uma tarefa só está pronta quando:

* funciona localmente
* funciona no Docker
* funciona na OCI
* está documentada
* tem commit limpo e revisado
* atende ao contrato DS ↔ BE

---

# ✈️ flight-on-time — "Prever, antecipar e melhorar."
