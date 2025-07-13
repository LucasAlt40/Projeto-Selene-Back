package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.domain.event.service.IEventService;
import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.dto.*;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
	IOrderRepository orderRepository;
	IEventService eventService;
	ICheckoutService checkoutService;

	public OrderService(IOrderRepository orderRepository, IEventService eventService, ICheckoutService checkoutService) {
		this.orderRepository = orderRepository;
		this.eventService = eventService;
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
			var ticketCategory = eventService.findTicketCategoryById(ticket.categoryId());
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
    public Order updateOrderStatus(Long id, OrderStatusEnum status) {
		Order order = orderRepository.findById(id);
		if(status.equals(OrderStatusEnum.EXPIRED)) {
			for (ItemOrder itemOrder : order.getItems()) {
				releaseTicket(itemOrder);
			}
		}
        return orderRepository.updateOrderStatus(id,status);
    }

	@Override
	public Order deleteOrder(Long orderId) {
		Order orderDelete = orderRepository.deleteById(orderId);

		for (ItemOrder itemOrder : orderDelete.getItems()) {
			releaseTicket(itemOrder);
		}
		checkoutService.deleteCheckoutByOrderId(orderId);

		return orderDelete;
	}

	private void reserveTicket(ItemOrder itemOrder) {
		eventService.reserveTicket(itemOrder.getTicketCategoryId(), itemOrder.getTicketCategoryQuantity());
	}

	private void releaseTicket(ItemOrder itemOrder) {
		eventService.releaseTicket(itemOrder.getTicketCategoryId(), itemOrder.getTicketCategoryQuantity());
	}
}


