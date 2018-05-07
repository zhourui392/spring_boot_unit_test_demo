package cn.teleus.common.page;

import java.util.HashMap;
import java.util.Map;

public class PageQuery {
	
	public static int DEFAULT_PAGE_SIZE = 15;
	
	public PageQuery(){
		this.pageIndex = 1;
	}
	protected Integer pageIndex;
	protected int limit = DEFAULT_PAGE_SIZE;
	private Map<String,Object> pageCondition = new HashMap<>();
	public int getPageOffset() {
		return (this.pageIndex-1)<0?0:(this.pageIndex-1)*this.limit;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
	@Override
	public String toString() {
		return "PageQuery [pageIndex=" + pageIndex + ", limit=" + limit + "]";
	}

	public void addContion(String key,Object value){
		pageCondition.put(key,value);
	}

	public Map<String, Object> getPageCondition() {
		return pageCondition;
	}
}
