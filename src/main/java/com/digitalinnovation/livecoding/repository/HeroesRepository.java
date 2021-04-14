package com.digitalinnovation.livecoding.repository;

//Model
import com.digitalinnovation.livecoding.document.Heroes;
//Leitura dos dados no repository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
//Habilitar requisicoes basicas da api no repositorio
import org.springframework.data.repository.CrudRepository;

//Classe usada para acessar o banco de dados
//Camada de acesso aos dados da nossa model

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String>{
}
