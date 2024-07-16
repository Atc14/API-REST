package com.api.rest.infrastructure.converters;

import com.api.rest.infrastructure.dtos.GameSteamData;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppDetails;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppScreenshots;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

@Component
public class StoreAppDetailsToGameSteamDataConverter implements Converter<StoreAppDetails, GameSteamData> {

    @Override
    public GameSteamData convert(final StoreAppDetails source) {
        Assert.notNull(source, "Source cannot be null");

        List<StoreAppScreenshots> screenshotsList = source.getScreenshots();

        return GameSteamData.builder()
                            .screenshotUrl(screenshotsList.get(new Random().nextInt(screenshotsList.size()))
                                                          .getFullPath())
                            .build();
    }
}