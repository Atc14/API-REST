package com.api.rest.infrastructure.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final String START_OF_TOKEN = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest()
                              .getPath()
                              .value();
        if (path.contains("auth"))
            return chain.filter(exchange);
        String auth = exchange.getRequest()
                              .getHeaders()
                              .getFirst(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(auth))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        if (!auth.startsWith(START_OF_TOKEN))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        String token = auth.replace(START_OF_TOKEN, "");
        exchange.getAttributes()
                .put("token", token);
        return chain.filter(exchange);
    }
}