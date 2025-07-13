package br.selene.projectseleneback.presentation.dto.notification;

import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;

public record RequestCheckoutNotificationDTO(
        String id,
        Long reference_id,
        CheckoutStatusEnum status
) {
}
