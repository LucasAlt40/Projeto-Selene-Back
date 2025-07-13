package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.infra.facade.NotificationFacade;
import br.selene.projectseleneback.presentation.dto.notification.RequestCheckoutNotificationDTO;
import br.selene.projectseleneback.presentation.dto.notification.RequestPaymentNotificationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationHookController {
    private final NotificationFacade facade;

    public NotificationHookController(NotificationFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/checkout")
    public void notifyCheckout(@RequestBody RequestCheckoutNotificationDTO request) {
        facade.updateCheckoutStatus(request.id(), request.referenceId());
    }

    @PostMapping("/payment")
    public void notifyPayment(@RequestBody RequestPaymentNotificationDTO request) {
        facade.updatePaymentStatus(request.referenceId(), request.charges().getFirst().status());
    }
}
