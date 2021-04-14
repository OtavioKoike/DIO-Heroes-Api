package com.digitalinnovation.livecoding.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
//Acessar a chave primaria do banco
import org.springframework.data.annotation.Id;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.NoArgsConstructor;

//Classe usada para model

//Mostrar que é uma classe de dados
//Já possui os getter e setters
@Data
@NoArgsConstructor
//Passando a tabela criada
@DynamoDBTable(tableName ="Heroes_Api_Table")

public class Heroes {
//Atributos da tabela
  @Id
//Qual vai ser a coluna dele (Primary Key)
  @DynamoDBHashKey (attributeName = "id")
  private String id;

  //Qual vai ser a coluna dele (Attributes)
  @DynamoDBAttribute (attributeName = "name")
  private String name;

  @DynamoDBAttribute (attributeName = "universe")
  private String universe;

  @DynamoDBAttribute (attributeName = "films")
  private int films;

//Constructor
  public Heroes(String id, String name, String universe, int films) {
    this.id = id;
    this.name = name;
    this.universe = universe;
    this.films = films;
  }

}

