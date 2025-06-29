package br.selene.projectseleneback.domain.order.repository;

import br.selene.projectseleneback.domain.order.Order;

public interface IOrderRepository {

    Order findById(int id);
    // TODO salvar ordem header e items e atualizar quantidade de tickets disponivel no avaliable
    Boolean save(Order order);
}
