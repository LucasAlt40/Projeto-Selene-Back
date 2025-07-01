package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.checkout.repository.ICheckoutRepository;
import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CheckoutService implements ICheckoutService {

    private final WebClient webClient;
    private final ICheckoutRepository checkoutRepository;


    public CheckoutService(WebClient.Builder builder, ICheckoutRepository checkoutRepository) {
        this.webClient = builder.baseUrl("https://sandbox.api.pagseguro.com") // ou o host da API
                .defaultHeader("Authorization", "<BEARER TOKEN>") // se necessário
                .build();
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public ResponseCreateCheckoutDTO createCheckout(Order order) {
        return null;
    }

    @Override
    public Page<Checkout> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Checkout findById(String checkoutId) {
        return this.checkoutRepository.findById(checkoutId);
    }

    @Override
    public Checkout save(Checkout checkout) {
        return null;
    }

    @Override
    public List<Checkout> findCheckoutsByOrderId(int orderId) {
        return List.of();
    }

//    public Mono<ResponseGatewayDTO> criarCheckout(RequestGatewayDTO request) throws JsonProcessingException {
//
//        String jsonBody = objectMapper.writeValueAsString(request);
//        System.out.println("Body que será enviado: " + jsonBody);
//
//        return webClient.post()
//                .uri("/checkouts")
//                .bodyValue(request)
//                .retrieve()
//                .bodyToMono(ResponseGatewayDTO.class);
//    }
}
