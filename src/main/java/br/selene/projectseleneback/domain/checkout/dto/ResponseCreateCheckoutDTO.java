package br.selene.projectseleneback.domain.checkout.dto;

import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;

public record ResponseCreateCheckoutDTO(
        String id,
        String linkCheckout,
        CheckoutStatusEnum status
) {}
