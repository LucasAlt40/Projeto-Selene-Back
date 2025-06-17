package br.selene.projectseleneback.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PagbankApiConfig {

    @Bean
    public WebClient pagbankWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://sandbox.api.pagseguro.com/checkouts")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
