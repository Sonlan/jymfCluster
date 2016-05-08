package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.CompanyCount;

public interface CompanyCountMapper {
	/**
	 * 分页,企业内部统计查询
	 * @param map
	 * @return
	 */
	List<CompanyCount> queryCompanyCount(Map<Object, Object> map);
	
	/**
	 * 获取指定时间范围内，企业标签使用情况
	 * @param map
	 * @return
	 */
	CompanyCount labelCompanyCount(Map<Object, Object> map);
}
