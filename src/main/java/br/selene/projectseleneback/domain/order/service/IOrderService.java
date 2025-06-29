package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.presentation.dto.notification.RequestCheckoutNotificationDTO;

public interface IOrderService {
    void updateOrderStatus(RequestCheckoutNotificationDTO request);
}
