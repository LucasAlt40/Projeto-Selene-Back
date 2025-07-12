package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.checkout.dto.ResponseLinksDTO;
import br.selene.projectseleneback.domain.checkout.repository.ICheckoutRepository;
import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.infra.mappers.OrderToRequestCheckout;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService implements ICheckoutService {

    private final WebClient webClient;
    private final ICheckoutRepository checkoutRepository;
    private final CustomerService customerService;

    @Value("${app.redirectCheckoutUrl}")
    private String redirectUrl;

    @Value("${app.notification_urls}")
    private String notificationUrls;

    @Value("${app.payment_notification_urls}")
    private String payment_notification_urls;

    @Value("${app.bearerToken}")
    private String bearerToken;


    public CheckoutService(WebClient.Builder builder, ICheckoutRepository checkoutRepository, CustomerService customerService, TicketCategoryService ticketCategoryService) {
        this.webClient = builder.baseUrl("https://sandbox.api.pagseguro.com")
                .defaultHeader("Authorization", "BEARER ")
                .build();
        this.checkoutRepository = checkoutRepository;
        this.customerService = customerService;
    }

    @Override
    public ResponseCreateCheckoutDTO createCheckout(Order order) {

        Customer customer = customerService.findById(order.getCustomerId());

        System.out.println(bearerToken);
        System.out.println(redirectUrl);

        var requestCheckout = OrderToRequestCheckout.from(order, customer, redirectUrl, List.of(notificationUrls), List.of(payment_notification_urls));

        System.out.println(requestCheckout.toString());
        try {
            var response = webClient.post()
                    .uri("/checkouts")
                    .bodyValue(requestCheckout)
                    .retrieve()
                    .bodyToMono(ResponseCreateCheckoutDTO.class)
                    .block()
            ;

            Optional<ResponseLinksDTO> payLink = response.links().stream()
                    .filter(link -> "PAY".equalsIgnoreCase(link.rel()))
                    .findFirst();



           checkoutRepository.save(new Checkout(response.id(), order.getId(), payLink.get().href(), response.status(), PaymentCheckoutStatusEnum.WAITING ));

            System.out.println(response.toString());
            return new ResponseCreateCheckoutDTO(response.id(), response.links(), response.status());
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Page<Checkout> findAll(Pageable pageable) {
        return checkoutRepository.findAll(pageable);
    }

    @Override
    public Checkout findById(String checkoutId) {
        return checkoutRepository.findById(checkoutId);
    }

    @Override
    public Checkout save(Checkout checkout) {
        return checkoutRepository.save(checkout);
    }

    @Override
    public List<Checkout> findCheckoutsByOrderId(int orderId) {
        return checkoutRepository.findCheckoutsByOrderId(orderId);
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
