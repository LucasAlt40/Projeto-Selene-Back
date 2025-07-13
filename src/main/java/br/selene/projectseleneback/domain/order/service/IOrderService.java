package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.dto.CreateTicketDTO;
import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.domain.order.dto.ResponseOrderDTO;

import java.util.List;

public interface IOrderService {
    ResponseOrderDTO create(RequestCreateOrderDTO request);
    void createItems(List<CreateTicketDTO> tickets);
    Order updateOrderStatus(Long id ,OrderStatusEnum status);
    Order deleteOrder(long orderId);
}
