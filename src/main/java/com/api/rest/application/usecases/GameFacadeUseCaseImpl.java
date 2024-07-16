package com.api.rest.application.usecases;

import com.api.rest.application.facades.GameFacadeUseCase;
import com.api.rest.application.services.GameService;
import com.api.rest.domain.models.GameModel;
import com.api.rest.infrastructure.dtos.GameDto;
import com.api.rest.infrastructure.dtos.GameSteamData;
import com.api.rest.infrastructure.dtos.GameSteamNewsData;
import com.ibasco.agql.protocols.valve.steam.webapi.interfaces.SteamNews;
import com.ibasco.agql.protocols.valve.steam.webapi.interfaces.SteamStorefront;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.SteamNewsItem;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppDetails;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class GameFacadeUseCaseImpl implements GameFacadeUseCase {

    @Resource
    Converter<StoreAppDetails, GameSteamData> storeAppDetailsGameSteamDataConverter;
    @Resource
    Converter<SteamNewsItem, GameSteamNewsData> steamNewsItemGameSteamDataNewsConverter;
    @Resource
    private GameService gameService;
    @Resource
    private Converter<GameDto, GameModel> gameDtoGameModelConverter;
    @Resource
    private Converter<GameModel, GameDto> gameModelGameDtoConverter;
    @Resource
    private SteamStorefront steamStorefront;
    @Resource
    private SteamNews steamNews;

    @Override
    public Mono<GameDto> saveGame(GameDto gameDto) {
        Assert.notNull(gameDto, "gameDto cannot be null!");

        return gameService.saveGame(gameDtoGameModelConverter.convert(gameDto))
                          .map(gameModelGameDtoConverter::convert);
    }

    @Override
    public Flux<GameDto> findAll() {
        return gameService.findAll()
                          .map(gameModelGameDtoConverter::convert);

    }

    @Override
    public Mono<GameDto> findById(String id) {
        Assert.notNull(id, "id cannot be null!");

        return gameService.findById(id)
                          .map(gameModelGameDtoConverter::convert);
    }

    @Override
    public Flux<GameDto> findByTitle(String title) {
        Assert.notNull(title, "title cannot be null!");

        return gameService.findByTitle(title)
                          .map(gameModelGameDtoConverter::convert);
    }

    @Override
    public Mono<Void> delete(String id) {
        Assert.notNull(id, "id cannot be null!");

        return gameService.deleteGame(id);
    }

    @Override
    public Mono<GameSteamData> getStoreAppDetails(Integer steamId, String countryCode, String language) {
        return Mono.fromFuture(steamStorefront.getAppDetails(steamId, countryCode, language))
                   .map(storeAppDetailsGameSteamDataConverter::convert);
    }

    @Override
    public Flux<GameSteamNewsData> getSteamNewsItem(Integer steamId, Integer maxLength, Integer endDate, Integer count, String feeds) {
        return Flux.fromStream(steamNews.getNewsForApp(steamId, maxLength, endDate, count, feeds)
                                        .join()
                                        .stream()
                                        .map(steamNewsItemGameSteamDataNewsConverter::convert));
    }
}