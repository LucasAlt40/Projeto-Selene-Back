package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.dto.CreateTicketDTO;
import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;

import java.util.List;

public interface IOrderService {
    Order create(RequestCreateOrderDTO request);
    void createItems(List<CreateTicketDTO> tickets);
    void updateOrderStatus(OrderStatusEnum status);
}
