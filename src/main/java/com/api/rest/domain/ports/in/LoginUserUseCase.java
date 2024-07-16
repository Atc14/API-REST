package com.api.rest.domain.ports.in;

import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import reactor.core.publisher.Mono;

public interface LoginUserUseCase {

    Mono<TokenDto> login(CredentialsDto credentialsDto);
}
