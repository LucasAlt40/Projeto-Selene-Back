package br.selene.projectseleneback.service;

import br.selene.projectseleneback.model.dto.RequestGatewayDTO;
import br.selene.projectseleneback.model.dto.ResponseGatewayDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CheckoutService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;


    public CheckoutService(WebClient.Builder builder, ObjectMapper objectMapper) {
        this.webClient = builder.baseUrl("https://sandbox.api.pagseguro.com") // ou o host da API
                .defaultHeader("Authorization", "<BEARER TOKEN>") // se necessário
                .build();
        this.objectMapper = objectMapper;
    }

    public Mono<ResponseGatewayDTO> criarCheckout(RequestGatewayDTO request) throws JsonProcessingException {

        String jsonBody = objectMapper.writeValueAsString(request);
        System.out.println("Body que será enviado: " + jsonBody);

        return webClient.post()
                .uri("/checkouts")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ResponseGatewayDTO.class);
    }
}
