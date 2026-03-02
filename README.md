# Sistema Jurídico API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-green)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

API RESTful para acompanhamento de processos jurídicos, desenvolvida como um projeto de estudo para aplicar conceitos de backend com Java e Spring Framework.

## 🎯 Objetivo do Projeto

O objetivo desta API é fornecer um backend robusto para uma aplicação (web ou mobile) focada em advogados e seus clientes. Ela permite a centralização, controle e consulta de informações sobre processos judiciais, garantindo que os dados sejam acessados e manipulados de forma segura, eficiente e em linguagem não jurídica para fácil entendimento.

## Funcionalidades Implementadas

- ✅ **CRUD de Processos**: API completa para Criar, Ler, Atualizar e Deletar processos.
- ✅ **Busca Avançada**: Capacidade de buscar processos associados a um cliente específico através do CPF.
- ✅ **Segurança Baseada em Proprietário**: Implementação de regras de negócio onde um advogado só pode visualizar, alterar ou excluir os processos que lhe pertencem.
- ✅ **Gerenciamento de Movimentações**: Endpoints para listar e adicionar novas movimentações a um processo existente, respeitando as regras de propriedade do processo.

## Endpoints da API

Abaixo está a lista de endpoints disponíveis e como utilizá-los.

### 1. Processos (`/processos`)

| Funcionalidade      | Método HTTP | Endpoint                  | Descrição                                                    |
| :------------------ | :---------: | :------------------------ | :----------------------------------------------------------- |
| Cadastrar Processo  | `POST`      | `/processos`              | Cria um novo processo associado ao advogado autenticado.     |
| Buscar por ID       | `GET`       | `/processos/{id}`         | Busca um processo específico. Apenas o advogado dono tem acesso. |
| Buscar por Cliente  | `GET`       | `/processos/cliente/{cpf}`  | Lista todos os processos de um cliente pelo CPF.             |
| Atualizar Processo  | `PUT`       | `/processos/{id}`         | Atualiza um processo. Apenas o advogado dono tem acesso.     |
| Deletar Processo    | `DELETE`    | `/processos/{id}`         | Deleta um processo. Apenas o advogado dono tem acesso.       |

### 2. Movimentações (`/movimentacoes`)

| Funcionalidade        | Método HTTP | Endpoint                         | Descrição                                                         |
| :-------------------- | :---------: | :------------------------------- | :---------------------------------------------------------------- |
| Listar Movimentações  | `GET`       | `/movimentacoes/processo/{id}` | Lista todas as movimentações de um processo específico.           |
| Adicionar Movimentação| `POST`      | `/movimentacoes/processo/{id}` | Adiciona uma nova movimentação a um processo. Apenas o advogado dono do processo pode adicionar. |

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Framework Principal:** [Spring Boot 3.3.1](https://spring.io/projects/spring-boot)
- **Módulos Spring:**
    - `Spring Web`: Para criação de endpoints RESTful.
    - `Spring Data JPA`: Para persistência de dados e comunicação com o banco.
    - `Spring Security`: Para a camada de segurança e regras de acesso.
    - `Validation`: Para validações dos dados de entrada.
- **Banco de Dados:** [H2 Database](https://www.h2database.com/html/main.html) (Banco de dados em memória para ambiente de desenvolvimento).
- **Gerenciador de Dependências:** [Maven](https://maven.apache.org/)
- **Utilitários:** [Lombok](https://projectlombok.org/) para redução de código boilerplate.

## 🚀 Como Rodar o Projeto

Você não precisa ter o Maven instalado em sua máquina, pois o projeto utiliza o Maven Wrapper.

**Pré-requisitos:**
- JDK 17 ou superior.

**Passos:**

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Andrea083/TG2026-CRUD1]
    ```

2.  **Navegue até a pasta do projeto:**
    ```bash
    cd sistema-juridico-api
    ```

3.  **Execute o projeto:**
    - No Linux ou macOS:
      ```bash
      ./mvnw spring-boot:run
      ```
    - No Windows:
      ```bash
      mvnw.cmd spring-boot:run
      ```

A API estará rodando em `http://localhost:8080`.

## 🧪 Como Testar a API

Você pode usar ferramentas como o [Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/) ou simplesmente o comando `curl` no seu terminal.

### 1. Criar um Novo Processo

- **Rota:** `POST /processos`
- **Descrição:** Cria um novo registro de processo jurídico.
- **Corpo da Requisição (`Body`):**
  ```json
  {
      "numeroProcesso": "0001234-56.2024.8.26.0001",
      "nomeReclamante": "João da Silva",
      "cpfReclamante": "123.456.789-00",
      "nomeReclamada": "Empresa de Telefonia S/A",
      "advogadoResponsavel": "Dra. Jus",
      "status": "ATIVO"
  }
  ```
- **Exemplo com `curl`:**
  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"numeroProcesso":"0001234-56.2024.8.26.0001","nomeReclamante":"João da Silva","cpfReclamante":"123.456.789-00","nomeReclamada":"Empresa de Telefonia S/A","advogadoResponsavel":"Dra. Jus","status":"ATIVO"}' http://localhost:8080/processos
  ```
- **Resposta de Sucesso (Status `201 Created`):** O objeto do processo recém-criado, incluindo seu `id`.

### 2. Consultar um Processo por ID

- **Rota:** `GET /processos/{id}`
- **Descrição:** Busca os detalhes de um processo específico. Lembre-se que, para simular a regra de segurança, o `advogadoResponsavel` no processo deve ser "Dra. Jus".
- **Exemplo com `curl` (supondo que o ID seja 1):**
  ```bash
  curl http://localhost:8080/processos/1
  ```
- **Respostas Possíveis:**
    - **`200 OK`**: Retorna os dados do processo.
    - **`403 Forbidden`**: Se você tentar acessar um processo cujo `advogadoResponsavel` não seja "Dra. Jus".
    - **`404 Not Found`**: Se não existir um processo com o ID informado.

---

Desenvolvido por **Andréa e Andriele**.
