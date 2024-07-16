package com.api.rest.infrastructure.repositories;

import com.api.rest.domain.models.GameModel;
import com.api.rest.domain.ports.out.GameRepositoryPort;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GameRepositoryAdapter implements GameRepositoryPort {

    @Resource
    private GameRepository gameRepository;

    @Override
    public Mono<GameModel> saveGame(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }

    @Override
    public Flux<GameModel> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Mono<GameModel> findById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public Flux<GameModel> findByTitleContainsIgnoreCase(String title) {
        return gameRepository.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public Mono<Void> deleteGame(String id) {
        return gameRepository.deleteById(id);
    }
}