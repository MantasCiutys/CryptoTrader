package com.mantasciutys.cryptotrader.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientBeans {

    @Bean
    public WebClient apiClient(@Value("${base.api.url}") String baseApiUrl) {
        return WebClient.create(baseApiUrl);
    }

    // Added temporarily as WebClient does not work for authorization
    @Bean
    public RestTemplate restTemplate(@Value("${base.api.url}") String baseApiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new RootUriTemplateHandler(baseApiUrl));
        return restTemplate;
    }
}
