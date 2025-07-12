package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.order.dto.RequestCreateOrderDTO;
import br.selene.projectseleneback.domain.order.service.IOrderService;
import br.selene.projectseleneback.domain.order.dto.ResponseOrderDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/order") // TODO
public class OrderController {

    IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseOrderDTO createOrder (@RequestBody RequestCreateOrderDTO request){
        return orderService.create(request);
    }

}
