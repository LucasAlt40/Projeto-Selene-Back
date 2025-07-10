package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TicketCategoryService{

    private final ITicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryService(ITicketCategoryRepository ticketCategoryRepository) {
        this.ticketCategoryRepository = ticketCategoryRepository;
    }

    public Page<TicketCategory> findAll(Pageable pageable){
        // TO-DO abstract and simplify pagination
        if(pageable == null) {
            pageable = new Pageable() {

                @Override
                public int getPageNumber() {
                    return 1;
                }

                @Override
                public int getPageSize() {
                    return 10;
                }

                @Override
                public long getOffset() {
                    return 0;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public Pageable next() {
                    return null;
                }

                @Override
                public Pageable previousOrFirst() {
                    return null;
                }

                @Override
                public Pageable first() {
                    return null;
                }

                @Override
                public Pageable withPage(int pageNumber) {
                    return null;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

            };
        }
        return ticketCategoryRepository.findAll(pageable);
    }

    public TicketCategory create(CreateTicketCategoryDTO createTicketCategoryDTO) {
        TicketCategory newTicketCategory = new TicketCategory();

        newTicketCategory.setPrice(createTicketCategoryDTO.price());
        newTicketCategory.setDescription(createTicketCategoryDTO.description());
        newTicketCategory.setQuantity(createTicketCategoryDTO.quantity());

        return ticketCategoryRepository.save(newTicketCategory);
    }

    public TicketCategory update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO) {
        TicketCategory newTicketCategory = new TicketCategory();

        newTicketCategory.setId(TicketCategoryId);
        newTicketCategory.setPrice(updateTicketCategoryDTO.price());
        newTicketCategory.setDescription(updateTicketCategoryDTO.description());
        newTicketCategory.setQuantity(updateTicketCategoryDTO.quantity());

        return ticketCategoryRepository.save(newTicketCategory);
    }
}
