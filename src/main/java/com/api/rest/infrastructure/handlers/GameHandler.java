package com.api.rest.infrastructure.handlers;

import com.api.rest.application.facades.GameFacadeUseCase;
import com.api.rest.infrastructure.dtos.GameDto;
import com.api.rest.infrastructure.dtos.GameSteamNewsData;
import com.api.rest.infrastructure.validators.ObjectValidator;
import jakarta.annotation.Resource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class GameHandler {

    private static final Integer NEWS_MAX_LENGTH = -1;
    private static final Integer NEWS_END_DATE = -1;
    private static final Integer NEWS_COUNT = 5;
    private static final String NEWS_FEEDS = "";
    @Resource
    private GameFacadeUseCase gameFacadeUseCase;
    @Resource
    private ObjectValidator objectValidator;

    public Mono<ServerResponse> findAllGames(ServerRequest request) {
        return ServerResponse.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(gameFacadeUseCase.findAll(), GameDto.class);
    }

    public Mono<ServerResponse> findGameById(ServerRequest request) {
        try {
            return gameFacadeUseCase.findById(request.pathVariable("id"))
                                    .flatMap(game -> ServerResponse.ok()
                                                                   .contentType(MediaType.APPLICATION_JSON)
                                                                   .body(fromValue(game)))
                                    .switchIfEmpty(ServerResponse.notFound()
                                                                 .build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> searchGame(ServerRequest request) {
        try {
            return ServerResponse.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(gameFacadeUseCase.findByTitle(request.queryParam("title")
                                                                            .orElseThrow(IllegalArgumentException::new))
                                         , GameDto.class)
                                 .switchIfEmpty(ServerResponse.notFound()
                                                              .build());

        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> createGame(ServerRequest request) {
        try {
            return request.bodyToMono(GameDto.class)
                          .doOnNext(gameDto -> objectValidator.validate(gameDto))
                          .flatMap(gameFacadeUseCase::saveGame)
                          .flatMap(game -> ServerResponse.accepted()
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .body(fromValue(game)))
                          .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_ACCEPTABLE)
                                                       .build());
        } catch (NullPointerException | IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }

    }

    public Mono<ServerResponse> updateGame(ServerRequest request) {
        try {
            return request.bodyToMono(GameDto.class)
                          .doOnNext(gameDto -> objectValidator.validate(gameDto))
                          .flatMap(game -> gameFacadeUseCase.findById(game.getId())
                                                            .flatMap(updatedGame -> {
                                                                updatedGame.setId(game.getId());
                                                                updatedGame.setTitle(game.getTitle());
                                                                updatedGame.setDescription(game.getDescription());
                                                                updatedGame.setSteamId(game.getSteamId());
                                                                return gameFacadeUseCase.saveGame(updatedGame);
                                                            }))
                          .flatMap(game -> ServerResponse.accepted()
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .body(fromValue(game)))
                          .switchIfEmpty(ServerResponse.notFound()
                                                       .build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> deleteGame(ServerRequest request) {
        try {
            return ServerResponse.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(gameFacadeUseCase.delete(request.pathVariable("id")), Void.class)
                                 .switchIfEmpty(ServerResponse.notFound()
                                                              .build());

        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> getStoreAppDetails(ServerRequest request) {
        try {
            return gameFacadeUseCase.getStoreAppDetails(Integer.valueOf(request.pathVariable("steamId")),
                                            LocaleContextHolder.getLocale()
                                                               .getCountry(),
                                            LocaleContextHolder.getLocale()
                                                               .toString())
                                    .flatMap(game -> ServerResponse.ok()
                                                                   .contentType(MediaType.APPLICATION_JSON)
                                                                   .body(fromValue(game)))
                                    .switchIfEmpty(ServerResponse.notFound()
                                                                 .build());

        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

    public Mono<ServerResponse> getSteamNewsItem(ServerRequest request) {
        try {
            return ServerResponse.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                         .body(gameFacadeUseCase.getSteamNewsItem(Integer.valueOf(request.pathVariable("steamId")),
                                                         NEWS_MAX_LENGTH,
                                                         NEWS_END_DATE,
                                                         NEWS_COUNT,
                                                         NEWS_FEEDS)
                                         , GameSteamNewsData.class)
                                 .switchIfEmpty(ServerResponse.notFound()
                                                              .build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest()
                                 .build();
        }
    }

}