package br.selene.projectseleneback.domain.checkout.service;

import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;

public interface ICheckoutService {

    ResponseCreateCheckoutDTO createCheckout (int OrderId);
}
