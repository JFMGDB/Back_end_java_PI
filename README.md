# Projeto InclusiveAID - Backend

## Introdução
O InclusiveAID é um assistente de inclusão digital que visa proporcionar uma experiência mais acessível e inclusiva para pessoas com deficiência. Este backend fornece uma API RESTful robusta que suporta autenticação segura, gerenciamento de usuários, tipos de deficiência, adaptações, sessões, análises de layout, sugestões proativas, legendas em tempo real e comandos de voz.

## Visão Geral da Arquitetura
A API foi desenvolvida utilizando:
- Java 21
- Spring Boot 3
- Arquitetura em camadas (MVC)

### Principais Tecnologias
- Spring Data JPA - Persistência de dados
- Spring Security - Segurança e autenticação
- H2 Database - Banco de dados em memória
- Lombok - Redução de código boilerplate
- MapStruct - Mapeamento entre objetos
- SpringDoc/Swagger - Documentação da API

## Como Configurar e Executar o Projeto

### Pré-requisitos
- JDK 21
- Maven 3.6+

### Configuração
1. Clone o repositório:
```bash
git clone https://github.com/JFMGDB/Back_end_java_PI.git
cd Back_end_java_PI
```

2. Instale as dependências:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

### Acesso ao H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:file:./inclusiveaid_db
- Username: sa
- Password: (em branco)

## Estrutura de Diretórios
```
src/
├─ main/
│  ├── java/com/company/aid/
│  │  ├── config/             # Configurações (CORS, Security, Swagger)
│  │  ├── security/           # Implementação JWT e filtros
│  │  ├── entity/             # Entidades JPA
│  │  ├── repository/         # Interfaces JpaRepository
│  │  ├── dto/                # Data Transfer Objects
│  │  ├── mapper/             # Interfaces MapStruct
│  │  ├── service/            # Interfaces de serviço
│  │  ├── service/impl/       # Implementações dos serviços
│  │  ├── controller/         # Controladores REST
│  │  └── exception/          # Tratamento global de erros
│  └── resources/
│      ├── application.properties
│      ├── schema.sql
│      └── data.sql
└─ test/                      # Testes de contexto
```

## Autenticação e Segurança
A API utiliza autenticação via JWT (JSON Web Token).

### Obtenção do Token
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "admin@ex.com",
  "password": "senha123"
}
```

### Uso do Token
Para acessar rotas protegidas, inclua o header:
```
Authorization: Bearer <JWT>
```

## Documentação da API (Swagger)
- URL: http://localhost:8080/swagger-ui/index.html

Para testar endpoints protegidos:
1. Acesse a interface do Swagger
2. Clique em "Authorize"
3. Cole o token JWT no formato: `Bearer <JWT>`

## Endpoints da API

| Recurso | Método | Caminho | Descrição |
|---------|--------|---------|-----------|
| Auth | POST | /api/auth/login | Gera JWT a partir de email+senha |
| Users | GET | /api/users | Lista usuários (paginado) |
| | POST | /api/users | Cria novo usuário |
| | GET | /api/users/{id} | Busca usuário por ID |
| | PUT | /api/users/{id} | Atualiza usuário |
| | DELETE | /api/users/{id} | Remove usuário |
| DisabilityTypes | * | /api/disability-types/** | CRUD tipos de deficiência |
| Adaptations | * | /api/adaptations/** | CRUD adaptações |
| Feedbacks | * | /api/feedbacks/** | CRUD feedbacks |
| Sessions | * | /api/sessions/** | CRUD sessões |
| LayoutAnalyses | * | /api/layout-analyses/** | CRUD análises de layout |
| Suggestions | * | /api/suggestions/** | CRUD sugestões proativas |
| Subtitles | * | /api/subtitles/** | CRUD legendas em tempo real |
| VoiceCommands | * | /api/voice-commands/** | CRUD comandos de voz |

*Para cada recurso, o padrão CRUD é aplicado:
- GET / - Lista (com paginação)
- POST / - Cria
- GET /{id} - Busca
- PUT /{id} - Atualiza
- DELETE /{id} - Remove

## Integração com o Front-End

### Autenticação
1. O front-end deve primeiro obter um token JWT através do endpoint de login
2. Todas as requisições subsequentes para endpoints protegidos devem incluir o token no header Authorization

### CORS
- A API está configurada para aceitar requisições do front-end rodando em localhost:3000
- A configuração CORS está implementada na classe WebConfig.java

### Exemplo de Uso
```bash
# 1. Login e obtenção do token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"admin@ex.com","password":"senha123"}' \
  | jq -r .token)

# 2. Exemplo de requisição autenticada
curl -X GET http://localhost:8080/api/users?page=0&size=5 \
  -H "Authorization: Bearer $TOKEN"
```


