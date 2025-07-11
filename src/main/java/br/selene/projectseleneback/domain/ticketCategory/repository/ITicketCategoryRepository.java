package br.selene.projectseleneback.domain.ticketCategory.repository;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketCategoryRepository {

	public TicketCategory save(TicketCategory ticketCategory);

	public Page<TicketCategory> findAll(Pageable pageable);

	public TicketCategory findById(Long ticketCategoryId);

}
