package br.selene.projectseleneback.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.selene.projectseleneback.infra.services.CheckoutService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @PostMapping("/new")
    public LinkDTO createCheckout(@RequestBody RequestCheckoutDTO request) throws InterruptedException, JsonProcessingException {

        RequestGatewayDTO requestCheckoutDTO = CheckoutMapper.toRequestGateway(request);

        ResponseGatewayDTO responseGatewayDTO =
                checkoutService
                        .criarCheckout(requestCheckoutDTO)
                        .block();

        return  responseGatewayDTO.links().get(1);
    }

    @PostMapping("/payment_notification_urls")
    public void newLog(@RequestBody Object object){
    }
    @PostMapping("/notification_urls")
    public void newLog2(@RequestBody Object object){

    }



}
