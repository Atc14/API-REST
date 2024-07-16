package com.api.rest.infrastructure.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialsDto {

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be email@email.email")
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
}