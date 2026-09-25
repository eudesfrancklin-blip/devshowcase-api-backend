# DevShowcase API Backend

API REST desenvolvida com Java e Spring Boot para gerenciamento de perfis, projetos, tecnologias e avaliações.

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
* Avaliação de projetos com nota e comentário
* Cálculo da média das avaliações
* Sistema de upvote
* Paginação e filtro de projetos
* Validação de dados
* Tratamento global de exceções
* Documentação interativa com Swagger

## Relacionamentos

* Profile 1:N Project
* Project N:N Technology
* Project 1:N Feedback

## Endpoints

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

A documentação da API está disponível em:

https://devshowcase-api-backend.onrender.com/swagger-ui/index.html

## API em produção

https://devshowcase-api-backend.onrender.com

## Autores

Eudes Franklin Alves Lisboa

Maria Luiza Alves da Silva
