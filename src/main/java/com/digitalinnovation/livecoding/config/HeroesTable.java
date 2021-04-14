package com.digitalinnovation.livecoding.config;

import java.util.Arrays;

//Para fazer comunicação com o AWS
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
//Nome dos atibutos/campos que estamos utilizando
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
//Esquema de chave
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
//Chave que estamos utilizando
import com.amazonaws.services.dynamodbv2.model.KeyType;
//Quantidade de espaço que ele vai ocupar
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
// Tipo de atributo que estamos passando para o dynamobd
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import static com.digitalinnovation.livecoding.constants.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovation.livecoding.constants.HeroesConstant.REGION_DYNAMO;
//Informa para o Spring Data que estamos usando o dynamo como nosso repositorio
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

//Classe usada para criar nossa tabela

@Configuration
//Reconhecer que aqui esta a configuraçao do dynamo
@EnableDynamoDBRepositories
public class HeroesTable {

  public static void main(String[] args) throws Exception {

    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
      .build();

    DynamoDB dynamoDB = new DynamoDB(client);

    String tableName = "Heroes_Api_Table";

    try {
      System.out.println("Criando tabela, aguarde...");
      Table table = dynamoDB.createTable(tableName,
        Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
        Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
        new ProvisionedThroughput(5L, 5L));

      table.waitForActive();
      System.out.println("Successo " + table.getDescription().getTableStatus());

    }
    catch (Exception e) {
      System.err.println("Não foi possível criar a tabela");
      System.err.println(e.getMessage());
    }

  }

}
