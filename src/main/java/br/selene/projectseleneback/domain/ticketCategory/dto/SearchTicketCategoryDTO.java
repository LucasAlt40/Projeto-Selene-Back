package br.selene.projectseleneback.domain.ticketCategory.dto;

import jakarta.validation.constraints.Min;

public class SearchTicketCategoryDTO {
	
	@Min(value = 1, message = "A página deve ser no mínimo 1")
    private Integer page;

    @Min(value = 1, message = "O tamanho da página deve ser no mínimo 1")
    private Integer pageSize;

	public SearchTicketCategoryDTO() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
