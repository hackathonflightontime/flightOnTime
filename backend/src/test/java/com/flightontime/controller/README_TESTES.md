# 🧪 Testes Automatizados — Backend (Spring Boot)

Este documento descreve a estratégia e implementação de **testes automatizados** do backend do projeto **flight-on-time**, conforme escopo definido no desafio.

O objetivo é garantir a **qualidade mínima**, **confiabilidade** e **estabilidade** da API Java, sem aumentar desnecessariamente a complexidade do projeto.

---

# 🎯 1. Objetivo dos Testes

Os testes automatizados têm como objetivo:

- Validar o funcionamento das principais camadas do backend
- Garantir que alterações futuras não quebrem comportamentos já implementados
- Atender ao requisito do projeto:
  
> **“Testes automatizados: unitários e de integração simples”**

---

# 🧪 2. Tipos de Testes Implementados

### 🔹 Testes Unitários
- Validam classes de serviço de forma isolada
- Não realizam chamadas HTTP reais
- Não dependem de banco de dados
- Não dependem do microserviço Python

### 🔹 Testes de Integração Simples
- Validam o fluxo Controller → Service
- Utilizam `MockMvc`
- Integração limitada ao contexto web do Spring
- Dependências externas são simuladas (mock)

---

# 🧱 3. Estrutura dos Testes

´´´
backend
└── src
└── test
└── java
└── com.flightontime
├── controller
│ └── PredictControllerTest.java
└── service
└── PredictServiceTest.java
´´´´

---

# ⚙️ 4. Tecnologias Utilizadas

- Java 17
- Spring Boot Test
- JUnit 5 (Jupiter)
- Mockito
- Maven Surefire Plugin

Todas as dependências de teste são gerenciadas via `spring-boot-starter-test`.

---

# ▶️ 5. Como Executar os Testes

No diretório `backend`:

```bash
mvn clean test
