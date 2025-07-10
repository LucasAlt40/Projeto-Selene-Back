package br.selene.projectseleneback.domain.checkout;

import br.selene.projectseleneback.domain.order.Order;

public class Checkout {

    private String id;
    private long orderId;
    private String paymentLink;
    private CheckoutStatusEnum status;
    private PaymentCheckoutStatusEnum paymentStatus;
    private PaymentMethodsCheckoutEnum paymentMethod;

	public Checkout() {}

	public Checkout(String id, long order, String paymentLink, CheckoutStatusEnum status, PaymentCheckoutStatusEnum paymentStatus) {
		this.id = id;
		this.orderId = order;
		this.paymentLink = paymentLink;
		this.status = status;
		this.paymentStatus = paymentStatus;
	}

    public PaymentMethodsCheckoutEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodsCheckoutEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getOrder() {
		return orderId;
	}

	public void setOrder(long order) {
		this.orderId = order;
	}

	public String getPaymentLink() {
		return paymentLink;
	}

	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}

	public CheckoutStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CheckoutStatusEnum status) {
		this.status = status;
	}

	public PaymentCheckoutStatusEnum getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentCheckoutStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
