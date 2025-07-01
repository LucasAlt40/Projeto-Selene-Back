package br.selene.projectseleneback.presentation;

public class MappingEndpoint {

	public static final String FIND = "/buscar";

	public class Customer {

		public static final String MAIN = "/usuario";
		public static final String FIND_ALL = FIND;
		public static final String CREATE = "cadastrar";
		public static final String UPDATE = "atualizar";

	}
	
	public class Event {
		
		public static final String MAIN = "/evento";
		public static final String FIND_ALL = FIND;
		
	}

	public class TicketCategory {

		public static final String MAIN = "/categoria-ingresso";
		public static final String FIND_ALL = FIND;
		public static final String CREATE = "cadastrar";
		public static final String UPDATE = "atualizar";
	}

}
