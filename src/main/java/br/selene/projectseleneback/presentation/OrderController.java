package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.order.Order;
import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.presentation.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/order") // TODO
public class OrderController {

    IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder (@RequestBody RequestCreateOrderDTO request){
        var response = new ApiResponse<>( true, "Pedido realizado com sucesso", orderService.create(request), null);
        return  ResponseEntity.ok(response);
    }

}
