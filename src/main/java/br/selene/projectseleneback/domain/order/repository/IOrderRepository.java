package br.selene.projectseleneback.domain.order.repository;

import br.selene.projectseleneback.domain.order.Order;

public interface IOrderRepository {

    Order findById(int id);
}
