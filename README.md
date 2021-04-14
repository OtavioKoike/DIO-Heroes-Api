## Digital Innovation: Criando seu gerenciador de super heróis da Marvel e da DC em uma API reativa com Spring Boot.

Nesta live coding, é feito a criação uma API para o gerenciamento de super heróis Marvel e DC. Desenvolvemos toda a configuração do DynamoDB, a implementação do repository, service e controlers. Por fim fizemos testes unitários para validar o nosso sistema.

Durante a sessão, serão abordados os seguintes tópicos:

- Criação de um projeto pelo Spring Boot.
- Criação e configuração do AWS CLI.
- Criação da aplicação (Config, Controllers, service, repository ...).
- Execução do DynamoDB, criação e população da tabela.
- Criaçao e Testes de requests no Postman.
- Criação da documentação da API pelo Postman. 
- Desenvolvimento de testes unitários para validação de funcionalides básicas: listagem e exclusão de herói.

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run
```
Para executar o dynamoDB na pasta dele:

```shell script
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```

e, em outro terminal, para listagem das tabelas:

```shell script
 aws dynamodb list-tables --endpoint-url http://localhost:8000
```

Para acessar o swagger local:

```shell script
http://localhost:8080/swagger-ui-heroes-reactive-api.html
```