package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.model.dto.RequestCheckoutNotificationDTO;

public interface IOrderService {
    void cancelOrder(RequestCheckoutNotificationDTO request);
}
