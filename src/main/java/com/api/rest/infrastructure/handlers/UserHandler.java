package com.api.rest.infrastructure.handlers;

import com.api.rest.application.facades.UserFacadeUseCase;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import com.api.rest.infrastructure.validators.ObjectValidator;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    @Resource
    private UserFacadeUseCase userFacadeUseCase;
    @Resource
    private ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        try {
            return request.bodyToMono(CredentialsDto.class)
                          .doOnNext(gameDto -> objectValidator.validate(gameDto))
                          .flatMap(credentialsDto -> ServerResponse.ok()
                                                                   .contentType(MediaType.APPLICATION_JSON)
                                                                   .body(userFacadeUseCase.login(credentialsDto),
                                                                           TokenDto.class))
                          .switchIfEmpty(ServerResponse.notFound()
                                                       .build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        try {
            return request.bodyToMono(UserDto.class)
                          .doOnNext(gameDto -> objectValidator.validate(gameDto))
                          .flatMap(userDto -> ServerResponse.ok()
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .body(userFacadeUseCase.saveUser(userDto),
                                                                    UserDto.class))
                          .switchIfEmpty(ServerResponse.notFound()
                                                       .build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }
}
