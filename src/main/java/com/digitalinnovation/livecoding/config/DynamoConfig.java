package com.digitalinnovation.livecoding.config;

//Para fazer comunicação com o AWS
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//Informa para o Spring Data que estamos usando o dynamo como nosso repositorio
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
//Saber qual o valor que vamos ler
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

//Classe usada para configurações da aplicaçao/banco de dados Dynamo

@Configuration
//Reconhecer que aqui esta a configuraçao do dynamo
@EnableDynamoDBRepositories
public class DynamoConfig {

//Passar o valor da nossa chave
  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;

  @Value("${aws_access_key_id}")
  private String amazonAWSAccessKey;

  @Value("${aws_secret_access_key}")
  private String amazonAWSSecretKey;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
    if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
      amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
    }
    return amazonDynamoDB;
  }

  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
  }

}
