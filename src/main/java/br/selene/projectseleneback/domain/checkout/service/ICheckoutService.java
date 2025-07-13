package br.selene.projectseleneback.domain.checkout.service;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import br.selene.projectseleneback.domain.checkout.dto.ResponseCreateCheckoutDTO;
import br.selene.projectseleneback.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICheckoutService {
    ResponseCreateCheckoutDTO createCheckout(Order order);

    // TODO Criar DTO'S para cada retorno
    Page<Checkout> findAll(Pageable pageable);
    Checkout findById(String checkoutId);
    Checkout save(Checkout checkout);
    List<Checkout> findCheckoutsByOrderId(int orderId);
    Checkout deleteCheckout(String checkoutId);
    Checkout deleteCheckoutByOrderId(long orderId);
    Checkout updateCheckoutPaymentStatusByOrderId(long orderId, PaymentCheckoutStatusEnum checkoutStatus);
    Checkout updateCheckoutPaymentStatus(String checkoutId, PaymentCheckoutStatusEnum checkoutStatus);
}
