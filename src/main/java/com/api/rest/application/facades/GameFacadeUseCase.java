package com.api.rest.application.facades;

import com.api.rest.infrastructure.dtos.GameDto;
import com.api.rest.infrastructure.dtos.GameSteamData;
import com.api.rest.infrastructure.dtos.GameSteamNewsData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameFacadeUseCase {

    Mono<GameDto> saveGame(GameDto gameDto);

    Flux<GameDto> findAll();

    Mono<GameDto> findById(String id);

    Flux<GameDto> findByTitle(String title);

    Mono<Void> delete(String id);

    Mono<GameSteamData> getStoreAppDetails(Integer steamId, String countryCode, String language);

    Flux<GameSteamNewsData> getSteamNewsItem(Integer steamId, Integer maxLength, Integer endDate, Integer count, String feeds);

}