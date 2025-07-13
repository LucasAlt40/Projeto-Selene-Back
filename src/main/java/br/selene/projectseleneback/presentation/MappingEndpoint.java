package br.selene.projectseleneback.presentation;

public class MappingEndpoint {

	public static final String FIND = "/find";
	public static final String CREATE = "/add";
	public static final String UPDATE = "/update";

	public class Customer {

		public static final String MAIN = "/customer";

	}

	public class Event {

		public static final String MAIN = "/event";
		public static final String TICKET_CATEGORY = "/ticket-category";
		public static final String TICKET_CATEGORY_FIND = TICKET_CATEGORY + FIND;
		public static final String TICKET_CATEGORY_ADD = TICKET_CATEGORY + CREATE;

	}

}
