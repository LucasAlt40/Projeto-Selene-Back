package br.selene.projectseleneback.domain.event;

public enum EventStatusEnum {

	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String name;

	private EventStatusEnum(String name) {
		this.name = name;
	}

	public boolean equals(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
