# InclusiveAID – Backend

## Visão Geral
O **InclusiveAID** é um assistente de inclusão digital que fornece recursos de acessibilidade para pessoas com deficiência. Este repositório contém uma API RESTful desenvolvida em **Java 21** com **Spring Boot 3**, organizada em camadas (MVC) e plenamente documentada com **OpenAPI/Swagger**.

Principais funcionalidades:
* Autenticação e autorização via **JWT**  
* Gerenciamento de usuários, papéis e permissões  
* CRUD de entidades relacionadas à acessibilidade (tipos de deficiência, adaptações, sugestões, comandos de voz, etc.)  
* Agentes de IA para geração de legendas, descrição de imagens, análise/adaptação de layout e automação de tarefas  
* Banco H2 embarcado com scripts de carga inicial  
* Boas práticas de segurança (CORS, cabeçalhos, rate-limit, filtros de logging)  

## Tecnologias Principais
* Java 21  
* Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security)  
* H2 Database  
* Lombok & MapStruct  
* SpringDoc-OpenAPI / Swagger UI  
* Maven

## Pré-requisitos
1. **JDK 21** ou superior  
2. **Maven 3.6+**

## Instalação
```bash
# clone o projeto
git clone <url-do-repositório>
cd Back_end_java_PI/inclusiveaid

# build
mvn clean package -DskipTests
```

## Execução
```bash
mvn spring-boot:run
```
A aplicação será iniciada em `http://localhost:8080`.

### Banco de Dados H2
* Console: `http://localhost:8080/h2-console`  
* JDBC URL: `jdbc:h2:mem:inclusiveaid_db`  
* Usuário: `sa` (sem senha)

Scripts de criação (`schema.sql`) e carga (`data.sql`) são executados automaticamente no primeiro start.

### Documentação Swagger
A documentação interativa pode ser acessada em:
`http://localhost:8080/swagger-ui.html`  *(ou* `/swagger-ui/index.html` *)*

## Autenticação JWT
1. Realize login para obter o token:
   ```http
   POST /api/auth/login
   Content-Type: application/json

   {
     "email": "admin@ex.com",
     "password": "senha123"
   }
   ```
2. Copie o valor retornado em `token` e envie nas próximas requisições:
   ```text
   Authorization: Bearer <JWT>
   ```

## Estrutura de Diretórios
```
src/
├─ main/
│  ├─ java/grupo05/inclusiveaid/
│  │  ├─ config/          # Configurações gerais e de segurança
│  │  ├─ security/        # Filtro e utilidades JWT
│  │  ├─ controller/      # REST Controllers
│  │  ├─ service/ (+impl) # Camada de negócio
│  │  ├─ repository/      # Interfaces JPA
│  │  ├─ entity/          # Entidades
│  │  ├─ dto/             # Data Transfer Objects
│  │  ├─ mapper/          # MapStruct mappers
│  │  └─ exception/       # Tratamento global de erros
│  └─ resources/
│     ├─ application.properties
│     ├─ schema.sql
│     └─ data.sql
└─ test/                  # Testes (contexto)
```

## Endpoints Principais
| Recurso | Método | Caminho | Descrição |
|---------|--------|---------|-----------|
| Auth | POST | `/api/auth/login` | Autentica usuário e gera JWT |
| Usuários | GET | `/api/usuarios` | Lista usuários (pag.) |
| Usuários | POST | `/api/usuarios` | Cria usuário |
| Usuários | GET | `/api/usuarios/{id}` | Busca usuário por ID |
| Usuários | PUT | `/api/usuarios/{id}` | Atualiza usuário |
| Usuários | DELETE | `/api/usuarios/{id}` | Remove usuário |
| Papéis (Roles) | GET | `/api/roles` | Lista papéis |
| Papéis (Roles) | POST | `/api/roles` | Cria papel |
| Papéis (Roles) | GET | `/api/roles/{id}` | Busca papel |
| Papéis (Roles) | PUT | `/api/roles/{id}` | Atualiza papel |
| Papéis (Roles) | DELETE | `/api/roles/{id}` | Remove papel |
| Permissões | GET | `/api/permissions` | Lista permissões |
| Permissões | POST | `/api/permissions` | Cria permissão |
| Permissões | GET | `/api/permissions/{id}` | Busca permissão |
| Permissões | PUT | `/api/permissions/{id}` | Atualiza permissão |
| Permissões | DELETE | `/api/permissions/{id}` | Remove permissão |
| Tipos de Deficiência | GET | `/api/disability-types` | Lista tipos |
| Tipos de Deficiência | POST | `/api/disability-types` | Cria tipo |
| Tipos de Deficiência | GET | `/api/disability-types/{id}` | Busca tipo |
| Tipos de Deficiência | PUT | `/api/disability-types/{id}` | Atualiza tipo |
| Tipos de Deficiência | DELETE | `/api/disability-types/{id}` | Remove tipo |
| Adaptações | GET | `/api/adaptations` | Lista adaptações |
| Adaptações | POST | `/api/adaptations` | Cria adaptação |
| Adaptações | GET | `/api/adaptations/{id}` | Busca adaptação |
| Adaptações | PUT | `/api/adaptations/{id}` | Atualiza adaptação |
| Adaptações | DELETE | `/api/adaptations/{id}` | Remove adaptação |
| Config. Adaptação Usuário | GET | `/api/user-adaptation-settings` | Lista configs |
| Config. Adaptação Usuário | POST | `/api/user-adaptation-settings` | Cria config |
| Config. Adaptação Usuário | GET | `/api/user-adaptation-settings/{id}` | Busca config |
| Config. Adaptação Usuário | PUT | `/api/user-adaptation-settings/{id}` | Atualiza config |
| Config. Adaptação Usuário | DELETE | `/api/user-adaptation-settings/{id}` | Remove config |
| Elementos de Layout | GET | `/api/layout-elements` | Lista elementos |
| Elementos de Layout | POST | `/api/layout-elements` | Cria elemento |
| Elementos de Layout | GET | `/api/layout-elements/{id}` | Busca elemento |
| Elementos de Layout | PUT | `/api/layout-elements/{id}` | Atualiza elemento |
| Elementos de Layout | DELETE | `/api/layout-elements/{id}` | Remove elemento |
| Análises de Layout | GET | `/api/layout-analysis` | Lista análises |
| Análises de Layout | POST | `/api/layout-analysis` | Cria análise |
| Análises de Layout | GET | `/api/layout-analysis/{id}` | Busca análise |
| Análises de Layout | DELETE | `/api/layout-analysis/{id}` | Remove análise |
| Sessões | GET | `/api/sessions` | Lista sessões |
| Sessões | POST | `/api/sessions` | Cria sessão |
| Sessões | GET | `/api/sessions/{id}` | Busca sessão |
| Sessões | DELETE | `/api/sessions/{id}` | Encerra sessão |
| Feedback | GET | `/api/feedback` | Lista feedbacks |
| Feedback | POST | `/api/feedback` | Cria feedback |
| Feedback | GET | `/api/feedback/{id}` | Busca feedback |
| Feedback | DELETE | `/api/feedback/{id}` | Remove feedback |
| Sugestões | GET | `/api/suggestions` | Lista sugestões |
| Sugestões | POST | `/api/suggestions` | Cria sugestão |
| Sugestões | GET | `/api/suggestions/{id}` | Busca sugestão |
| Sugestões | PUT | `/api/suggestions/{id}` | Atualiza sugestão |
| Sugestões | DELETE | `/api/suggestions/{id}` | Remove sugestão |
| Legendas | GET | `/api/subtitles` | Lista legendas |
| Legendas | POST | `/api/subtitles` | Cria legenda |
| Legendas | GET | `/api/subtitles/{id}` | Busca legenda |
| Legendas | PUT | `/api/subtitles/{id}` | Atualiza legenda |
| Legendas | DELETE | `/api/subtitles/{id}` | Remove legenda |
| Traduções LIBRAS | GET | `/api/libras-translations` | Lista traduções |
| Traduções LIBRAS | POST | `/api/libras-translations` | Cria tradução |
| Traduções LIBRAS | GET | `/api/libras-translations/{id}` | Busca tradução |
| Traduções LIBRAS | PUT | `/api/libras-translations/{id}` | Atualiza tradução |
| Traduções LIBRAS | DELETE | `/api/libras-translations/{id}` | Remove tradução |
| Comandos de Voz | GET | `/api/voice-commands` | Lista comandos |
| Comandos de Voz | POST | `/api/voice-commands` | Cria comando |
| Comandos de Voz | GET | `/api/voice-commands/{id}` | Busca comando |
| Comandos de Voz | PUT | `/api/voice-commands/{id}` | Atualiza comando |
| Comandos de Voz | DELETE | `/api/voice-commands/{id}` | Remove comando |
| Tarefas | GET | `/api/tarefas` | Lista tarefas |
| Tarefas | POST | `/api/tarefas` | Cria tarefa |
| Tarefas | GET | `/api/tarefas/{id}` | Busca tarefa |
| Tarefas | PUT | `/api/tarefas/{id}` | Atualiza tarefa |
| Tarefas | DELETE | `/api/tarefas/{id}` | Remove tarefa |
| Responsáveis | GET | `/api/responsible` | Lista responsáveis |
| Responsáveis | POST | `/api/responsible` | Cria responsável |
| Responsáveis | GET | `/api/responsible/{id}` | Busca responsável |
| Responsáveis | PUT | `/api/responsible/{id}` | Atualiza responsável |
| Responsáveis | DELETE | `/api/responsible/{id}` | Remove responsável |
| Interações de Agente | GET | `/api/agent-interactions` | Lista interações |
| Interações de Agente | POST | `/api/agent-interactions` | Cria interação |
| Interações de Agente | GET | `/api/agent-interactions/{id}` | Busca interação |
| Interações de Agente | PUT | `/api/agent-interactions/{id}` | Atualiza interação |
| Interações de Agente | DELETE | `/api/agent-interactions/{id}` | Remove interação |
| Agentes de IA | GET | `/api/ai-agents` | Lista agentes |
| Agentes de IA | POST | `/api/ai-agents` | Cria agente |
| Agentes de IA | GET | `/api/ai-agents/{id}` | Busca agente |
| Agentes de IA | PUT | `/api/ai-agents/{id}` | Atualiza agente |
| Agentes de IA | DELETE | `/api/ai-agents/{id}` | Remove agente |
| Agentes de IA | GET | `/api/ai-agents/active` | Lista agentes ativos |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/voice-command` | Processa comando de voz |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/analyze-layout` | Analisa layout |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/adapt-layout` | Adapta layout |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/generate-subtitle` | Gera legendas |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/describe-image` | Descreve imagem |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/users/{userId}/automate-task` | Automatiza tarefa |
| Agentes de IA | PUT | `/api/ai-agents/{agentId}/model` | Atualiza modelo IA |
| Agentes de IA | POST | `/api/ai-agents/{agentId}/train` | Treina modelo IA |

> Todos os endpoints protegidos requerem o header `Authorization: Bearer <token>`.

## Paginação
Listagens aceitam os parâmetros query `page` (0-based) e `size` (default 10).

## Integração com Front-End
1. Execute o login e armazene o JWT em *local storage* ou *cookie seguro*.
2. Configure um *interceptor* para adicionar o header `Authorization` a cada chamada.
3. Use as rotas públicas (`/api/auth/**`) sem token.
4. Utilize a documentação Swagger para testar e descobrir novos endpoints.

## Boas Práticas & Convenções
* Utilize DTOs definidos no projeto para comunicação entre camadas.  
* Validações são feitas via *Bean Validation* – campos inválidos retornam **HTTP 400**.  
* Respostas de erro seguem o padrão JSON com `timestamp`, `status`, `error` e `message`.  
* Os mapeamentos seguem a convenção REST (`/api/<recurso>`).  
* Ao contribuir, siga o guia de estilo do **Google Java Format**.

## Testes Automatizados
A suíte de testes JUnit pode ser executada via:
```bash
mvn clean test
```

Caso ainda não existam testes para um módulo, utilize o template em `src/test/java/grupo05/inclusiveaid` e crie casos para serviços e controladores.

## Apresentação
A apresentação do projeto (slides) está disponível em:
https://example.com/apresentacao-inclusiveaid

## Novo recurso – Configurações específicas de deficiência
| Recurso | Método | Caminho |
|---------|--------|---------|
| Config. Espec. Deficiência | GET | `/api/disability-specific-configs` |
| Config. Espec. Deficiência | POST | `/api/disability-specific-configs` |
| Config. Espec. Deficiência | GET | `/api/disability-specific-configs/{id}` |
| Config. Espec. Deficiência | PUT | `/api/disability-specific-configs/{id}` |
| Config. Espec. Deficiência | DELETE | `/api/disability-specific-configs/{id}` |


