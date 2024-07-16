package com.api.rest.application.usecases;

import com.api.rest.domain.models.GameModel;
import com.api.rest.domain.ports.in.RetrieveGameUseCase;
import com.api.rest.domain.ports.out.GameRepositoryPort;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RetrieveGameUseCaseImpl implements RetrieveGameUseCase {

    @Resource
    private GameRepositoryPort gameRepositoryPort;

    @Override
    public Flux<GameModel> findAll() {
        return gameRepositoryPort.findAll();
    }

    @Override
    public Flux<GameModel> findByTitle(String title) {
        return gameRepositoryPort.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public Mono<GameModel> findById(String id) {
        return gameRepositoryPort.findById(id);
    }
}
