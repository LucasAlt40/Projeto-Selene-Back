package br.selene.projectseleneback.domain.customer.dto;

public class SearchCustomerDTO {

	private int page;
	private int pageSize;
	
	public SearchCustomerDTO() {
	}

	public SearchCustomerDTO(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
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
