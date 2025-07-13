package br.selene.projectseleneback.domain.event.dto;

import jakarta.validation.constraints.Min;

public class SearchEventDTO {
	@Min(value = 1, message = "A página deve ser no mínimo 1")
	private Integer page;

	@Min(value = 1, message = "O tamanho da página deve ser no mínimo 1")
	private Integer pageSize;

	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	public SearchEventDTO() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageOrDefault() {
		return (page != null && page >= 1) ? page : DEFAULT_PAGE;
	}

	public int getPageSizeOrDefault() {
		return (pageSize != null && pageSize >= 1) ? pageSize : DEFAULT_PAGE_SIZE;
	}
}
