package br.selene.projectseleneback.model.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record ResponseGatewayDTO(
        String id,
        String reference_id,
        OffsetDateTime expiration_date,
        OffsetDateTime created_at,
        String status,
        Customer customer,
        boolean customer_modifiable,
        List<Items> items,
        int additional_amount,
        int discount_amount,
        List<PaymentMethodsDTO> payment_methods,
        List<LinkDTO> links
) {
}

