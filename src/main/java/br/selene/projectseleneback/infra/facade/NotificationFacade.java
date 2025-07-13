package br.selene.projectseleneback.infra.facade;

import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.service.ICustomerService;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.dto.EventDTO;
import br.selene.projectseleneback.domain.event.service.IEventService;
import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.domain.ticket.Ticket;
import br.selene.projectseleneback.domain.ticket.service.ITicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationFacade {
    private final ICheckoutService checkoutService;
    private final IOrderService orderService;
    private final ITicketService ticketService;
    private final ICustomerService customerService;
    private final IEventService eventService;

    public NotificationFacade(ICheckoutService checkoutService, IOrderService orderService, ITicketService ticketService, ICustomerService customerService, IEventService eventService) {
        this.checkoutService = checkoutService;
        this.orderService = orderService;
        this.ticketService = ticketService;
        this.customerService = customerService;
        this.eventService = eventService;
    }

    public void updatePaymentStatus(Long orderId, PaymentCheckoutStatusEnum paymentStatus) {

        orderService.updateOrderStatus(orderId, mapToOrderStatus(paymentStatus));
        checkoutService.updateCheckoutPaymentStatusByOrderId(orderId, paymentStatus);

        if(paymentStatus.equals(PaymentCheckoutStatusEnum.PAID)) {
            Order order = orderService.findById(orderId);
            Customer customer = customerService.findById(order.getCustomerId());

            order.getItems().forEach(item -> {
                for (int i = 0; i < item.getTicketCategoryQuantity(); i++) {
                    EventDTO event = eventService.findById(item.getEventId());
                    Ticket ticket = new Ticket();

                    ticket.setStatus("ACTIVE");
                    ticket.setCustomerId(order.getCustomerId());
                    ticket.setCustomerDocument(customer.getDocument());
                    ticket.setCustomerName(customer.getName());
                    ticket.setEventId(item.getEventId());
                    ticket.setEventDate(event.getDate());
                    ticket.setEventTitle(event.getTitle());
                    ticket.setEventDescription(event.getDescription());
                    ticket.setTicketCategoryId(item.getTicketCategoryId());
                    ticket.setTicketCategoryPrice(item.getTicketCategoryPrice());
                    ticket.setTicketCategoryDescription(item.getTicketCategoryDescription());
                    ticket.setCreatedAt(LocalDateTime.now());

                    ticketService.save(ticket);
                }
            });
        }
    }

    public void updateCheckoutStatus(String checkoutId, Long orderId) {
        checkoutService.deleteCheckout(checkoutId);
        orderService.deleteOrder(orderId);
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
