# ✈️ **flight-on-time — Briefing Oficial do Projeto**

## 🎯 1. Visão Geral

O projeto **flight-on-time** tem como objetivo prever se um voo tem risco de **atraso** com base em informações operacionais como companhia aérea, origem, destino, horário e distância.

A solução é composta por:

* **Módulo de Data Science**: cria o modelo preditivo
* **Microserviço FastAPI**: serve o modelo via HTTP
* **Backend Spring Boot**: expõe a API pública `/predict`
* **Banco PostgreSQL**: registra logs e estatísticas
* **Infrastructure (OCI)**: executa tudo em ambiente real via Docker Compose

---

## 💼 2. Contexto do Problema

Atrasos de voos afetam:

* Passageiros
* Companhias aéreas
* Aeroportos
* Operações de logística

Consequências comuns:

* Conexões perdidas
* Altos custos operacionais
* Má experiência do cliente

Antecipar o risco de atraso permite:

* avisar passageiros antes
* ajustar operações
* planejar gate, pista e logística
* reduzir custos

---

## 🚀 3. Objetivo do MVP

Criar um sistema capaz de:

1. Receber dados de um voo via JSON
2. Enviar esses dados ao modelo preditivo
3. Retornar:

   * **previsão**: "Pontual" ou "Atrasado"
   * **probabilidade**: número entre 0 e 1
4. Registrar logs no banco
5. Disponibilizar estatísticas via endpoint `/stats` *(opcional, recomendado)*

---

## 🧠 4. Solução Proposta

### 🔹 **Data Science**

* Exploração dos dados
* Engenharia de features
* Treinamento do modelo
* Exportação `.joblib`
* Microserviço FastAPI

### 🔹 **Backend**

* API REST em Java/Spring Boot
* Validação das requisições
* Integração com FastAPI
* Persistência em PostgreSQL
* Documentação via Swagger

### 🔹 **OCI Deploy**

* VM Ubuntu
* Docker + Docker Compose
* Containers: backend, microserviço, postgres

---

## 🏗️ 5. Arquitetura (resumo)

```
Frontend/Client
     ↓
Backend (Java)
     ↓
Microserviço (Python)
     ↓
Modelo ML
     ↓
Banco PostgreSQL
```

Tudo containerizado e orquestrado por Docker Compose na OCI.

---

## 📅 6. Prazos do Projeto

| Etapa           | Período    |
| --------------- | ---------- |
| Início          | 08/12/2025 |
| Entrega técnica | 13/01/2026 |
| Entrega final   | 20/01/2026 |

---

## 🎯 7. Entregáveis

### 🔹 Obrigatórios

* API `/predict` funcionando
* Modelo ML treinado
* Microserviço FastAPI funcional
* Documentação clara
* Demonstração técnica

### 🔹 Recomendados (fortalecem a nota)

* Persistência no banco
* Endpoint `/stats`
* Docker Compose completo
* Deploy OCI
* Diagramas de arquitetura
* Kanban organizado (Trello)

---

## 👥 8. Estrutura do Time

O time é dividido em:

* **Data Science** — modelagem + microserviço
* **Back-End** — API Java + banco
* **OCI / Deploy** — infraestrutura e Docker
* **Documentação** — READMEs, diagramas, briefing
* **Tech Lead (Darlei)** — responsável por guia, decisões e coordenação

---

## 📌 9. Regras Técnicas do Projeto

* Backend **não deve** carregar modelo ML local
* Toda previsão deve passar via **HTTP para FastAPI**
* Banco deve ser **PostgreSQL em ambiente Docker/OCI**
* Em dev pode usar H2 para facilitar
* Código deve ser limpo, modular e versionado corretamente
* Toda mudança significativa passa por **Pull Request**

---

## 🧭 10. Definição de Pronto (Done)

Um item é considerado entregue quando:

* funciona localmente
* funciona via Docker
* funciona na OCI
* está documentado
* está testado (mínimo manual)
* está integrado com os demais módulos

---

## 🏁 11. Objetivo Final

Entregar um MVP **robusto, claro, funcional e apresentável**, demonstrando:

* domínio básico de arquitetura
* modelagem preditiva funcional
* comunicação entre serviços
* deploy real na nuvem
* documentação profissional

---

### ✈️ flight-on-time — “Transformando dados em decisões.”

