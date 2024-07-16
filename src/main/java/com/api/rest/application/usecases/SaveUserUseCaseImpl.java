package com.api.rest.application.usecases;

import com.api.rest.domain.models.UserModel;
import com.api.rest.domain.ports.in.SaveUserUseCase;
import com.api.rest.domain.ports.out.UserRepositoryPort;
import com.api.rest.infrastructure.dtos.UserDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SaveUserUseCaseImpl implements SaveUserUseCase {

    @Resource
    private UserRepositoryPort userRepositoryPort;

    @Override
    public Mono<UserModel> saveUser(UserDto userDto) {
        return userRepositoryPort.saveUser(userDto);
    }
}
