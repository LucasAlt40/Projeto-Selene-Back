package br.selene.projectseleneback.presentation.dto.notification;

public record RequestCheckoutNotificationDTO(
        String id,
        int referenceId,
        String status
) {
}
