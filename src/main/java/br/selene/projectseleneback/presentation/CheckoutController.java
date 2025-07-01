package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.service.ICheckoutService;
import br.selene.projectseleneback.infra.services.CheckoutService;

import br.selene.projectseleneback.presentation.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    private final ICheckoutService checkoutService;

    public CheckoutController(ICheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Checkout>> getById(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Sucesso ao obter checkout", this.checkoutService.findById(id), null));
    }


//    @PostMapping("/new")
//    public LinkDTO createCheckout(@RequestBody RequestCheckoutDTO request) throws InterruptedException, JsonProcessingException {
//
//        RequestGatewayDTO requestCheckoutDTO = CheckoutMapper.toRequestGateway(request);
//
//        ResponseGatewayDTO responseGatewayDTO =
//                checkoutService
//                        .criarCheckout(requestCheckoutDTO)
//                        .block();
//
//        return  responseGatewayDTO.links().get(1);
//    }
//
//    @PostMapping("/payment_notification_urls")
//    public void newLog(@RequestBody Object object){
//    }
//    @PostMapping("/notification_urls")
//    public void newLog2(@RequestBody Object object){
//
//    }



}
