package com.api.rest.application.services;

import com.api.rest.domain.models.GameModel;
import com.api.rest.domain.ports.in.DeleteGameUseCase;
import com.api.rest.domain.ports.in.RetrieveGameUseCase;
import com.api.rest.domain.ports.in.SaveGameUseCase;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameService implements SaveGameUseCase, RetrieveGameUseCase, DeleteGameUseCase {

    @Resource
    private SaveGameUseCase saveGameUseCase;
    @Resource
    private RetrieveGameUseCase retrieveGameUseCase;
    @Resource
    private DeleteGameUseCase deleteGameUseCase;

    @Override
    public Mono<GameModel> saveGame(GameModel gameModel) {
        return saveGameUseCase.saveGame(gameModel);
    }

    @Override
    public Flux<GameModel> findAll() {
        return retrieveGameUseCase.findAll();
    }

    @Override
    public Flux<GameModel> findByTitle(String title) {
        return retrieveGameUseCase.findByTitle(title);
    }

    @Override
    public Mono<GameModel> findById(String id) {
        return retrieveGameUseCase.findById(id);
    }

    @Override
    public Mono<Void> deleteGame(String id) {
        return deleteGameUseCase.deleteGame(id);
    }
}