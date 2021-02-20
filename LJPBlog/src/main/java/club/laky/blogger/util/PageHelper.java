package club.laky.blogger.util;

import java.util.List;

public class PageHelper<T> {
	// 总数
	private int totolCount;
	// 总页数
	private int totalPage;
	// 当前页面
	private int page;
	// 单页面数量
	private int limit;
	// 数据
	private List<T> data;
	
	public PageHelper(int totolCount, int page, int limit, List<T> data) {
		this.totolCount = totolCount;
		
		totalPage = totolCount;

		if(limit != 0 && page != 0) {
			if (totolCount % limit != 0) {
				totalPage = totolCount / limit + 1;
			} else {
				totalPage = totolCount / limit;
			}
			if (page > totalPage || page < 0) {
				this.page = totalPage + 1;
			}
		}else {
			totalPage = 0;
		}
		this.page = page;
		this.limit = limit;
		this.data = data;
	}

	public PageHelper() {
	}

	public int getTotolCount() {
		return totolCount;
	}

	public void setTotolCount(int totolCount) {
		this.totolCount = totolCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
