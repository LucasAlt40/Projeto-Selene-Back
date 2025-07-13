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

        switch (paymentStatus) {
            case PAID:
                return OrderStatusEnum.COMPLETED;
            case IN_ANALYSIS:
                return OrderStatusEnum.PROCESSING;
            case DECLINED:
                return OrderStatusEnum.PROCESSING;
            case CANCELED:
                return OrderStatusEnum.CANCELLED;
            case WAITING:
                return OrderStatusEnum.WAITING_PAYMENT;
            default:
                throw new IllegalArgumentException("Unknown PaymentCheckoutStatusEnum: " + paymentStatus);
        }
    }
}
