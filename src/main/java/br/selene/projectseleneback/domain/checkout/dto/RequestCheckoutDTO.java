package br.selene.projectseleneback.domain.checkout.dto;


import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.util.List;

public record RequestCheckoutDTO(
        CustomerCheckoutDTO  customer,
        List<ItemsCheckoutDTO> items,
        List<PaymentMethodCheckoutDTO> payment_methods,
        String redirect_url,
        List<String> notification_urls,
        List<String> payment_notification_urls
){}

