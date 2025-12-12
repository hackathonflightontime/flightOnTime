# 📝 **PULL_REQUEST_TEMPLATE.md — COM EXEMPLO**

Cole **até o EXEMPLO incluído** no arquivo `.github/PULL_REQUEST_TEMPLATE.md`.
Assim ninguém tem desculpa de preencher errado.

---

# 🔀 PULL REQUEST — flight-on-time

*(TEMPLATE OFICIAL — preencha abaixo para abrir um PR)*

## 📝 Descrição do PR

Explique o que foi feito e por quê.

> Exemplo: “Implementa o endpoint `/predict` e integra com o microserviço.”

---

## 🔧 Tipo de mudança

Marque uma opção:

* [ ] `feat` — nova funcionalidade
* [ ] `fix` — correção de bug
* [ ] `docs` — documentação
* [ ] `chore` — configs / docker / build
* [ ] `refactor` — refatoração
* [ ] `test` — testes
* [ ] `style` — formatação

---

## 🧪 Como testar este PR?

Explique passos claros:

```
1. Rodar backend com `mvn spring-boot:run`
2. Rodar microserviço com `uvicorn app:app --port 8000`
3. Enviar POST para /predict
4. Verificar resposta
```

---

## 📸 Evidências (prints, logs ou payloads)

Coloque imagens, trechos de código ou JSON de exemplo:

```
Resposta esperada:
{
  "previsao": "Atrasado",
  "probabilidade": 0.78
}
```

---

## ✔ Checklist do Desenvolvedor

Antes de pedir revisão:

* [ ] Branch correta (`feature/...`, `fix/...`)
* [ ] Commits pequenos e claros
* [ ] Testado localmente
* [ ] Testado no Docker (se aplicável)
* [ ] Documentação atualizada
* [ ] Sem arquivos desnecessários

---

## 🔍 Checklist do Revisor

O revisor confere:

* [ ] Código limpo
* [ ] Sem duplicações
* [ ] Funcionalidade testada
* [ ] PR pequeno e objetivo
* [ ] Nada quebrado
* [ ] Aprovado

---

## 👤 Responsável

Nome:

---

---

# 🟦 **AGORA O EXEMPLO COMPLETO — PR PREENCHIDO (para iniciantes copiarem)**

> 🔥 **Este exemplo fica NO MESMO ARQUIVO**, abaixo do template.
> Assim o dev olha em cima → vê o modelo.
> Olha embaixo → vê como preencher.

---

# 🟢 EXEMPLO DE PULL REQUEST PREENCHIDO

# 🔀 Pull Request — Implementação do endpoint `/predict` no backend

## 📝 Descrição do PR

Este PR implementa o endpoint `POST /predict` no backend.
Ele recebe os dados do voo, chama o microserviço FastAPI, retorna a previsão ao cliente e salva o log no banco.

Inclui:

* DTO `PredictRequest`
* DTO `PredictResponse`
* `PredictController`
* `PredictService`
* `PredictClient` (chamada HTTP ao microserviço)
* Validação dos campos envidos
* Registro do log da previsão

---

## 🔧 Tipo de mudança

* [x] `feat`
* [ ] `fix`
* [ ] `docs`
* [ ] `chore`
* [ ] `refactor`
* [ ] `test`
* [ ] `style`

---

## 🧪 Como testar este PR?

1. Rodar o microserviço:

```
uvicorn app:app --reload --port 8000
```

2. Rodar o backend:

```
mvn spring-boot:run
```

3. Testar no Postman:

```
POST http://localhost:8080/predict
```

Body:

```json
{
  "companhia": "AZ",
  "origem": "GIG",
  "destino": "GRU",
  "data_partida": "2025-11-10T14:30:00",
  "distancia_km": 350
}
```

4. Verificar resposta:

```json
{
  "previsao": "Atrasado",
  "probabilidade": 0.78
}
```

---

## 📸 Evidências

### Resposta:

```
{
  "previsao": "Atrasado",
  "probabilidade": 0.78
}
```

### Swagger funcionando:

*(print do Swagger aqui)*

### Log salvo no banco:

```
id=123, previsao=Atrasado, prob=0.78
```

---

## ✔ Checklist do Desenvolvedor

* [x] Branch: `feature/be-endpoint-predict`
* [x] Commits limpos
* [x] Testado localmente
* [x] Documentação atualizada
* [x] Tudo dentro do escopo da tarefa

---

## 🔍 Checklist do Revisor

* [ ] Código ok
* [ ] Resposta correta
* [ ] Sem warnings
* [ ] Branch limpa
* [ ] PR aprovado

---

## 👤 Responsável

Darlei

