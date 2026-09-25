# DevShowcase API

API REST desenvolvida para o projeto **DevShowcase**, uma aplicação destinada à apresentação de perfis, projetos de software, tecnologias utilizadas e avaliações dos projetos.

O projeto foi desenvolvido com **Java 21 e Spring Boot**, utilizando **PostgreSQL** para persistência dos dados e uma arquitetura em camadas baseada em **Controller, Service e Repository**.

A API está disponível em produção utilizando **Render**.

## 🚀 Tecnologias

* Java 21
* Spring Boot 4.1.1
* Spring Web MVC
* Spring Data JPA
* Hibernate
* Bean Validation
* PostgreSQL
* Maven
* Docker
* Swagger / OpenAPI
* Render

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura em camadas:

```text
Cliente / Postman
       ↓
   Controller
       ↓
DTO + Validation
       ↓
     Service
       ↓
   Repository
       ↓
   PostgreSQL
```

Estrutura principal:

```text
src/main/java/com/devshowcase/devshowcaseapi
│
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
└── config
```

### Responsabilidades

**Controller**

Responsável pelos endpoints HTTP e pela comunicação com o cliente.

**Service**

Centraliza as regras de negócio, validações de integridade, cálculos e operações que envolvem mais de uma entidade.

**Repository**

Responsável pelo acesso e persistência dos dados utilizando Spring Data JPA.

**DTO**

Responsável pelo transporte dos dados entre a API e o cliente, evitando expor diretamente as entidades JPA.

**Entity**

Representa o modelo persistido no banco de dados.

**Exception**

Centraliza as exceções e o tratamento global dos erros da API.

---

## 🗃️ Modelo de domínio

O DevShowcase possui quatro entidades principais:

* `Profile`
* `Project`
* `Technology`
* `Feedback`

Relacionamentos:

```text
Profile 1 ───── N Project

Project N ───── N Technology

Project 1 ───── N Feedback
```

### Profile

Representa o perfil responsável pelos projetos.

Principais atributos:

* `id`
* `name`
* `bio`
* `githubUrl`
* `linkedinUrl`

### Project

Representa um projeto de software apresentado no DevShowcase.

Principais atributos:

* `id`
* `title`
* `description`
* `repositoryUrl`
* `demoUrl`
* `upvotes`
* `ratingAverage`
* `profile`
* `technologies`

### Technology

Representa uma tecnologia utilizada em um projeto.

Principais atributos:

* `id`
* `name`

O nome da tecnologia possui restrição de unicidade no banco de dados.

### Feedback

Representa uma avaliação realizada sobre um projeto.

Principais atributos:

* `id`
* `rating`
* `comment`
* `project`

A avaliação aceita notas de **1 a 5**.

---

## 🔌 Endpoints

### Profiles

#### Criar perfil

```http
POST /api/profiles
```

Exemplo:

```json
{
  "name": "Regian Leopoldo",
  "bio": "Estudante de Engenharia de Software e desenvolvedor.",
  "githubUrl": "https://github.com/RegianLeopoldo",
  "linkedinUrl": "https://www.linkedin.com/"
}
```

Retorno:

```http
201 Created
```

#### Buscar perfil

```http
GET /api/profiles/{id}
```

Retorno:

```http
200 OK
```

---

### Technologies

#### Criar tecnologia

```http
POST /api/technologies
```

Exemplo:

```json
{
  "name": "Java"
}
```

Retorno:

```http
201 Created
```

#### Listar tecnologias

```http
GET /api/technologies
```

Retorno:

```http
200 OK
```

---

### Projects

#### Criar projeto

```http
POST /api/projects
```

Exemplo:

```json
{
  "title": "DevShowcase API",
  "description": "API REST para apresentação de projetos de software.",
  "repositoryUrl": "https://github.com/RegianLeopoldo/devshowcase-api",
  "demoUrl": "https://devshowcase.example.com",
  "profileId": 1,
  "technologyIds": [1]
}
```

Retorno:

```http
201 Created
```

#### Listar projetos

```http
GET /api/projects
```

O endpoint possui paginação.

Parâmetros disponíveis:

```text
page
size
technologyId
```

Exemplo:

```http
GET /api/projects?page=0&size=10
```

Filtro por tecnologia:

```http
GET /api/projects?technologyId=1
```

Filtro e paginação:

```http
GET /api/projects?technologyId=1&page=0&size=10
```

#### Upvote em projeto

```http
PUT /api/projects/{id}/upvote
```

Cada chamada incrementa o número de upvotes do projeto.

Retorno:

```http
200 OK
```

---

### Feedbacks

#### Criar feedback

```http
POST /api/projects/{projectId}/feedbacks
```

Exemplo:

```json
{
  "rating": 5,
  "comment": "Excelente projeto!"
}
```

A nota deve estar entre `1` e `5`.

Retorno:

```http
201 Created
```

Após a criação de um feedback, a API recalcula a média das avaliações do projeto e atualiza o campo `ratingAverage`.

Exemplo:

```text
Feedback 1: 5
Feedback 2: 3

Média: 4.0
```

---

## ✅ Validação

A API utiliza **Bean Validation** para validar os dados recebidos.

Exemplos de validações:

* campos obrigatórios;
* nome não pode ser vazio;
* URLs devem possuir formato válido;
* nota deve estar entre 1 e 5;
* comentário possui limite de 500 caracteres.

Exemplo de requisição inválida:

```json
{
  "name": ""
}
```

Resposta:

```http
400 Bad Request
```

---

## ⚠️ Tratamento de erros

A aplicação possui um tratamento global de exceções utilizando `@RestControllerAdvice`.

Os principais erros tratados são:

* `400 Bad Request`
* `404 Not Found`
* `409 Conflict`

Exemplo de recurso não encontrado:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Profile não encontrado",
  "timestamp": "2026-09-06T06:06:30"
}
```

Exemplo de erro de validação:

```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "Dados inválidos",
  "errors": {
    "name": "Nome é obrigatório"
  }
}
```

A API evita expor stack traces ou detalhes internos da aplicação ao cliente.

---

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** com **Spring Data JPA e Hibernate**.

Tabelas principais:

```text
profiles
projects
technologies
project_technologies
feedbacks
```

O relacionamento `Project × Technology` é representado pela tabela intermediária:

```text
project_technologies
```

---

## ⚙️ Configuração local

### Pré-requisitos

* Java 21
* PostgreSQL
* Git
* Docker (opcional)
* IntelliJ IDEA ou outra IDE compatível

### Banco de dados

Crie um banco PostgreSQL chamado:

```text
devshowcase
```

### Variáveis de ambiente

A aplicação utiliza variáveis de ambiente para as credenciais do banco:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Exemplo:

```text
DB_URL=jdbc:postgresql://localhost:5432/devshowcase
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

As credenciais não devem ser versionadas no Git.

---

## ▶️ Executando localmente

Clone o repositório:

```bash
git clone https://github.com/RegianLeopoldo/devshowcase-api.git
```

Entre no projeto:

```bash
cd devshowcase-api
```

Execute utilizando o Maven Wrapper.

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será executada em:

```text
http://localhost:8080
```

---

## 🐳 Docker

O projeto possui um `Dockerfile` utilizando build multi-stage.

Para criar a imagem:

```bash
docker build -t devshowcase-api .
```

Executar o container:

```bash
docker run --name devshowcase-api -p 8080:8080 devshowcase-api
```

As configurações do banco devem ser fornecidas por variáveis de ambiente.

---

## 📚 Swagger / OpenAPI

A API possui documentação interativa utilizando **Swagger UI** e **OpenAPI**.

### Local

```text
http://localhost:8080/swagger-ui/index.html
```

### Produção

[Swagger UI da API em produção](https://devshowcase-api-vqpu.onrender.com/swagger-ui/index.html)

A especificação OpenAPI também está disponível em:

```text
/v3/api-docs
```

### OpenAPI em produção

[OpenAPI JSON](https://devshowcase-api-vqpu.onrender.com/v3/api-docs)

---

## ☁️ Deploy

A aplicação está publicada em produção utilizando:

* GitHub
* Docker
* Render Web Service
* Render PostgreSQL

Fluxo de deploy:

```text
GitHub
   ↓
Render
   ↓
Docker Build
   ↓
Spring Boot
   ↓
PostgreSQL
```

A aplicação utiliza variáveis de ambiente no ambiente de produção para armazenar as credenciais do banco.

### API em produção

https://devshowcase-api-vqpu.onrender.com

### Endpoint de exemplo

```http
GET https://devshowcase-api-vqpu.onrender.com/api/projects
```

---

## 🧪 Testes da API

Os endpoints podem ser testados utilizando:

* Postman
* Swagger UI

Os testes incluem:

* criação e consulta de perfis;
* criação e listagem de tecnologias;
* criação e listagem paginada de projetos;
* filtro de projetos por tecnologia;
* criação de feedbacks;
* cálculo da média das avaliações;
* incremento de upvotes;
* validação de dados;
* tratamento de recursos inexistentes.

---

## 🔐 Segurança e boas práticas

O projeto utiliza algumas práticas importantes para uma API REST:

* DTOs para entrada e saída de dados;
* validação dos dados recebidos;
* credenciais por variáveis de ambiente;
* restrições no banco de dados;
* tratamento global de exceções;
* separação de responsabilidades;
* transações nas operações de escrita;
* uso de JPA/Hibernate para persistência;
* `.gitignore` para evitar versionamento de arquivos sensíveis;
* Docker para empacotamento da aplicação.

---

## 📁 Estrutura do projeto

```text
devshowcase-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── devshowcase/
│   │   │           └── devshowcaseapi/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               │   ├── request/
│   │   │               │   └── response/
│   │   │               ├── entity/
│   │   │               ├── exception/
│   │   │               ├── repository/
│   │   │               └── service/
│   │   │
│   │   └── resources/
│   │       └── application.yml
│   │
│   └── test/
│
├── .gitignore
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🎓 Projeto acadêmico

Projeto desenvolvido para a disciplina **Programação Backend**, do curso de **Tecnologia em Sistemas para Internet**.

O projeto aborda conceitos de:

* modelagem de domínio;
* APIs REST;
* HTTP;
* arquitetura em camadas;
* DTOs;
* validação;
* persistência relacional;
* JPA/Hibernate;
* PostgreSQL;
* tratamento global de exceções;
* OpenAPI/Swagger;
* paginação e filtros;
* Docker;
* deploy em nuvem.

---

## 👨‍💻 Autor

**Regian Leopoldo**

GitHub:

https://github.com/RegianLeopoldo

Repositório:

https://github.com/RegianLeopoldo/devshowcase-api
