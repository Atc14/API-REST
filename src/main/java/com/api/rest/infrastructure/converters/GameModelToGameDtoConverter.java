package com.api.rest.infrastructure.converters;

import com.api.rest.domain.models.GameModel;
import com.api.rest.infrastructure.dtos.GameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GameModelToGameDtoConverter implements Converter<GameModel, GameDto> {
    @Override
    public GameDto convert(final GameModel source) {
        Assert.notNull(source, "GameModel cannot be null");

        return GameDto.builder()
                      .id(source.getId())
                      .title(source.getTitle())
                      .description(source.getDescription())
                      .steamId(source.getSteamId())
                      .build();
    }
}
