package com.api.rest.infrastructure.converters;

import com.api.rest.domain.models.UserModel;
import com.api.rest.infrastructure.dtos.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserModelToUserDtoConverter implements Converter<UserModel, UserDto> {


    @Override
    public UserDto convert(final UserModel source) {
        Assert.notNull(source, "UserModel cannot be null");

        return UserDto.builder()
                      .id(source.getId())
                      .username(source.getUsername())
                      .email(source.getEmail())
                      .password(source.getPassword())
                      .build();
    }
}