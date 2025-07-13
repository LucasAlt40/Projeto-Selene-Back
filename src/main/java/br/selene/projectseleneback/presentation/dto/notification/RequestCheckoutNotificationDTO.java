package br.selene.projectseleneback.presentation.dto.notification;

import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;

public record RequestCheckoutNotificationDTO(
        String id,
        int referenceId,
        PaymentCheckoutStatusEnum status
) {
}
