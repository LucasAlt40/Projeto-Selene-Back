package br.selene.projectseleneback.presentation.dto.notification;

import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;

public record ChargesPaymentDTO(
        String id,
        String reference_id,
        PaymentCheckoutStatusEnum status
) {
}
