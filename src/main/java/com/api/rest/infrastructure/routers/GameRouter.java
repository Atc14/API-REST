package com.api.rest.infrastructure.routers;

import com.api.rest.infrastructure.handlers.GameHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class GameRouter {

    @Bean
    public RouterFunction<ServerResponse> routerGame(GameHandler gameHandler) {
        return RouterFunctions.route(GET("api/v1"), gameHandler::findAllGames)
                              .andRoute(GET("api/v1/game/{id}"), gameHandler::findGameById)
                              .andRoute(GET("api/v1/game/steam/details/{steamId}"), gameHandler::getStoreAppDetails)
                              .andRoute(GET("api/v1/game/steam/news/{steamId}"), gameHandler::getSteamNewsItem)
                              .andRoute(GET("api/v1/game"), gameHandler::searchGame)
                              .andRoute(POST("api/v1/game/new"), gameHandler::createGame)
                              .andRoute(PUT("api/v1/game/edit"), gameHandler::updateGame)
                              .andRoute(DELETE("api/v1/game/delete/{id}"), gameHandler::deleteGame);
    }
}