package com.api.rest.infrastructure.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSteamNewsData {

    private String title;

    private String url;

    private String author;

    private String contents;
}