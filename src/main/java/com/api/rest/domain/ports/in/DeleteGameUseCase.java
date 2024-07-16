package com.api.rest.domain.ports.in;

import reactor.core.publisher.Mono;

public interface DeleteGameUseCase {

    Mono<Void> deleteGame(String id);
}