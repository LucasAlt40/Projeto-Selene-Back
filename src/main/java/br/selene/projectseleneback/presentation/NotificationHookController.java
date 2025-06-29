package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.presentation.dto.notification.RequestCheckoutNotificationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationHookController {

    private IOrderService orderService;

    @PostMapping("/checkout")
    public void notifyCheckout(@RequestBody RequestCheckoutNotificationDTO request) {
        this.orderService.updateOrderStatus(request);
    }

    @PostMapping("/payment")
    public void notifyPayment(@RequestBody RequestCheckoutNotificationDTO request) {
        this.orderService.updateOrderStatus(request);
    }
}
