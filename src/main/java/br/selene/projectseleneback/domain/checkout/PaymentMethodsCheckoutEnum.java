package br.selene.projectseleneback.domain.checkout;

public enum PaymentMethodsCheckoutEnum {
    CREDIT_CARD("CREDIT_CARD");

    private final String name;

    private PaymentMethodsCheckoutEnum(String name) {
        this.name = name;
    }

    public boolean equals(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
