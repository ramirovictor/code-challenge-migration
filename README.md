# DummyJSON Client

## Descrição do Projeto

Este projeto é um microsserviço desenvolvido em **Java 17** e **Spring Boot 3.2.5** que interage com a API pública [DummyJSON](https://dummyjson.com/docs/products) para realizar consultas sobre produtos. O projeto foi atualizado a partir de uma versão em Java 8 e Spring Boot 2.x.x como parte de um desafio técnico de migração.

## Principais Tecnologias

- **Java 17**
- **Spring Boot 3.2.5**
- **WebClient** para chamadas HTTP reativas
- **Spring Actuator** para monitoramento
- **JUnit 5** para testes

## Requisitos do Desafio e Implementação

### Requisitos Atendidos

1. **Atualizar o projeto para Java 17 e Spring Boot 3.2.5**
   - O `pom.xml` foi atualizado para suportar Java 17 e Spring Boot 3.2.5, eliminando qualquer dependência depreciada ou incompatível.

2. **Substituir `RestTemplate` por `WebClient` ou `OpenFeign`**
   - O `RestTemplate` foi substituído pelo **WebClient**, que é nativo do Spring, permitindo maior flexibilidade e suporte para chamadas assíncronas.

3. **Substituir testes unitários por testes de integração utilizando `@SpringBootTest`**
   - Testes de integração foram implementados para a **Controller** e **Service** com o uso do `MockWebServer`, garantindo que os testes não dependam da API externa. Além disso, testes unitários foram mantidos para o DTO com **JUnit 5**.

4. **Refatorar qualquer código depreciado ou incompatível**
   - Todas as classes e configurações foram ajustadas para refletir as mudanças do novo namespace `jakarta.*` e eliminar avisos de depreciação.

5. **Garantir que todos os testes passam após a migração**
   - Todos os testes foram ajustados e passam com sucesso.

6. **Parametrizar a URL da API DummyJSON por ambiente**
   - A URL da API é configurada no `application.yaml` e no `application-test.yaml`, permitindo fácil adaptação por ambiente.

7. **Adicionar um endpoint `/health`**
   - A funcionalidade foi implementada com o Spring Actuator. O estado de saúde do microsserviço pode ser verificado em `http://localhost:8080/actuator/health`.

### Desafios Opcionais (Não Implementados)

Devido à limitação de tempo, os seguintes desafios opcionais não foram implementados:
- **Containerização do projeto**: Apesar de ser uma melhoria interessante, a criação de um `Dockerfile` e configuração para rodar o microsserviço em um container não foi priorizada.

---

## Passos para Executar o Projeto

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8.x** ou superior

### Executando a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.ibm.com/Wendell-Santos/code-challenge-migration.git
    cd dummyjson-client
    ```

2. Compile e execute a aplicação:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço em `http://localhost:8080`.

### Executando os Testes

Para rodar os testes automatizados, utilize:

```bash
mvn clean test
```

---

## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjson
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── config
│   │   │       │   └── WebClientConfig.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       │   └── ProductResponse.java
│   │   │       ├── service
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       ├── application.yaml
│   │       └── application-test.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjson
│       │       ├── controller
│       │       │   └── ProductControllerTest.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       └── service
│       │           └── ProductServiceTest.java
└── pom.xml
```

---

## Observações Finais

O desafio foi implementado com sucesso, atendendo todos os requisitos obrigatórios. 
O projeto está preparado para execução em Java 17 com Spring Boot 3.2.5, e os testes garantem a funcionalidade esperada. 
Para containerizar o projeto, será necessário adicionar um `Dockerfile` e um script de inicialização.
O contexto completo do desafio, com todas as informações e requisitos, pode ser encontrado no arquivo [README-DESAFIO.md](./README-DESAFIO.md)
