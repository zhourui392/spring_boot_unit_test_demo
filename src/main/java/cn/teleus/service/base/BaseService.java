package cn.teleus.service.base;

import cn.teleus.common.page.PageQuery;
import cn.teleus.common.page.PageResult;

public interface BaseService<T> {
	
	T getById(Long id);
	
	T getById(Integer id);

	T getById(String id);

	Boolean update(T record);

	Boolean deleteById(Long id);
	
	Boolean deleteById(Integer id);

	Boolean deleteById(String id);

	Boolean add(T record);

	PageResult<T> getPageList(PageQuery pageQuery);
}
