package br.selene.projectseleneback.infra.facade;

import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class NotificationFacade {
    public ICheckoutService checkoutService;
    public IOrderService orderService;

    public NotificationFacade(ICheckoutService checkoutService, IOrderService orderService) {
        this.checkoutService = checkoutService;
        this.orderService = orderService;
    }

    public void updatePaymentStatus(Long orderId, PaymentCheckoutStatusEnum paymentStatus) {

        orderService.updateOrderStatus(orderId, mapToOrderStatus(paymentStatus));
        checkoutService.updateCheckoutPaymentStatusByOrderId(orderId, paymentStatus);

        if(paymentStatus.equals(PaymentCheckoutStatusEnum.PAID)) {
            // TODO GERAR TICKET
        }
    }

    public void updateCheckoutStatus(String checkoutId, Long orderId) {
        checkoutService.deleteCheckout(checkoutId);
        orderService.updateOrderStatus(orderId, OrderStatusEnum.EXPIRED);
    }

    private OrderStatusEnum mapToOrderStatus(PaymentCheckoutStatusEnum paymentStatus) {
        if (paymentStatus == null) {
            throw new IllegalArgumentException("PaymentCheckoutStatusEnum cannot be null");
        }

        return switch (paymentStatus) {
            case PAID -> OrderStatusEnum.COMPLETED;
            case IN_ANALYSIS -> OrderStatusEnum.PROCESSING;
            case DECLINED -> OrderStatusEnum.PROCESSING;
            case CANCELED -> OrderStatusEnum.CANCELLED;
            case WAITING -> OrderStatusEnum.WAITING_PAYMENT;
            default -> throw new IllegalArgumentException("Unknown PaymentCheckoutStatusEnum: " + paymentStatus);
        };
    }
}
