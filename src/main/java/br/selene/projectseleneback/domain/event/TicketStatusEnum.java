package br.selene.projectseleneback.domain.event;

public enum TicketStatusEnum {

	USED("USED"), UNUSED("UNUSED");

	private final String name;

	private TicketStatusEnum(String name) {
		this.name = name;
	}

	public boolean equals(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
