package com.digitalinnovation.livecoding.controller;

//Model
import com.digitalinnovation.livecoding.document.Heroes;
//Repository
import com.digitalinnovation.livecoding.repository.HeroesRepository;
//Service
import com.digitalinnovation.livecoding.service.HeroesService;
//Logs no java
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
//Tipo de resposta da nossa requisicao
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovation.livecoding.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

//Classe usada para controlar nossa aplicação

//Controle da nossa api
@RestController
@Slf4j
public class HeroesController {
//Injecao de dependencia
  HeroesService heroesService;
  HeroesRepository heroesRepository;

//Habilitar o log
  private static final org.slf4j.Logger log =
//  Fabrica de log
    org.slf4j.LoggerFactory.getLogger(HeroesController.class);

//Conscructor
  public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
    this.heroesService = heroesService;
    this.heroesRepository = heroesRepository;
  }

//Simbolizar as ações get passando a rota
  @GetMapping(HEROES_ENDPOINT_LOCAL)
//Codigo de resposta
  @ResponseStatus(HttpStatus.OK)
//Flux -> conjunto de dados
  public Flux<Heroes> getAllItems() {
    log.info("requesting the list off all heroes");
    return heroesService.findAll();

  }

//Simbolizar as ações get passando a rota
  @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
//Mono -> unico dado
  public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
    log.info("Requesting the hero with id {}", id);
    return heroesService.findByIdHero(id)
//     Reotornar se a requisição deu certo ou não
      .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

//Simbolizar as ações put passando a rota
  @PostMapping(HEROES_ENDPOINT_LOCAL)
//Codigo de resposta
  @ResponseStatus(HttpStatus.CREATED)
//Mono -> unico dado
  public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
    log.info("A new Hero was Created");
    return heroesService.save(heroes);

  }

//Simbolizar as ações delete passando a rota
  @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
//Codigo de resposta
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
//Mono -> unico dado
  public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
    heroesService.deletebyIDHero(id);
    log.info("Deleting the hero with id {}", id);
    return Mono.just(HttpStatus.NOT_FOUND);
  }
}
