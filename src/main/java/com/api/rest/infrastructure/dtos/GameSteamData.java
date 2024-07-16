package com.api.rest.infrastructure.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSteamData {

    private String screenshotUrl;
}