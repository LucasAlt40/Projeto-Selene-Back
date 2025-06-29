package br.selene.projectseleneback.domain.order;

public enum OrderStatusEnum {
    EXPIRED("EXPIRED");

    private final String name;

    private OrderStatusEnum(String name) {
        this.name = name;
    }

    public boolean equals(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
