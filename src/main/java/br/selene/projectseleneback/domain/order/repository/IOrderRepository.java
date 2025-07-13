package br.selene.projectseleneback.domain.order.repository;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.ItemOrder;

import java.util.List;

public interface IOrderRepository {

    Order findById(long orderId);
    Order save(Order order);
    List<Order> findAllByEvent(int eventId);
    List<Order> findAllByCustomer(int customerId);
    Order deleteById(long orderId);
}
