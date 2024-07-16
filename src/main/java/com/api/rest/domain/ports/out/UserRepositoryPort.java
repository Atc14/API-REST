package com.api.rest.domain.ports.out;

import com.api.rest.domain.models.UserModel;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {

    Mono<TokenDto> login(CredentialsDto credentialsDto);

    Mono<UserModel> saveUser(UserDto userDto);
}
