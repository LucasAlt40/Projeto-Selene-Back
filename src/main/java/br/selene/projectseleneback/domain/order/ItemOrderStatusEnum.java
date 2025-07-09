package br.selene.projectseleneback.domain.order;

public enum ItemOrderStatusEnum {

	CANCELED("CANCELED"), PENDING("PENDING"), PAID("PAID");

	private final String name;

	private ItemOrderStatusEnum(String name) {
		this.name = name;
	}

	public boolean equals(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
