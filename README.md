# DevShowcase API Backend

API REST desenvolvida em Java com Spring Boot para gerenciamento de perfis, projetos, tecnologias e avaliações.

## Tecnologias

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger / OpenAPI

## Funcionalidades

* Cadastro e consulta de perfis
* Cadastro e consulta de tecnologias
* Cadastro e consulta de projetos
* Avaliação de projetos
* Sistema de upvote
* Paginação e filtros
* Validação de dados
* Tratamento global de exceções
* Documentação da API com Swagger

## Relacionamentos

* Profile 1:N Project
* Project N:N Technology
* Project 1:N Feedback

## Principais endpoints

```text
POST   /api/profiles
GET    /api/profiles/{id}

POST   /api/technologies
GET    /api/technologies

POST   /api/projects
GET    /api/projects

POST   /api/projects/{projectId}/feedbacks
PUT    /api/projects/{id}/upvote
```

## Swagger

Após executar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

## Execução local

Configure as variáveis de ambiente:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Depois execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Produção

**API:** []

**Swagger:** []

## Autores

Eudes Franklin Alves Lisboa
Maria Luiza Alves da Silva
