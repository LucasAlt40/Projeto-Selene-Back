package br.selene.projectseleneback.domain.checkout;

import br.selene.projectseleneback.domain.order.Order;

public class Checkout {

	private String id;
	private Order order;
	private String paymentLink;
	private CheckoutStatusEnum status;
	private PaymentCheckoutStatusEnum paymentStatus;

	public Checkout() {
	}

	public Checkout(String id, Order order, String paymentLink, CheckoutStatusEnum status,
			PaymentCheckoutStatusEnum paymentStatus) {
		this.id = id;
		this.order = order;
		this.paymentLink = paymentLink;
		this.status = status;
		this.paymentStatus = paymentStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
