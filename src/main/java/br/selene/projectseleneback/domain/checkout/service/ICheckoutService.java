package br.selene.projectseleneback.domain.checkout.service;

import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.order.Order;

public interface ICheckoutService {

    ResponseCreateCheckoutDTO createCheckout(Order order);
}
