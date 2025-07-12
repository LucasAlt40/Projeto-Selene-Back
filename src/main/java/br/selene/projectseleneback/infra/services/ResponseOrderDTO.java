package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.order.Order;

public record ResponseOrderDTO(Order order, ResponseCreateCheckoutDTO checkout) {}
