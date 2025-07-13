package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.dto.*;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import br.selene.projectseleneback.domain.ticketCategory.service.ITicketCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    IOrderRepository orderRepository;
    ITicketCategoryService ticketCategoryService;
    ICheckoutService checkoutService;

    public OrderService(IOrderRepository orderRepository, ITicketCategoryService ticketCategoryService, ICheckoutService checkoutService) {
        this.orderRepository = orderRepository;
        this.ticketCategoryService = ticketCategoryService;
        this.checkoutService = checkoutService;
    }

    @Override
    @Transactional
    public ResponseOrderDTO create(RequestCreateOrderDTO request) {

        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setStatus(OrderStatusEnum.WAITING_PAYMENT);

        List<ItemOrder> items = new ArrayList<>();

        for (CreateTicketDTO ticket : request.tickets()) {
            var ticketCategory = ticketCategoryService.findById(ticket.categoryId());
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setEventId(ticket.eventId());
            itemOrder.setTicketCategoryId(ticket.categoryId());
            itemOrder.setTicketCategoryPrice(ticketCategory.getPrice());
            itemOrder.setTicketCategoryDescription(ticketCategory.getDescription());
            itemOrder.setTicketCategoryQuantity(ticket.quantity());
            reserveTicket(itemOrder);
            items.add(itemOrder);
        }

        order.setItems(items);

        var createdOrder = orderRepository.save(order);

        var checkout = checkoutService.createCheckout(createdOrder);

        OrderDTO orderDTO = new OrderDTO(
                createdOrder.getId(),
                createdOrder.getItems().stream().map(item ->
                        new ItemOrderDTO(
                                item.getTicketCategoryDescription(),
                                item.getTicketCategoryQuantity(),
                                item.getEventId(),
                                item.getTicketCategoryPrice()
                        )
                ).toList(),
                createdOrder.getStatus().name()
        );

        return new ResponseOrderDTO(orderDTO, checkout);
    }


    @Override
    public void createItems(List<CreateTicketDTO> tickets) {

    }

    @Override
    public Order updateOrderStatus(Long id,OrderStatusEnum status) {
        return orderRepository.updateOrderStatus(id,status);
    }

    @Override
    public Order deleteOrder(long orderId) {
        Order orderDelete = orderRepository.deleteById(orderId);

        for (ItemOrder itemOrder : orderDelete.getItems()) {
            releaseTicket(itemOrder);
        }
        checkoutService.deleteCheckoutByOrderId(orderId);

        return orderDelete;
    }

    private void reserveTicket(ItemOrder itemOrder) {
       ticketCategoryService.reserveTicket(itemOrder.getTicketCategoryId(), itemOrder.getTicketCategoryQuantity());
    }

    private void releaseTicket(ItemOrder itemOrder) {
        ticketCategoryService.releaseTicket(itemOrder.getTicketCategoryId(), itemOrder.getTicketCategoryQuantity());
    }
}

