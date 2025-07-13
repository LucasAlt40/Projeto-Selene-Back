package br.selene.projectseleneback.presentation.dto.notification;

import java.util.List;

public record RequestPaymentNotificationDTO(
        String id,
        Long reference_id,
        List<ChargesPaymentDTO> charges
) {
}
