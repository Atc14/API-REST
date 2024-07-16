package com.api.rest.application.usecases;

import com.api.rest.domain.models.GameModel;
import com.api.rest.domain.ports.in.SaveGameUseCase;
import com.api.rest.domain.ports.out.GameRepositoryPort;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SaveGameUseCaseImpl implements SaveGameUseCase {

    @Resource
    private GameRepositoryPort gameRepositoryPort;

    @Override
    public Mono<GameModel> saveGame(GameModel gameModel) {
        return gameRepositoryPort.saveGame(gameModel);
    }
}