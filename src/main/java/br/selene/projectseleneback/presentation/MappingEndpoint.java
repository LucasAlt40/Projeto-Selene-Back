package br.selene.projectseleneback.presentation;

public class MappingEndpoint {

	public static final String FIND = "/buscar";

	public class Customer {

		public static final String MAIN = "/usuario";
		public static final String FIND_ALL = FIND;

	}
	
	public class Event {
		
		public static final String MAIN = "/evento";
		public static final String FIND_ALL = FIND;
		
	}

}
