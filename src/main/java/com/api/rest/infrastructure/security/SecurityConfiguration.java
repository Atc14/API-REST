package com.api.rest.infrastructure.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfiguration {

    @Resource
    private ServerSecurityContextRepository securityContextRepository;


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity,
                                              JwtAuthenticationFilter filter) {

        return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                           .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("auth/**")
                                                                                            .permitAll()
                                                                                            .anyExchange()
                                                                                            .authenticated()
                           )
                           .addFilterAfter(filter, SecurityWebFiltersOrder.FIRST)
                           .securityContextRepository(securityContextRepository)
                           .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                           .logout(ServerHttpSecurity.LogoutSpec::disable)
                           .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                           .build();
    }
}