package com.api.rest.application.services;

import com.api.rest.domain.models.UserModel;
import com.api.rest.domain.ports.in.LoginUserUseCase;
import com.api.rest.domain.ports.in.SaveUserUseCase;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements LoginUserUseCase, SaveUserUseCase {

    @Resource
    private LoginUserUseCase loginUserUseCase;
    @Resource
    private SaveUserUseCase saveUserUseCase;

    @Override
    public Mono<TokenDto> login(CredentialsDto credentialsDto) {
        return loginUserUseCase.login(credentialsDto);
    }

    @Override
    public Mono<UserModel> saveUser(UserDto userDto) {
        return saveUserUseCase.saveUser(userDto);
    }
}