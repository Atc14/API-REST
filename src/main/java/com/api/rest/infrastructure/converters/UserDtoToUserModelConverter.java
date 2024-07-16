package com.api.rest.infrastructure.converters;

import com.api.rest.domain.models.UserModel;
import com.api.rest.infrastructure.dtos.UserDto;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserDtoToUserModelConverter implements Converter<UserDto, UserModel> {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel convert(final UserDto source) {
        Assert.notNull(source, "UserDto cannot be null");

        return UserModel.builder()
                        .id(source.getId())
                        .username(source.getUsername())
                        .email(source.getEmail())
                        .password(passwordEncoder.encode(source.getPassword()))
                        .build();
    }
}