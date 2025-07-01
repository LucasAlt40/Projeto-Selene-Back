package br.selene.projectseleneback.domain.checkout.repository;

import br.selene.projectseleneback.domain.checkout.Checkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICheckoutRepository {
    Page<Checkout> findAll(Pageable pageable);
    Checkout findById(int checkoutId);
    Checkout save(Checkout checkout);
}
