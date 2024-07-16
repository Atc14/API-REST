package com.api.rest.infrastructure.repositories;

import com.api.rest.domain.models.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {

    Mono<UserModel> findOneByEmail(String email);
}