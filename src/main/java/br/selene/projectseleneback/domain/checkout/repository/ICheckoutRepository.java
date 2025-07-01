package br.selene.projectseleneback.domain.checkout.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICheckoutRepository {
    Page<Checkout> findAll(Pageable pageable);
    Checkout findById(String checkoutId);
    Checkout save(Checkout checkout);
    List<Checkout> findCheckoutsByOrderId(int orderId);
}
