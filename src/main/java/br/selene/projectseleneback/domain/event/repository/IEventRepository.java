package br.selene.projectseleneback.domain.event.repository;

import br.selene.projectseleneback.domain.event.Event;

public interface IEventRepository {

	public Iterable<Event> findAll();
	
	public Event findById(int eventId);


	
}
