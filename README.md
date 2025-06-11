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
| Auth | POST | /api/auth/register | Registra novo usuário |
| Auth | POST | /api/auth/refresh-token | Renova token JWT |
| AIAgent | GET | /api/ai-agents | Lista todos os agentes de IA |
| AIAgent | POST | /api/ai-agents | Cria novo agente de IA |
| AIAgent | GET | /api/ai-agents/{id} | Busca agente por ID |
| AIAgent | PUT | /api/ai-agents/{id} | Atualiza agente |
| AIAgent | DELETE | /api/ai-agents/{id} | Remove agente |
| AIAgent | POST | /api/ai-agents/{agentId}/users/{userId}/voice-command | Processa comando de voz do usuário |
| AIAgent | POST | /api/ai-agents/{agentId}/users/{userId}/analyze-layout | Analisa layout para o usuário |
| AIAgent | POST | /api/ai-agents/{agentId}/users/{userId}/suggest | Gera sugestões proativas |
| AIAgent | POST | /api/ai-agents/{agentId}/users/{userId}/translate-libras | Traduz conteúdo para Libras |
| Users | GET | /api/users | Lista usuários (paginado) |
| Users | POST | /api/users | Cria novo usuário |
| Users | GET | /api/users/{id} | Busca usuário por ID |
| Users | PUT | /api/users/{id} | Atualiza usuário |
| Users | DELETE | /api/users/{id} | Remove usuário |
| Users | GET | /api/users/{id}/sessions | Lista sessões do usuário |
| Users | GET | /api/users/{id}/adaptations | Lista adaptações do usuário |
| Responsible | GET | /api/responsibles | Lista responsáveis |
| Responsible | POST | /api/responsibles | Cria novo responsável |
| Responsible | GET | /api/responsibles/{id} | Busca responsável por ID |
| Responsible | PUT | /api/responsibles/{id} | Atualiza responsável |
| Responsible | DELETE | /api/responsibles/{id} | Remove responsável |
| Task | GET | /api/tasks | Lista tarefas |
| Task | POST | /api/tasks | Cria nova tarefa |
| Task | GET | /api/tasks/{id} | Busca tarefa por ID |
| Task | PUT | /api/tasks/{id} | Atualiza tarefa |
| Task | DELETE | /api/tasks/{id} | Remove tarefa |
| Permissions | GET | /api/permissions | Lista permissões |
| Permissions | POST | /api/permissions | Cria nova permissão |
| Permissions | GET | /api/permissions/{id} | Busca permissão por ID |
| Permissions | PUT | /api/permissions/{id} | Atualiza permissão |
| Permissions | DELETE | /api/permissions/{id} | Remove permissão |
| DisabilityTypes | GET | /api/disability-types | Lista tipos de deficiência |
| DisabilityTypes | POST | /api/disability-types | Cria novo tipo |
| DisabilityTypes | GET | /api/disability-types/{id} | Busca tipo por ID |
| DisabilityTypes | PUT | /api/disability-types/{id} | Atualiza tipo |
| DisabilityTypes | DELETE | /api/disability-types/{id} | Remove tipo |
| Adaptations | GET | /api/adaptations | Lista adaptações |
| Adaptations | POST | /api/adaptations | Cria nova adaptação |
| Adaptations | GET | /api/adaptations/{id} | Busca adaptação por ID |
| Adaptations | PUT | /api/adaptations/{id} | Atualiza adaptação |
| Adaptations | DELETE | /api/adaptations/{id} | Remove adaptação |
| LibrasTranslation | GET | /api/libras-translations | Lista traduções |
| LibrasTranslation | POST | /api/libras-translations | Cria nova tradução |
| LibrasTranslation | GET | /api/libras-translations/{id} | Busca tradução por ID |
| LibrasTranslation | PUT | /api/libras-translations/{id} | Atualiza tradução |
| LibrasTranslation | DELETE | /api/libras-translations/{id} | Remove tradução |
| Sessions | GET | /api/sessions | Lista sessões |
| Sessions | POST | /api/sessions | Cria nova sessão |
| Sessions | GET | /api/sessions/{id} | Busca sessão por ID |
| Sessions | PUT | /api/sessions/{id} | Atualiza sessão |
| Sessions | DELETE | /api/sessions/{id} | Remove sessão |
| LayoutAnalyses | GET | /api/layout-analyses | Lista análises |
| LayoutAnalyses | POST | /api/layout-analyses | Cria nova análise |
| LayoutAnalyses | GET | /api/layout-analyses/{id} | Busca análise por ID |
| LayoutAnalyses | PUT | /api/layout-analyses/{id} | Atualiza análise |
| LayoutAnalyses | DELETE | /api/layout-analyses/{id} | Remove análise |
| Suggestions | GET | /api/suggestions | Lista sugestões |
| Suggestions | POST | /api/suggestions | Cria nova sugestão |
| Suggestions | GET | /api/suggestions/{id} | Busca sugestão por ID |
| Suggestions | PUT | /api/suggestions/{id} | Atualiza sugestão |
| Suggestions | DELETE | /api/suggestions/{id} | Remove sugestão |
| Subtitles | GET | /api/subtitles | Lista legendas |
| Subtitles | POST | /api/subtitles | Cria nova legenda |
| Subtitles | GET | /api/subtitles/{id} | Busca legenda por ID |
| Subtitles | PUT | /api/subtitles/{id} | Atualiza legenda |
| Subtitles | DELETE | /api/subtitles/{id} | Remove legenda |
| VoiceCommands | GET | /api/voice-commands | Lista comandos |
| VoiceCommands | POST | /api/voice-commands | Cria novo comando |
| VoiceCommands | GET | /api/voice-commands/{id} | Busca comando por ID |
| VoiceCommands | PUT | /api/voice-commands/{id} | Atualiza comando |
| VoiceCommands | DELETE | /api/voice-commands/{id} | Remove comando |
| Feedbacks | GET | /api/feedbacks | Lista feedbacks |
| Feedbacks | POST | /api/feedbacks | Cria novo feedback |
| Feedbacks | GET | /api/feedbacks/{id} | Busca feedback por ID |
| Feedbacks | PUT | /api/feedbacks/{id} | Atualiza feedback |
| Feedbacks | DELETE | /api/feedbacks/{id} | Remove feedback |

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


