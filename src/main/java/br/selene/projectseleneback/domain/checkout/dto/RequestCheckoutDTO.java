package br.selene.projectseleneback.domain.checkout.dto;


import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.time.LocalDateTime;
import java.util.List;

public record RequestCheckoutDTO(
        String reference_id,
        String expiration_date,
        CustomerCheckoutDTO  customer,
        List<ItemsCheckoutDTO> items,
        List<PaymentMethodCheckoutDTO> payment_methods,
        String redirect_uri,
        List<String> notification_urls,
        List<String> payment_notification_urls
){}

