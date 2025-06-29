package br.selene.projectseleneback.infra.repository;

import org.springframework.stereotype.Repository;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;

@Repository
public class JdbcTicketCategortRepository implements ITicketCategoryRepository {

	@Override
	public TicketCategory save(TicketCategory ticketCategory) {
		// TODO Auto-generated method stub
		return null;
	}

}
