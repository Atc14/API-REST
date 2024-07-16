package com.api.rest.infrastructure.security;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    @Resource
    private final JwtProvider jwtProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                   .map(auth -> jwtProvider.getClaims(auth.getCredentials()
                                                          .toString()))
                   .log()
                   .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)))
                   .map(claims -> new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList()));
    }
}