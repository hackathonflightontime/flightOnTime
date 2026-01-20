🧪 Testes – FlightOnTime Backend

📊 RESUMO FINAL

Total de testes: 11

Status: Todos passando

Build: SUCCESS

Última atualização: 18/01/2026

Os testes cobrem DTOs, camada de serviço, controllers e endpoints reais da API, garantindo o funcionamento do contrato HTTP do backend.

✅ TESTES IMPLEMENTADOS

1. Testes de DTOs (4 testes)

VooRequestTest.java – 1 teste

VooResponseTest.java – 3 testes

Validação de getters, setters e consistência dos objetos de transferência de dados utilizados nos endpoints.

2. Testes de Service (2 testes)

VooServiceTest.java – 2 testes

Testes unitários da camada de serviço, isolando regras de negócio com uso de Mockito.

3. Testes de Controller / Endpoints (4 testes)

SimpleControllerTest.java – 2 testes

PredictControllerTest.java – 2 testes

Endpoints cobertos:

🔹 POST /predict

Retorna 200 OK para requisição válida

Retorna 400 Bad Request para payload inválido

Os testes de endpoint utilizam:

@WebMvcTest

MockMvc

Service mockado com @MockBean

Esses testes validam:

Comportamento HTTP da API

Validação do payload de entrada

Status HTTP retornado

Estrutura do JSON de resposta

O objetivo é garantir o contrato da API, sem dependência de banco de dados ou microserviço Python.

4. Testes de Integração (1 teste)

IntegrationTest.java – 1 teste

Validação do carregamento do contexto da aplicação e integração básica entre camadas.

## 🚀 COMO EXECUTAR

cd backend
mvn test

📁 ESTRUTURA DAS PASTAS

src/test/java/com/flightontime/
├── unit/
│   ├── dto/
│   │   ├── VooRequestTest.java
│   │   └── VooResponseTest.java
│   ├── service/
│   │   └── VooServiceTest.java
│   └── controller/
│       ├── SimpleControllerTest.java
│       └── PredictControllerTest.java
└── integration/
    └── IntegrationTest.java


🔧 TECNOLOGIAS UTILIZADAS

JUnit 5

Mockito

Spring Boot Test

MockMvc

Maven Surefire


## 📈 PRÓXIMOS PASSOS (OPCIONAIS)

Os itens abaixo representam evoluções possíveis do projeto, fora do escopo atual de testes:

- Expansão da cobertura de testes de endpoint para novos contratos da API
- Implementação de testes com banco de dados em memória (H2)
- Testes de integração com o microserviço Python (FastAPI)
- Geração de relatório de cobertura de código (JaCoCo)

📸 EVIDÊNCIAS

Build SUCCESS

Relatórios disponíveis em target/surefire-reports/

Console:
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
