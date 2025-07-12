package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.order.ItemOrder;
import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.dto.CreateTicketDTO;
import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.repository.IOrderRepository;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    IOrderRepository orderRepository;
    ITicketCategoryRepository ticketCategoryRepository;

    public OrderService(IOrderRepository orderRepository, ITicketCategoryRepository ticketCategoryRepository) {
        this.orderRepository = orderRepository;
        this.ticketCategoryRepository = ticketCategoryRepository;
    }

    @Override
    @Transactional
    public Order create(RequestCreateOrderDTO request) {

        Order order = new Order();
        order.setCustomerId(request.customerId());

        List<ItemOrder> items = new ArrayList<>();


        for (CreateTicketDTO ticket : request.tickets()) {
            var ticketCategory = ticketCategoryRepository.findById(ticket.categoryId());
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setEventId(ticket.eventId());
            itemOrder.setTicketCategoryId(ticket.categoryId());
            itemOrder.setTicketCategoryPrice(ticketCategory.getPrice());
            itemOrder.setTicketCategoryDescription(ticketCategory.getDescription());
            itemOrder.setTicketCategoryQuantity(ticket.quantity());

            items.add(itemOrder);
        }

        order.setItems(items);

        return orderRepository.save(order);
    }

    @Override
    public void createItems(List<CreateTicketDTO> tickets) {

    }

    @Override
    public void updateOrderStatus(OrderStatusEnum status) {

    }
}
