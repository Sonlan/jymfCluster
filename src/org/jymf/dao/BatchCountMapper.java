package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.BatchCount;

public interface BatchCountMapper {

	/**
	 * 获取所有已经注册的公司的ID
	 * @return
	 */
	List<BigDecimal> selectAllCompanyId();
	
	/**
	 * 首次统计时获取的查询区间
	 * @return
	 */
	BatchCount selectTimeSection(Map<Object, Object> map);
	
	/**
	 * 写入统计信息
	 * @param map
	 */
	void insertBatchCount(Map<Object, Object> map);
	
	void insertBatchCarCount(Map<Object, Object> map);
}
