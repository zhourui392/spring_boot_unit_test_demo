package cn.teleus.net.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	T selectByPrimaryKey(Long id);
	
	T selectByPrimaryKey(Integer id);
	
	T selectByPrimaryKey(String id);

	int deleteByPrimaryKey(Long id);
	
	int deleteByPrimaryKey(Integer id);

	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	int updateByPrimaryKey(T record);
	
	int updateByPrimaryKeySelective(T record);

	int getCountByConditions(Map<String, Object> conditions);

	<T> List<T> getListByConditions(Map<String, Object> conditions);
}
