package br.selene.projectseleneback.domain.event;

public enum TicketOrderStatusEnum {

	CANCELED("CANCELED"), PENDING("PENDING"), PAID("PAID");

	private final String name;

	private TicketOrderStatusEnum(String name) {
		this.name = name;
	}

	public boolean equals(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
