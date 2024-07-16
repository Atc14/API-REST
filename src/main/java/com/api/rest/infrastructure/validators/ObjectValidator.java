package com.api.rest.infrastructure.validators;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import jakarta.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Component
public class ObjectValidator {

    @Autowired
    private Validator validator;

    public <T> T validate(T object) {
        if (validator.validate(object)
                     .isEmpty())
            return object;
        else {
            String message = validator.validate(object)
                                      .stream()
                                      .map(ConstraintViolation::getMessage)
                                      .collect(Collectors.joining(", "));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
