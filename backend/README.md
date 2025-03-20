# Roadmap4IT

## 🚀 Executando o projeto localmente com Gradle

### 📋 Pré-requisitos

Certifique-se de ter os seguintes requisitos instalados:

- [Java 17+](https://adoptium.net/)
- [Gradle 8+](https://gradle.org/install/)
- [PostgreSQL](https://www.postgresql.org/download/) (ou utilize Docker)

### ⚙️ Configuração do Banco de Dados

Se estiver rodando localmente, crie um banco de dados PostgreSQL:

1. Acesse o PostgreSQL:
   ```sh
   psql -U postgres
   ```
2. Crie o banco de dados:
   ```sql
   CREATE DATABASE roadmap4it;
   ```
3. (Opcional) Altere as configurações no `application.properties` ou `application.yml` caso necessário.

#### Usando Docker (alternativa mais simples)

Se preferir usar Docker, execute:

```sh
docker-compose up -d
```

### ▶️ Rodando o projeto

Com o Gradle instalado, execute o seguinte comando no terminal:

```sh
./gradlew bootRun
```

(Para Windows, use `gradlew.bat bootRun`)

Isso iniciará o servidor Spring Boot na porta 3000 por padrão.

### 🛠️ Construindo o projeto

Para compilar o projeto e gerar um `.jar`, execute:

```sh
./gradlew build
```

O arquivo será gerado em `build/libs/`.

### 🧪 Executando os testes

Para rodar os testes automatizados:

```sh
./gradlew test
```

### 🔗 Endpoints disponíveis

Após iniciar o servidor, acesse:

- **Swagger UI:** `http://localhost:3000/swagger-ui.html` (ainda nao configurado)
- **API Base URL:** `http://localhost:3000/courses`

---