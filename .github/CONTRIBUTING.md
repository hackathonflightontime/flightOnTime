# ✈️ **CONTRIBUTING.md — Guia de Contribuição**

Obrigado por contribuir com o projeto **flight-on-time**!

Este documento define **todas as regras obrigatórias** para manter a qualidade do código, organização do time e fluxo de trabalho previsível.
Como somos um time com iniciantes, **seguir estas regras é obrigatório**.

---

# 🔷 1. Fluxo de Trabalho (Workflow)

Todo desenvolvimento segue este fluxo:

```
main → develop → feature/... → PR → develop → main
```

### 🔹 **main**

* código estável
* usado para deploy e apresentação

### 🔹 **develop**

* onde tudo é integrado
* PRs devem ser feitos **sempre para develop**

### 🔹 **NUNCA** faça commits direto em `main`.

### 🔹 **NUNCA** faça commits direto em `develop`.

---

# 🔷 2. Criando uma Branch

Sempre atualize o develop antes:

```bash
git checkout develop
git pull
```

Depois crie a branch:

```bash
git checkout -b <tipo>/<area>-<descricao>
```

## Tipos permitidos:

* `feature` → nova funcionalidade
* `fix` → correção de bug
* `chore` → tarefas técnicas (docker, config, build)
* `docs` → documentação

## Áreas (scopes):

* `be` → backend
* `ds` → data science
* `ms` → microservice
* `oci` → deploy / DevOps
* `docs` → documentação geral

## Exemplos válidos:

```
feature/be-endpoint-predict
fix/be-validacao-data
feature/ds-treino-modelo
chore/oci-docker-compose
docs/atualizar-readme-backend
```

---

# 🔷 3. Padrão de Commits

Usamos **Conventional Commits** simplificado.

### Formato:

```
<tipo>: descrição curta e objetiva
```

### Tipos permitidos:

* `feat:` nova funcionalidade
* `fix:` correção
* `docs:` documenctação
* `refactor:` refatoração sem mudança de comportamento
* `test:` adição/modificação de testes
* `chore:` mudanças de build, docker, configs
* `style:` ajustes de formatação

### Exemplos válidos:

```
feat: implementar endpoint /predict
fix: corrigir parse de data_partida
docs: adicionar explicação de deploy OCI
chore: configurar docker-compose com postgres
```

### Exemplos proibidos:

```
update
arrumei umas coisas
não sei
ajustes gerais
```

---

# 🔷 4. Regra de Ouro: Um commit = Uma mudança lógica

Não misture coisas diferentes no mesmo commit.

### Correto:

* implementou endpoint? → 1 commit
* corrigiu validação? → 1 commit
* atualizou README? → 1 commit

### Errado:

* implementou endpoint
* * configurou docker
* * criou teste
* * alterou variável
    → tudo num commit só

---

# 🔷 5. Pull Requests (PR)

Ao terminar sua tarefa:

```bash
git add .
git commit -m "feat: descrição"
git push -u origin <branch>
```

Depois abra um PR **da sua branch → develop**.

### O PR deve conter:

* o que foi feito
* por que foi feito
* como testar
* prints se necessário

### O PR **não é aceito** se:

* tiver commits ruins (“update”)
* branch estiver com nome errado
* alterar arquivos fora do escopo da tarefa
* quebrar o build

---

# 🔷 6. Revisão de Código (Code Review)

O PR sempre deve ser revisado por outra pessoa
(caso não haja disponível, o Tech Lead revisa).

O revisor verifica:

* clareza do código
* aderência aos padrões
* commits limpos
* sem duplicação
* sem código morto
* sem print statements desnecessários

O autor do PR deve corrigir tudo solicitado antes da aprovação.

---

# 🔷 7. Boas Práticas para Iniciantes

✔ atualize o develop antes de criar branch
✔ faça commits pequenos
✔ nomeie tudo corretamente
✔ escreva logs claros
✔ siga o formato dos diretórios
✔ teste antes de abrir PR
✔ pergunte quando travar

---

# 🔷 8. Proibido

* ❌ commitar direto em `main`
* ❌ misturar DS + BE na mesma branch
* ❌ usar nomes genéricos em commit
* ❌ abrir PR sem descrição
* ❌ empurrar código quebrado
* ❌ subir arquivos gigantes sem necessidade (datasets brutos)

---

# 🔷 9. Definition of Done (para cada tarefa)

Uma tarefa só está pronta quando:

* funciona localmente
* funciona no Docker
* está integrada com o módulo dependente
* está documentada
* tem testes básicos (quando aplicável)
* PR revisado e aprovado
* merge realizado

---

# ✈️ flight-on-time — Organização, clareza e consistência.

