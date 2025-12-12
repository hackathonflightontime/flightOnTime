# 🐛 ISSUE TEMPLATE — flight-on-time

Use este template para abrir qualquer issue no projeto.

---

## 🔖 Tipo da Issue

Marque uma opção:

* [ ] Bug
* [ ] Nova funcionalidade
* [ ] Documentação
* [ ] Tarefa técnica (Docker, CI, configs)
* [ ] Refatoração
* [ ] Melhoria
* [ ] Discussão / dúvida

---

## 📝 Descrição

Explique claramente **o que precisa ser feito**, qual o contexto e por quê.

> Exemplo:
> “Implementar o endpoint `/predict` que envia os dados ao microserviço Python e retorna previsão + probabilidade.”

---

## 📌 Passos para Reproduzir (se for bug)

1.
2.
3.

---

## 📸 Evidências (logs, prints, mensagens)

Cole aqui:

```
<log, erro ou print>
```

---

## 🎯 Critério de Aceitação (Definition of Done)

A issue será considerada concluída quando:

* [ ] Funcionar localmente
* [ ] Funcionar no Docker
* [ ] Funcionar integrado ao backend/microserviço
* [ ] Estiver documentado
* [ ] Passar pelo Code Review
* [ ] PR aprovado e mergeado

---

## 👤 Responsável

Quem vai fazer essa tarefa?

---

## ⏳ Estimativa de esforço

* [ ] 1h
* [ ] 2h
* [ ] 4h
* [ ] 8h
* [ ] > 1 dia

---

## 🔗 Dependências

Liste outras tarefas/PRs que precisam estar prontas antes desta.

---

# ————————————

Agora o time não abre mais issue de qualquer jeito.

---

# 🔗 **2. PULL_REQUEST_TEMPLATE.md**

Cole o conteúdo em:

📄 `.github/PULL_REQUEST_TEMPLATE.md`

---

# 🔀 PULL REQUEST — flight-on-time

Obrigado por abrir um PR!
Preencha tudo para facilitar a revisão.

---

## 📝 Descrição do PR

Explique **claramente** o que este PR faz e por quê.

> Exemplo:
> “Implementa o endpoint `/predict`, cria o DTO de entrada e conecta ao microserviço FastAPI.”

---

## 🔧 Tipo de mudança

Marque uma opção:

* [ ] `feat` — Nova funcionalidade
* [ ] `fix` — Correção de bug
* [ ] `docs` — Documentação
* [ ] `chore` — Configuração / Infra / Docker
* [ ] `refactor` — Refatoração
* [ ] `test` — Testes
* [ ] `style` — Ajustes visuais/código

---

## 🧪 Como testar este PR?

Explique o passo a passo:

```
1. Rodar o backend com `mvn spring-boot:run`
2. Enviar POST para /predict com o body X
3. Verificar resposta
```

Se for DS:

```
1. Rodar microserviço com uvicorn
2. Chamar /predict-model
3. Verificar probabilidade retornada
```

---

## 📸 Evidências (prints, logs, payloads)

Inclua prints, logs, resultados ou evidências importantes.

---

## ✔ Checklist do Desenvolvedor

Antes de pedir revisão, confirme:

* [ ] O PR segue o padrão de branch
* [ ] Commits estão limpos e com nomes corretos
* [ ] Testei localmente
* [ ] Testei no Docker
* [ ] Não quebrei nenhum módulo
* [ ] Não inclui arquivos desnecessários
* [ ] Atualizei documentação se necessário

---

## 🔍 Checklist do Revisor

* [ ] Código limpo
* [ ] Sem duplicação
* [ ] Sem warnings críticos
* [ ] Funcionalidade clara
* [ ] Requisição/Resposta coerente
* [ ] Documentação atualizada
* [ ] PR pequeno e objetivo
* [ ] Tudo ok para merge

---

## 🧑‍💼 Responsável

Quem desenvolveu este PR:

---

