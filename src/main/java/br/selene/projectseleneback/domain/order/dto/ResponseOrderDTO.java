package br.selene.projectseleneback.domain.order.dto;

import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.order.Order;

public record ResponseOrderDTO(
        OrderDTO order,
        ResponseCreateCheckoutDTO checkout
) {}



