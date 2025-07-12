package br.selene.projectseleneback.presentation.utils;

import java.util.List;
import org.springframework.data.domain.Page;

public class CustomPage<T> {

	private List<T> content;
	private CustomPageable pageable;

	public CustomPage() {
	}

	public CustomPage(Page<T> page) {
		this.content = page.getContent();
		this.pageable = new CustomPageable(
			page.getNumber() + 1,
			page.getSize(),
			page.getTotalElements(),
			page.getTotalPages()
		);
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public CustomPageable getPageable() {
		return pageable;
	}

	public void setPageable(CustomPageable pageable) {
		this.pageable = pageable;
	}

	public static class CustomPageable {
		private int page;
		private int pageSize;
		private long total;
		private int totalPages;

		public CustomPageable() {}

		public CustomPageable(int page, int pageSize, long total, int totalPages) {
			this.page = page;
			this.pageSize = pageSize;
			this.total = total;
			this.totalPages = totalPages;
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

		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
	}
}
