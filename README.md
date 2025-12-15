# ✈️ flight-on-time

Sistema de previsão de atrasos de voos usando **Data Science (Python + FastAPI)** e **Back-End (Java + Spring Boot)** com deploy em **OCI – Oracle Cloud Infrastructure**.

Este projeto foi desenvolvido como solução para o desafio *FlightOnTime*, cujo objetivo é prever se um voo provavelmente será **Pontual** ou **Atrasado**, baseado em informações operacionais.

---

# 📘 1. Arquitetura Geral do Projeto hacatoon

```
Usuário → API Java (Spring Boot) → Microserviço Python (FastAPI) →
Modelo de Machine Learning → Resposta (previsão + probabilidade)
```

Ambos os serviços serão deployados em uma **VM na OCI**, usando **Docker + Docker Compose**.

---

# 🎯 2. Objetivo do Projeto

Criar um MVP funcional capaz de:

* Receber dados de um voo via JSON
* Enviar esses dados ao modelo preditivo
* Retornar:

  * `previsao`: “Pontual” ou “Atrasado”
  * `probabilidade`: valor entre 0 e 1

Além disso:

* Registrar histórico no banco PostgreSQL
* Permitir endpoints extras, como estatísticas (`/stats`)
* Documentar tudo com clareza para o hackathon

---

# 🧱 3. Estrutura de Pastas do Projeto

```
flight-on-time
│── backend/                → API Java / Spring Boot
│── datascience/            → Modelo ML + Microserviço FastAPI
│── oci/                    → Scripts e guias de deploy na Oracle Cloud
│── docs/                   → Briefing, timeline, arquitetura e dailies
│── .github/                → PR templates, issues, contributing
│── postman/                → Testes de API
│── examples/               → Exemplos de payloads
│── README.md               → Este arquivo
```

---

# 🛠️ 4. Tecnologias Utilizadas

### 🔧 **Back-End**

* Java 17
* Spring Boot 3.2
* Spring Web
* Spring Data JPA
* H2 (dev) + PostgreSQL (prod / OCI)
* OpenAPI (Swagger)
* Docker / Docker Compose

### 🧠 **Data Science**

* Python 3.10
* FastAPI
* Uvicorn
* Pandas
* Scikit-Learn
* Joblib

### ☁️ **Infraestrutura / OCI**

* Oracle Compute Instance (Always Free)
* Docker + Docker Compose
* Firewall Rules
* Variáveis de Ambiente

---

# 🚀 5. Como Rodar Localmente (visão geral)

### 🔧 Backend (Java)

```
cd backend
mvn spring-boot:run
```

### 🧠 Microserviço Python

```
cd datascience/service
uvicorn app:app --host 0.0.0.0 --port 8000
```

### 🐳 Docker Compose (versão final)

```
docker-compose up -d
```

---

# 📄 6. Documentação Complementar

Toda a documentação detalhada está em `/docs/`:

| Documento            | Finalidade                         |
| -------------------- | ---------------------------------- |
| `README_BRIEFING.md` | Entendimento do problema e solução |
| `README_TIMELINE.md` | Sprints, prazos e cronograma       |
| `README_DAILIES.md`  | Processo de trabalho do time       |
| `arquitetura/`       | Diagramas visuais                  |

---

# 🧪 7. Testes da API

Coleção Postman disponível em:
`postman/collection.json`

Exemplos de payloads em:
`examples/request_example.json`

---

# 🧑‍💻 8. Estrutura do Time e Fluxo de Trabalho

O projeto é dividido em 3 frentes:

* **Data Science** → modelo + microserviço
* **Back-End** → API + banco + validações
* **OCI** → deploy e infraestrutura

O fluxo entre equipes segue um contrato de integração claro, documentado nos READMEs específicos.

---

# 🏁 9. Status Atual

✔ Estrutura do projeto criada
- ⬜ Documentação interna sendo preenchida
- ⬜ Implementação inicial do módulo DS
- ⬜ Implementação inicial do backend
- ⬜ Deploy OCI
- ⬜ Testes integrados
- ⬜ Apresentação final

---

# 🙌 10. Autor / Tech Lead

Projeto organizado por: **Darlei**
Com suporte técnico e documentação de toda a equipe.

---

# ✈️ Flight On Time — Transformando dados em previsões úteis.

