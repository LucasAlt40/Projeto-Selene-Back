package br.selene.projectseleneback.domain.checkout;

public class Checkout {
    private String id;
    private int idOrder;
    private String paymentLink;
    private CheckoutStatusEnum status;
    private PaymentCheckoutStatusEnum paymentStatus;

    public Checkout() {}

    public Checkout(String id, int idOrder, String paymentLink, CheckoutStatusEnum status, PaymentCheckoutStatusEnum paymentStatus) {
        this.id = id;
        this.idOrder = idOrder;
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

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
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
