package org.jymf.service;

import java.util.Map;

public interface ISysSetService {
	
	/**
	 * 获取运行状态
	 * @param name
	 * @return
	 *       -1  无效状态
	 *        0  首次运行
	 *        1  非首次运行 
	 */
	 int getRunStatus(String name);
    
	void updateValue(Map<String, Object> map);
	
	void insert(Map<String, Object> map);
}
