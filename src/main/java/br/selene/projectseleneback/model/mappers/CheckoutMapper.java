package br.selene.projectseleneback.model.mappers;

import br.selene.projectseleneback.model.dto.*;

import java.util.List;


public class CheckoutMapper {

    public static RequestGatewayDTO toRequestGateway(RequestCheckoutDTO dto) {

        return new RequestGatewayDTO(
                new Customer(
                        dto.customer().name(),
                        dto.customer().email(),
                        dto.customer().tax_id(),
                        new PhoneDTO(
                                dto.customer().phone().country(),
                                dto.customer().phone().area(),
                                dto.customer().phone().number()
                        )
                ),
                dto.items().stream()
                        .map(item -> new Items(
                                item.name(),
                                item.quantity(),
                                item.unit_amount(),
                                item.imageUrl()
                        ))
                        .toList()
                ,
                List.of(new PaymentMethodsDTO(dto.paymentMethod())),
                "http://localhost:4200/payment",
                List.of("http://localhost:8080/checkout/notification_urls"),
                List.of("http://localhost:8080/checkout/payment_notification_urls")
        );
    }
}