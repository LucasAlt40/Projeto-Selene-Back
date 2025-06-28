package br.selene.projectseleneback.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RequestGatewayDTO(
        Customer customer,
        List<Items> items,
        List<PaymentMethodsDTO> payment_methods,
        String redirect_uri,
        List<String> notification_urls,
        List<String> payment_notification_urls
) {
}
