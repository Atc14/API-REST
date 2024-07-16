package com.api.rest.domain.ports.in;

import com.api.rest.domain.models.UserModel;
import com.api.rest.infrastructure.dtos.UserDto;
import reactor.core.publisher.Mono;

public interface SaveUserUseCase {

    Mono<UserModel> saveUser(UserDto userDto);
}
