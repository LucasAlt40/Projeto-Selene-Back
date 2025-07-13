package br.selene.projectseleneback.domain.checkout.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import br.selene.projectseleneback.domain.checkout.PaymentCheckoutStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICheckoutRepository {
    Page<Checkout> findAll(Pageable pageable);
    Checkout findById(String checkoutId);
    Checkout save(Checkout checkout);
    List<Checkout> findCheckoutsByOrderId(long orderId);
    Checkout deleteCheckoutById(String checkoutId);
    Checkout deleteCheckoutByOrderId(long orderId);
    Checkout updateCheckoutPaymentStatusByOrderId(long orderId, PaymentCheckoutStatusEnum checkoutStatus);
    Checkout updateCheckoutPaymentStatus(String checkoutId, PaymentCheckoutStatusEnum checkoutStatus);
}
