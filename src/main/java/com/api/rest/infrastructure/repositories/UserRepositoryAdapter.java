package com.api.rest.infrastructure.repositories;

import com.api.rest.domain.models.UserModel;
import com.api.rest.domain.ports.out.UserRepositoryPort;
import com.api.rest.infrastructure.converters.GameDtoToGameModelConverter;
import com.api.rest.infrastructure.converters.UserDtoToUserModelConverter;
import com.api.rest.infrastructure.dtos.CredentialsDto;
import com.api.rest.infrastructure.dtos.TokenDto;
import com.api.rest.infrastructure.dtos.UserDto;
import com.api.rest.infrastructure.security.JwtProvider;
import com.api.rest.infrastructure.security.UserDetailsImpl;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    @Resource
    private UserRepository userRepository;
    @Resource
    private JwtProvider jwtProvider;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDtoToUserModelConverter userDtoToUserModelConverter;

    @Override
    public Mono<TokenDto> login(CredentialsDto credentialsDto) {
        return userRepository.findOneByEmail(credentialsDto.getEmail())
                             .filter(userModel -> passwordEncoder.matches(credentialsDto.getPassword(),
                                     userModel.getPassword()))
                             .map(userModel -> new TokenDto(jwtProvider.buildToken(UserDetailsImpl.builder()
                                                                                                  .id(userModel.getId())
                                                                                                  .username(userModel.getUsername())
                                                                                                  .username(userModel.getEmail())
                                                                                                  .password(userModel.getPassword())
                                                                                                  .build())))
                             .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @Override
    public Mono<UserModel> saveUser(UserDto userDto) {
        return userRepository.findOneByEmail(userDto.getEmail())
                             .hasElement()
                             .flatMap(exists -> exists ?
                                     Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)) :
                                     userRepository.save(userDtoToUserModelConverter.convert(userDto)));
    }
}