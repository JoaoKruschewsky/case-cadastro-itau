# Cadastro de Pessoas — Full Stack

Sistema de cadastro de pessoas com geração automática de login, desenvolvido com **Spring Boot (Java 21)** no back-end e **Angular 15** no front-end.

---

## Tecnologias

| Camada     | Tecnologia                         |
|------------|------------------------------------|
| Back-end   | Java 21, Spring Boot, Gradle, H2   |
| Front-end  | Angular 15, TypeScript             |
| Banco      | H2 (arquivo local `./data/testdb`) |
| Container  | Docker + Docker Compose            |

---

## Pré-requisitos

Para rodar com Docker:
- Docker e Docker Compose instalados

Para rodar manualmente:
- Java 21 (JDK)
- Gradle (ou usar o wrapper `./gradlew`)
- Node 18+ e Angular CLI 15

---

## Executando com Docker (recomendado)

Na raiz do projeto (onde está o `docker-compose.yaml`):

```bash
# 1. Gerar o JAR do back-end
./gradlew bootJar

# 2. Subir todos os serviços
docker-compose up --build
```

Serviços disponíveis após subida:

| Serviço    | URL                              |
|------------|----------------------------------|
| Back-end   | http://localhost:8080            |
| Front-end  | http://localhost:4200            |
| H2 Console | http://localhost:8080/h2-console |

Credenciais H2 Console:
- JDBC URL: `jdbc:h2:file:./data/testdb`
- Usuário: `admin`
- Senha: `123`

---

## Executando manualmente (sem Docker)

### Back-end

```bash
./gradlew bootRun
```

O servidor sobe em `http://localhost:8080`.

### Front-end

```bash
cd case-front
npm install
ng serve
```

O front sobe em `http://localhost:4200`. O `proxy.conf.json` já redireciona `/manager-controller` para `http://localhost:8080`.

---

## Endpoints da API

### POST `/manager-controller/v1/manager/register`

Cadastra uma nova pessoa e retorna o login gerado automaticamente.

**Request body:**
```json
{
  "name": "Maria Silva Souza",
  "cpf": "52998224725",
  "email": "maria@email.com",
  "dataNascimento": "20-05-1990",
  "cep": "01001000",
  "endereco": {
    "logradouro": "Praca da Se",
    "bairro": "Se",
    "estado": "Sao Paulo",
    "uf": "SP"
  }
}
```

**Response (200):**
```json
{
  "message": "Login gerado com sucesso!",
  "data": {
    "loginResponse": "marsouz"
  }
}
```

**Erros possíveis:**
```json
{ "status": 401, "detail": "Email Cadastrado" }
{ "status": 401, "detail": "CPF cadastrado" }
{ "status": 401, "detail": "Data nao pode ser futura" }
{ "status": 500, "detail": "Ocorreu um erro ao tentar gerar o login" }
```

---

### GET `/manager-controller/v1/manager/login/{login}`

Busca os dados de uma pessoa pelo login gerado. Usado pela tela de login do front-end para redirecionar ao perfil.

**Exemplo:**
```
GET /manager-controller/v1/manager/login/marsouz
```

**Response (200):**
```json
{
  "message": "Usuario encontrado!",
  "data": {
    "name": "Maria Silva Souza",
    "login": "marsouz"
  }
}
```

**Erro:**
```json
{ "status": 401, "detail": "Usuario nao cadastrado" }
```

---

## Fluxo da aplicação

```
[Cadastro] → preenche formulário → POST /register → login gerado
          → modal exibe o login → após 10s → redireciona para /profile

[Login]    → informa o login → GET /login/{login} → recebe nome e login
          → redireciona para /profile exibindo nome e login
```

---

## Páginas do front-end

| Rota        | Componente  | Descrição                              |
|-------------|-------------|----------------------------------------|
| `/`         | Register    | Formulário de cadastro                 |
| `/register` | Register    | Formulário de cadastro                 |
| `/login`    | Login       | Campo para informar o login gerado     |
| `/profile`  | Profile     | Exibe nome e login após autenticação   |

---

## Rodando os testes

```bash
./gradlew test
```

Testes unitários em:
- `src/test/java/com/example/demo/application/service/ManagerUserImplTest.java`
- `src/test/java/com/example/demo/helpers/HelpersTest.java`

Relatório HTML após execução:
```
build/reports/tests/test/index.html
```

---

## Estrutura do projeto

```
case/
├── src/main/java/com/example/demo/
│   ├── adapter/
│   │   ├── configuration/        # InterceptorException, ServiceConfiguration
│   │   ├── dto/                  # RegisterUserRequest, ResponseLoginUser, ResponseUser, AddressDTO, RequestLogin, ApiResponseDTO
│   │   ├── in/                   # ManagerController, ApiResponseMapper
│   │   └── out/repository/       # H2RepositoryAdapter, UserRepository, UserMapper
│   ├── application/
│   │   ├── exception/            # ApiException, UserException, ValidationDataInputException
│   │   ├── service/              # ManagerUserImpl
│   │   └── usecase/              # LoginGeneratorUseCase, ValidationUseCase
│   └── domain/
│       ├── model/entity/         # User, Address
│       └── ports/                # ManagerUser (in), H2Manager (out)
├── case-front/src/app/
│   ├── pages/
│   │   ├── register/             # Formulário de cadastro
│   │   ├── login/                # Tela de login
│   │   └── profile/              # Tela de perfil (nome + login)
│   ├── models/                   # Response, RegisterUser, Address
│   └── shared/
│       ├── service/              # ApiService, cepService
│       └── utils/                # ValidatorsUtil
├── data/                         # Banco H2 persistido
├── Dockerfile                    # Imagem do back-end
├── docker-compose.yaml           # Orquestração (backend + frontend + h2)
└── build.gradle
```