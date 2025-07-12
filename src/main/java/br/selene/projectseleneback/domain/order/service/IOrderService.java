package br.selene.projectseleneback.domain.order.service;

import br.selene.projectseleneback.domain.order.dto.CreateTicketDTO;
import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.OrderStatusEnum;
import br.selene.projectseleneback.infra.services.ResponseOrderDTO;

import java.util.List;

public interface IOrderService {
    ResponseOrderDTO create(RequestCreateOrderDTO request);
    void createItems(List<CreateTicketDTO> tickets);
    void updateOrderStatus(OrderStatusEnum status);
}
