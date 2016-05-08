package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.LabelApplyLog;

/**
 * 标签号段申请日志DB操作接口
 * @author wfj
 * @date   2015.6.1
 */
public interface LabelApplyLogMapper {

	void add(LabelApplyLog log);
	
	/**
	 * 查询申请状态变更日志
	 * @param map "t"=Log
	 */
	List<LabelApplyLog> findLogs(Map<String,Object> map);
}