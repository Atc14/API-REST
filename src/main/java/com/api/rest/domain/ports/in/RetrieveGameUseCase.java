package com.api.rest.domain.ports.in;

import com.api.rest.domain.models.GameModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface RetrieveGameUseCase {

    Flux<GameModel> findAll();

    Flux<GameModel> findByTitle(String title);

    Mono<GameModel> findById(String id);
}