package com.api.rest.domain.ports.out;

import com.api.rest.domain.models.GameModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameRepositoryPort {

    Mono<GameModel> saveGame(GameModel gameModel);

    Flux<GameModel> findAll();

    Mono<GameModel> findById(String id);

    Flux<GameModel> findByTitleContainsIgnoreCase(String title);

    Mono<Void> deleteGame(String id);

}