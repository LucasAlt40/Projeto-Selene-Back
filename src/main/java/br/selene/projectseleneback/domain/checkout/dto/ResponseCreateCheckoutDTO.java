package br.selene.projectseleneback.domain.checkout.dto;

import br.selene.projectseleneback.domain.checkout.CheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;

import java.util.List;

public record ResponseCreateCheckoutDTO(
        String id,
        List<ResponseLinksDTO> links,
        CheckoutStatusEnum status
) {}

