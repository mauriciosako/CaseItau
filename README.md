# REST API
>O projeto consiste em um WS Rest na linguagem Java e com persistência de dados na memória com H2 database. Este WS recebe e envia dados em formato JSON e possui as funcionalidades de cadastrar clientes e listá-los/consultá-los, assim como realizar transferências de valores entre suas contas e listar as transferências de uma determinada conta</p>

# Setup do projeto

## Importar projeto e rodar no Eclipse.
#### 1- Abra o Eclipse e realize a importação do projeto
  
>URI: https://github.com/mauriciosako/CaseItau.git


### 2- Executar o projeto
>Abra a classe Startup.java, clique com o botao direito do mouse sobre a classe e vá até,

Run as> Java Application

>Após a inicialização, abra o navegador em http://localhost:8080/swagger-ui/ para acessar a documentação.

### 3 - Endpoint Cliente e Transferencia
>Utilizar o [Postman](https://www.getpostman.com "postman") para testar a API. Pode-se importar o arquivo CaseItau.postman_collection.json para ajudar nos testes.


#### a. Cadastrar um cliente - POST - http://localhost:8080/cliente
```json
{
  "nome": "string",
  "numConta": "string",
  "saldo": 0
}
```

#### b. Consultar um cliente - GET - http://localhost:8080/cliente/{conta}

#### c. Listar todos os clientes - GET - http://localhost:8080/cliente


#### d. Realizar uma transferência - POST - http://localhost:8080/transf
```json
{
  "contaDestino": "string",
  "contaOrigem": "string",
  "valor": 0
}
```

#### e. Consultar as transferências relacionadas a uma conta - GET - http://localhost:8080/transf/{conta}



