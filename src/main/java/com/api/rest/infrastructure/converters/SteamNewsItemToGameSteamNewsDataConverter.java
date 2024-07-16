package com.api.rest.infrastructure.converters;

import com.api.rest.infrastructure.dtos.GameSteamNewsData;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.SteamNewsItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SteamNewsItemToGameSteamNewsDataConverter implements Converter<SteamNewsItem, GameSteamNewsData> {

    @Override
    public GameSteamNewsData convert(final SteamNewsItem source) {
        Assert.notNull(source, "Source cannot be null");

        return GameSteamNewsData.builder()
                                .title(source.getTitle())
                                .url(source.getUrl())
                                .author(source.getAuthor())
                                .contents(source.getContents())
                                .build();
    }
}