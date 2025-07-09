package br.selene.projectseleneback.domain.checkout;

public enum CheckoutStatusEnum {

	EXPIRED("EXPIRED"), IN_PROCESS("IN_PROCESS");

	private final String name;

	private CheckoutStatusEnum(String name) {
		this.name = name;
	}

	public boolean equals(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
