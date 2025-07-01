package br.selene.projectseleneback.domain.order.repository;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.TicketOrder;

import java.util.List;

public interface IOrderRepository {

    Order findById(int orderId);
    // TODO salvar ordem header e items e atualizar quantidade de tickets disponivel no avaliable
    Boolean save(Order order);
    List<TicketOrder> findItemsByOrderId(int orderId);
}
