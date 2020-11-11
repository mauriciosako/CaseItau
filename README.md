# Web Service REST java
>O projeto consiste em um WS Rest na linguagem Java e com persistência de dados na memória com H2 database. Este WS recebe e envia dados em formato JSON e possui as funcionalidades de cadastrar clientes e listá-los/consultá-los, assim como realizar transferências de valores entre suas contas e listar as transferências de uma determinada conta</p>

# setup do projeto

## Importar projeto e rodar no Eclipse.
#### 1- Abra o eclipse e realize a importação do projeto
  
>URI: https://github.com/mauriciosako/CaseItau.git

>com o projeto importado no eclipse, clique com o botão direito sobre o mesmo e selecione.

### 2- rode o projeto
>Abra a classe Startup.java, clique com o botao direito do mouse sobre a classe e vá até,

Run as> Java Application

>Após a inicialização, abra o navegador em http://localhost:8080/swagger-ui/ para acessar a documentação.

### 3 - criar listar e deletar clientes
>Utilizar o [Postman](https://www.getpostman.com "postman") para testar a API.

>#### criar
#method -  uri

POST - http://localhost:8080/rest-api
```json
#headers 
{ "Content-type": "application/json" } }

#body
{
"nome":"",
"endereco":"",
"cpf":""
}
```
>#### atualizar 
method -  uri


PUT -  http://localhost:8080/rest-api/{id}
```json
#headers 
{ "Content-type": "application/json" }

#body
{
"nome":"",
"endereco":"",
"cpf":""
}
```

>#### listar 
method -  uri

GET - http://localhost:8080/rest-api

>#### buscar por id 
method -  uri

GET - http://localhost:8080/rest-api/{id}

>#### deletar
method -  uri

DELETE - http://localhost:8080/rest-api/{id}

