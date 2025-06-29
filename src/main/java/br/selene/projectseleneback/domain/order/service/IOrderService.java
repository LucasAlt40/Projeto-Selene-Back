package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.presentation.dto.RequestCheckoutNotificationDTO;

public interface IOrderService {
    void updateOrderStatus(RequestCheckoutNotificationDTO request);
}
