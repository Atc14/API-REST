package com.api.rest.infrastructure.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GameDto {
    private String id;

    @NotBlank
    private String title;

    private String description;

    @Digits(integer = 10, fraction = 0)
    private Integer steamId;
}