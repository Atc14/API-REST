package com.api.rest.infrastructure.repositories;

import com.api.rest.domain.models.GameModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GameRepository extends ReactiveMongoRepository<GameModel, String> {

    Flux<GameModel> findByTitleContainsIgnoreCase(String title);
}