# ✈️ **Backend — API Java (Spring Boot)**

Parte do projeto **flight-on-time**, responsável por receber os dados do voo, enviar ao modelo de previsão e retornar **previsão + probabilidade** para o cliente.

---

# 📘 1. Objetivo do Backend

O backend é responsável por:

* Expor o endpoint **POST /predict**
* Validar dados da requisição
* Enviar dados ao microserviço Python (FastAPI)
* Receber a previsão e devolver ao usuário
* Registrar logs no banco PostgreSQL
* Expor endpoints adicionais (ex.: **GET /stats**)
* Documentar a API com Swagger/OpenAPI

---

# 🧱 2. Estrutura do Projeto

```
backend
│── pom.xml
│── src
│   ├── main
│   │   ├── java/com/flightontime
│   │   │   ├── controller
│   │   │   ├── dto
│   │   │   ├── service
│   │   │   ├── client
│   │   │   ├── exception
│   │   │   ├── model
│   │   │   └── repository
│   │   └── resources
│   │       ├── application.properties
│   └── test/java/com/flightontime
```

---

# 🔧 3. Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.2.x**
* Spring Web
* Spring Validation
* Spring Data JPA
* H2 Database (ambiente local)
* PostgreSQL 15 (produção via Docker / OCI)
* Swagger (OpenAPI)
* Docker / Docker Compose

---

# ⚙️ 4. Dependências do `pom.xml`

```xml
<!-- Web -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Validation -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- JPA -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Banco H2 (dev) -->
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>

<!-- PostgreSQL (prod / Docker / OCI) -->
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>

<!-- Swagger / OpenAPI -->
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>

<!-- Facilidades para desenvolvimento -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
</dependency>
```

---

# 🧠 5. Endpoints

## 🔹 **POST /predict**

### Entrada:

```json
{
  "companhia": "AZ",
  "origem": "GIG",
  "destino": "GRU",
  "data_partida": "2025-11-10T14:30:00",
  "distancia_km": 350
}
```

### Saída:

```json
{
  "previsao": "Atrasado",
  "probabilidade": 0.78
}
```

---

## 🔹 **GET /stats** *(opcional / recomendado)*

Retorna estatísticas como:

```json
{
  "total_previsoes": 240,
  "percentual_atraso": 0.34
}
```

---

# 🧩 6. DTO Inicial

```java
public record PredictRequest(
    String companhia,
    String origem,
    String destino,
    String data_partida,
    Integer distancia_km
) {}
```

---

# 🔌 7. Integração com o Microserviço Python

O backend se comunica com FastAPI via HTTP.

### Exemplo de chamada:

```java
RestTemplate rest = new RestTemplate();
String url = "http://localhost:8000/predict-model";

ResponseEntity<ResponseDTO> response =
      rest.postForEntity(url, dto, ResponseDTO.class);
```

---

# 🏦 8. Banco de Dados

### Dev → **H2 Database**

Arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:flighton
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

Acesso ao console:

```
http://localhost:8080/h2-console
```

---

### Produção / OCI → **PostgreSQL 15**

```properties
spring.datasource.url=jdbc:postgresql://postgres:5432/flighton
spring.datasource.username=admin
spring.datasource.password=admin123
spring.jpa.hibernate.ddl-auto=update
```

---

# 🐳 9. Execução via Docker (com Docker Compose)

O backend será rodado junto com:

* PostgreSQL
* Microserviço FastAPI

Exemplo de entrada no docker-compose (virá depois):

```yaml
backend:
  build: ./backend
  container_name: flight_backend
  ports:
    - "8080:8080"
  depends_on:
    - postgres
    - microservice
```

---

# ▶️ 10. Como Rodar Localmente (sem Docker)

```
cd backend
mvn spring-boot:run
```

Swagger disponível em:

```
http://localhost:8080/swagger-ui.html
```

---

# 🧪 11. Testes Unitários

O backend utilizará:

* JUnit 5
* Mockito
* Spring Boot Test

Testes ficam em:

```
src/test/java/com/flightontime
```

---

# 🧑‍💻 12. Dev Notes

* O backend **não carrega o modelo na memória**
* Toda previsão é feita via requisição ao microserviço
* A API deve ser simples e rápida

---

# 💼 Tech Lead

Documento elaborado para o time por **Darlei**.
Essas pastas são parte do esqueleto e serão preenchidas ao longo do projeto.
Dúvidas Whatapp ou na Nocoutry. Respondo sempre, demoro um pouco mais sempre respondo. 
---
