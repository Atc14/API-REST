package com.api.rest.application.usecases;

import com.api.rest.domain.ports.in.LoginUserUseCase;
import com.api.rest.domain.ports.out.UserRepositoryPort;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    @Resource
    private UserRepositoryPort userRepositoryPort;

    @Override
    public Mono<TokenDto> login(CredentialsDto credentialsDto) {
        return userRepositoryPort.login(credentialsDto);
    }
}
