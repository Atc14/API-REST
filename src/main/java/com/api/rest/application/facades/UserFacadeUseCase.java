package com.api.rest.application.facades;

import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import reactor.core.publisher.Mono;

public interface UserFacadeUseCase {

    Mono<TokenDto> login(CredentialsDto credentialsDto);
    Mono<UserDto> saveUser(UserDto userDto);


}
