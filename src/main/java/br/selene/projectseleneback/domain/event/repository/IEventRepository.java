package br.selene.projectseleneback.domain.event.repository;

import br.selene.projectseleneback.domain.event.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventRepository {

	public Iterable<Event> findAll();

	public Page<Event> findAll(Pageable pageable);

	public Event findById(int id);

	public Event save(Event event);

}
