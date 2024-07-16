package com.api.rest.infrastructure.routers;

import com.api.rest.infrastructure.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerUser(UserHandler userHandler) {
        return RouterFunctions.route(POST("auth/login"), userHandler::login)
                              .andRoute(POST("auth/create"), userHandler::createUser);
    }
}
