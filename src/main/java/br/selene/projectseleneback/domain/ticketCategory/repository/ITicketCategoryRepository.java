package br.selene.projectseleneback.domain.ticketCategory.repository;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;

import org.springframework.data.domain.Page;

public interface ITicketCategoryRepository {

	public TicketCategory save(TicketCategory ticketCategory);

	public Page<TicketCategory> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);

	public TicketCategory findById(Long ticketCategoryId);

}
