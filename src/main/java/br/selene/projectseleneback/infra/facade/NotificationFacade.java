package br.selene.projectseleneback.infra.facade;

import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.presentation.dto.notification.RequestCheckoutNotificationDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationFacade {
    // public ITicketCategoryService ticketCategoryService;
    public ICheckoutService checkoutService;
    public IOrderService orderService;

    public void updateStatusOrder() {
        orderService.updateOrderStatus(OrderStatusEnum.EXPIRED);
        // TODO Lógica para devolver a quantidade de tickets
    }
}
