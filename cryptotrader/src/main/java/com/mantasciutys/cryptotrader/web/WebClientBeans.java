package com.mantasciutys.cryptotrader.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientBeans {

    @Bean
    public WebClient apiClient(@Value("${base.api.url}") String baseApiUrl) {
        return WebClient.create(baseApiUrl);
    }
}
