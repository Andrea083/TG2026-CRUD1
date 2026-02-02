# Sistema Jurídico API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-green)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

API RESTful para gestão de processos jurídicos, desenvolvida como um projeto de estudo para aplicar conceitos de backend com Java e Spring Framework.

## 🎯 Objetivo do Projeto

O objetivo desta API é fornecer um backend robusto para uma aplicação (web ou mobile) focada em advogados e seus clientes. Ela permite a centralização, controle e consulta de informações sobre processos judiciais, garantindo que os dados sejam acessados e manipulados de forma segura e eficiente.

## ✨ Funcionalidades

- **CRUD Completo de Processos:** Crie, leia, atualize e delete registros de processos jurídicos.
- **Validação de Dados:** Garante a integridade dos dados recebidos pela API (ex: campos obrigatórios, formato de dados).
- **Segurança Simples:** Implementa uma regra de negócio onde um cliente só pode visualizar os detalhes dos processos em seu nome.

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
