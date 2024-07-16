package com.api.rest.infrastructure.converters;

import com.api.rest.domain.models.GameModel;
import com.api.rest.infrastructure.dtos.GameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GameDtoToGameModelConverter implements Converter<GameDto, GameModel> {
    @Override
    public GameModel convert(final GameDto source) {
        Assert.notNull(source, "GameDto cannot be null");

        return GameModel.builder()
                        .id(source.getId())
                        .title(source.getTitle())
                        .description(source.getDescription())
                        .steamId(source.getSteamId())
                        .build();
    }
}
