# Release Manager API

**Release Manager API** tem como principal objetivo gerenciar e registrar informações sobre releases. Por meio de endpoints seguros e autenticados via **OAuth** e **JWT**, a aplicação permite o cadastro, consulta e atualização de releases, salvando os dados no banco conforme regras de negócio definidas.

---

## Guia de Uso

### Pré-requisitos para o Ambiente

Antes de começar, certifique-se de que o ambiente possui todas as ferramentas abaixo instaladas e configuradas corretamente:

- **Java 21**: Certifique-se de ter o JDK (Java Development Kit) 21. <a href="https://openjdk.org/install/" target="_blank">Guia de instalação</a>.
- **Maven**: Necessário para gerenciamento de dependências e configuração do projeto. <a href="https://maven.apache.org/install.html" target="_blank">Guia de instalação do Maven</a>.
- **Docker e Docker Compose**: Utilizados para executar a aplicação em conjunto com suas dependências. <a href="https://docs.docker.com/get-docker/" target="_blank">Guia de instalação do Docker</a>.
- **Banco de Dados H2**: O projeto utiliza o banco de dados H2 em memória para facilitar ambientes de desenvolvimento e testes. Não é necessário instalar nada manualmente, pois o H2 é iniciado automaticamente junto com a aplicação.  
  <a href="https://www.h2database.com/html/main.html" target="_blank">Saiba mais sobre o H2 Database</a>.
---

### Executando via Maven

1. Clone este repositório:
   ```bash
   git clone https://github.com/sauloddiniz/release_manager.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd release_manager
   ```

3. Utilize o Maven para baixar as dependências e iniciar a aplicação:
   ```bash
   mvn spring-boot:run
   ```

Após rodar o comando acima, o Maven irá resolver e baixar automaticamente todas as dependências necessárias.  
A aplicação estará disponível localmente, normalmente na porta **8080** (ou conforme configurado no seu `application.properties`).


### Executando via Docker Compose (dependência do Docker)

Com esse modo de execução, não é necessário se preocupar com instalações ou configurações locais, pois a imagem da aplicação já está disponível no Docker Hub.

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

2. Execute o comando abaixo estando na raiz do projeto, para iniciar todos os serviços definidos no arquivo `docker-compose.yaml`:
   ```bash
    docker-compose up
    ```

3. Para parar os containers, utilize:
   ```bash
   docker-compose down
   ```

Após esses passos, a aplicação já estará disponível.

---

### Obtendo o Token de Autenticação

1. Ao tentar acessar qualquer endpoint pela primeira vez, você será automaticamente redirecionado para a tela de login do **OAuth2** utilizando o GitHub.

2. Realize o login com sua conta do GitHub.

3. Após a autenticação, você será redirecionado para a página do Swagger.  
   O token JWT gerado estará disponível como um **query param** na URL do Swagger (`?jwt=<seu-token-aqui>`).

4. Copie o valor do token que está na URL.

5. No Swagger, clique em **Authorize** (ícone de cadeado) e cole o token copiado no campo solicitado, sempre no formato:

6. Após autorizar com o token JWT, você poderá realizar requisições autenticadas em todos os endpoints. A autenticação é obrigatória para todas as operações protegidas.

---

**Dicas:**
- O token é necessário para que as requisições da API funcionem corretamente no Swagger.
- Se precisar de um novo token (por expiração ou logout), basta recarregar o Swagger e repetir o processo de login.

---


### Documentação dos Endpoints

Ao iniciar a aplicação, você poderá acessar a documentação dos endpoints através do **Swagger UI**:

- **Ambiente local**:
    - <a href="http://localhost:8080/release-manager/swagger-ui/index.html" target="_blank">Swagger UI - Local</a>


- A documentação traz detalhes de cada endpoint, incluindo os parâmetros esperados e a estrutura de autenticação.


- Alguns dados já foram pré-cadastrados no banco para facilitar os testes e execução de requisições nos endpoints.
---