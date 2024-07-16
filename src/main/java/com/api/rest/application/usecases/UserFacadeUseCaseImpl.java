package com.api.rest.application.usecases;

import com.api.rest.application.facades.UserFacadeUseCase;
import com.api.rest.application.services.UserService;
import com.api.rest.domain.models.UserModel;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

@Component
public class UserFacadeUseCaseImpl implements UserFacadeUseCase {

    @Resource
    private UserService userService;
    @Resource
    private Converter<UserDto, UserModel> userDtoUserModelConverter;
    @Resource
    private Converter<UserModel, UserDto> userModelUserDtoConverter;

    @Override
    public Mono<TokenDto> login(CredentialsDto credentialsDto) {
        Assert.notNull(credentialsDto, "credentials cannot be null!");

        return userService.login(credentialsDto);
    }

    @Override
    public Mono<UserDto> saveUser(UserDto userDto) {
        Assert.notNull(userDto, "user cannot be null!");

        return userService.saveUser(userDto)
                          .map(userModelUserDtoConverter::convert);
    }
}
