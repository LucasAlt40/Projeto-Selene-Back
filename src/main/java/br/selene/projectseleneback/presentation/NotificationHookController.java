package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.model.dto.RequestCheckoutNotificationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationHookController {

    @PostMapping("/checkout")
    public void notifyCheckout(@RequestBody RequestCheckoutNotificationDTO request) {

    }
}
