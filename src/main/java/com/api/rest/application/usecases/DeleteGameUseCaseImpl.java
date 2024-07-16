package com.api.rest.application.usecases;

import com.api.rest.domain.ports.in.DeleteGameUseCase;
import com.api.rest.domain.ports.out.GameRepositoryPort;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteGameUseCaseImpl implements DeleteGameUseCase {

    @Resource
    private GameRepositoryPort gameRepositoryPort;

    @Override
    public Mono<Void> deleteGame(String id) {

        return gameRepositoryPort.deleteGame(id);
    }
}
