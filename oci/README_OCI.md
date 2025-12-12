# ☁️ **README_OCI.md — Deploy na Oracle Cloud (OCI)**

Guia oficial para execução do projeto **flight-on-time** em ambiente real usando **Oracle Cloud Infrastructure (OCI)**.

O objetivo é subir:

* Microserviço FastAPI (modelo ML)
* Backend Spring Boot
* Banco PostgreSQL
* Tudo rodando via **Docker Compose**
* Em uma **Compute Instance (Always Free)**

---

# 🎯 1. Conceito Geral do Deploy

Arquitetura final na OCI:

```
Usuário → Backend Java (8080)
                  ↓ HTTP
       Microserviço FastAPI (8000)
                  ↓ 
               PostgreSQL (5432)
```

Todos os serviços rodam na **mesma VM**, isolados por containers Docker.

---

# 🧱 2. Criar a Máquina Virtual (Compute Instance)

1. Acesse o painel OCI
2. Vá em **Compute → Instances → Create**
3. Configurações recomendadas:

| Opção   | Valor                                |
| ------- | ------------------------------------ |
| Shape   | VM.Standard.E2.1.Micro (Always Free) |
| Sistema | Ubuntu 22.04                         |
| Network | VCN padrão                           |
| SSH Key | gerar nova ou usar existente         |

4. Criar a instância
5. Copie o **IP Público**

---

# 💻 3. Conectar na VM via SSH

No terminal local:

```bash
ssh -i ~/.ssh/id_rsa ubuntu@IP_DA_VM
```

---

# 🐳 4. Instalar Docker na VM

```bash
sudo apt update
sudo apt install -y docker.io
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -aG docker ubuntu
```

⚠️ **Faça logout e login novamente na VM** para ativar as permissões.

---

# 🐳 5. Instalar Docker Compose

```bash
sudo apt install -y docker-compose
```

Verificar:

```bash
docker-compose --version
```

---

# 📦 6. Preparar Estrutura do Projeto na VM

Na VM:

```bash
mkdir flight-on-time
cd flight-on-time
```

Depois subir arquivos via SCP ou Git:

### 📌 Método recomendado:

Usar **GitHub** → clonar na VM:

```bash
git clone https://github.com/SEU_USUARIO/flight-on-time.git
```

---

# 🧩 7. Criar o arquivo `docker-compose.yml` (virá pronto na próxima fase)

Exemplo da estrutura final:

```yaml
version: "3.8"

services:
  postgres:
    image: postgres:15-alpine
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin123
      POSTGRES_DB: flighton
    restart: always
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

  microservice:
    build: ./datascience/service
    ports:
      - "8000:8000"
    depends_on:
      - postgres

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    depends_on:
      - microservice
      - postgres

volumes:
  pgdata:
```

A versão completa será criada após finalizarmos todos os READMEs.

---

# 🔌 8. Liberar portas no firewall da OCI

Ir em:

**Networking → VCN → Subnets → Security Lists → Ingress Rules**

Adicionar:

| Porta | Serviço                                       |
| ----- | --------------------------------------------- |
| 8080  | Backend                                       |
| 8000  | FastAPI                                       |
| 5432  | Postgres (opcional, ideal manter restringido) |

Regra:

```
Source CIDR: 0.0.0.0/0
Protocol: TCP
Destination Port: <porta>
```

---

# ▶️ 9. Rodar o projeto na OCI

No diretório raiz:

```bash
docker-compose up -d
```

Verificar containers:

```bash
docker ps
```

---

# 🌍 10. Acessos Externos

Backend (API Java):

```
http://IP_PUBLICO:8080/swagger-ui.html
```

Microserviço FastAPI:

```
http://IP_PUBLICO:8000/docs
```

---

# 💡 11. Restart após alterações

```bash
docker-compose down
docker-compose up -d --build
```

---

# 🚨 12. Problemas Comuns & Soluções

### ❌ Backend não encontra microserviço

* Verifique se usa URL: `http://microservice:8000` dentro do container
* Verifique `depends_on:` no docker-compose

---

### ❌ Microserviço não sobe

* Verifique se o modelo `.joblib` está no local correto
* Reinstale dependências:

```
docker-compose build microservice
```

---

### ❌ Banco não conecta

Use a URL correta:

```
jdbc:postgresql://postgres:5432/flighton
```

---

# ✔️ 13. Checklist Final de Produção

* [ ] VM criada
* [ ] Docker instalado
* [ ] Docker Compose instalado
* [ ] Projeto clonado
* [ ] docker-compose.yml configurado
* [ ] Portas abertas
* [ ] Backend funcionando
* [ ] Microserviço funcionando
* [ ] Banco conectado

---

# 🙌 Responsável pelo Deploy

Darlei flight-on-time.