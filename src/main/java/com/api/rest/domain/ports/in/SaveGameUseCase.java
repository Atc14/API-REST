package com.api.rest.domain.ports.in;

import com.api.rest.domain.models.GameModel;
import reactor.core.publisher.Mono;

public interface SaveGameUseCase {

    Mono<GameModel> saveGame(GameModel gameModel);
}