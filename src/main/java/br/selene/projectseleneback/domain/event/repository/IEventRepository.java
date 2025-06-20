package br.selene.projectseleneback.domain.event.repository;

import java.util.List;

import br.selene.projectseleneback.domain.event.Event;

public interface IEventRepository {

	public List<Event> findAll();
	
	public Event findById(int eventId);
	
}
