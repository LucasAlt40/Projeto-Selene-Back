package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.model.dto.LinkDTO;
import br.selene.projectseleneback.model.dto.RequestCheckoutDTO;
import br.selene.projectseleneback.model.dto.RequestGatewayDTO;
import br.selene.projectseleneback.model.dto.ResponseGatewayDTO;
import br.selene.projectseleneback.model.mappers.CheckoutMapper;
import br.selene.projectseleneback.service.CheckoutService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*")
@Slf4j
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
    public Void newLog(@RequestBody Object object){
        log.debug("payment_notification_urls\n",object.toString());
        return null;
    }
    @PostMapping("/notification_urls")
    public Void newLog2(@RequestBody Object object){

        log.debug("notification_urls\n",object.toString());
        return null;
    }



}
