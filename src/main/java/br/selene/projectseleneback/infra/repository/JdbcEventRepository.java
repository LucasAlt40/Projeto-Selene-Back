package br.selene.projectseleneback.infra.repository;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEventRepository implements IEventRepository {

    @Override
    public Iterable<Event> findAll() {
        return null;
    }

    @Override
    public Event findById(int eventId) {
        return null;
    }
}
