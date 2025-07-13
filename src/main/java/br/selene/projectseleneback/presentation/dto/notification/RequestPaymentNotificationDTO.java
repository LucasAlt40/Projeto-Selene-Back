package br.selene.projectseleneback.presentation.dto.notification;

import java.util.List;

public record RequestPaymentNotificationDTO(
        String id,
        Long referenceId,
        List<ChargesPaymentDTO> charges
) {
}
