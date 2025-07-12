package br.selene.projectseleneback.infra.mappers;

import br.selene.projectseleneback.domain.checkout.PaymentMethodsCheckoutEnum;
import br.selene.projectseleneback.domain.checkout.dto.*;
import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderToRequestCheckout {

    public static RequestCheckoutDTO from(Order order, Customer customer, String redirect_url, List<String> notification_urls, List<String> payment_notification_urls) {

        // TODO fazer conversão de uma melhor forma
        var phone = customer.getPhone();
        var splitPhone = phone.split("\\s+");

        var items = order.getItems();


        var customerDto =  new CustomerCheckoutDTO(customer.getName(), customer.getEmail(), customer.getDocument(), new PhoneCustomerCheckoutDTO(splitPhone[0], splitPhone[1], splitPhone[2]));

        List<ItemsCheckoutDTO> itemOrders = new ArrayList<>();
        items.forEach(item -> {
            itemOrders.add(new ItemsCheckoutDTO(item.getTicketCategoryDescription(), item.getTicketCategoryQuantity(), item.getTicketCategoryPrice(), ""));
        });

        List<PaymentMethodCheckoutDTO> paymentMethodOrders = new ArrayList<>();

        for (PaymentMethodsCheckoutEnum method : PaymentMethodsCheckoutEnum.values()) {
           String name =  method.name();
           var dto = new PaymentMethodCheckoutDTO(name);
           paymentMethodOrders.add(dto);
        }


        return new RequestCheckoutDTO(
                customerDto,
                itemOrders,
                paymentMethodOrders,
                redirect_url,
                notification_urls,
                payment_notification_urls
        );
    }
}


