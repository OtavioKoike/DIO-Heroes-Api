package com.digitalinnovation.livecoding.service;

//Model
import com.digitalinnovation.livecoding.document.Heroes;
//Repository
import com.digitalinnovation.livecoding.repository.HeroesRepository;
//Para reconhecer que essa clase é um service
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Quem vai ter o serviço da nossa aplicaçao para o controller acessar
//Informando que ess classe e um service
@Service
public class HeroesService {
//Injeção de dependencia
  private final HeroesRepository heroesRepository;

//Construtor
  public HeroesService(HeroesRepository heroesRepository) {
    this.heroesRepository = heroesRepository;
  }

//Flux -> conjunto de dados
  public Flux<Heroes> findAll(){
    return Flux.fromIterable(this.heroesRepository.findAll());
  }

//Mono -> unico dado
  public  Mono<Heroes> findByIdHero(String id){
    return  Mono.justOrEmpty(this.heroesRepository.findById(id));
  }

//Criar heores
  public Mono<Heroes> save(Heroes heroes){
    return  Mono.justOrEmpty(this.heroesRepository.save(heroes));
  }

  public Mono<Boolean> deletebyIDHero(String id) {
    heroesRepository.deleteById(id);
    return Mono.just(true);
  }

}

