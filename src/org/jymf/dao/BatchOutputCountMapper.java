package org.jymf.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jymf.entity.BatchOutputCount;

public interface BatchOutputCountMapper {
	/**
	 * 首次统计时获取的查询区间
	 * @return
	 */
	List<Date> selectTimeSection(Map<Object, Object> map);
	
	/**
	 * 写入统计信息
	 * @param map
	 */
	void insertBatchCount(Map<Object, Object> map);

	List<BigDecimal> selectAllCompanyId();
	
	List<BatchOutputCount> selectHistoryCount(Map<Object, Object> map);
	
	void updateHistoryCount(BatchOutputCount batchOutputCnt);
}
