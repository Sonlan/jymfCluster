package org.jymf.dao;

import java.util.List;
import java.util.Map;
import org.jymf.entity.Logs;

/**
 * 日志DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface LogsMapper {
    int insert(Logs record);
    
	/**
	 * 分页
	 * @param map
	 * @return
	 */
	List<Logs> query(Map<Object, Object> map);
}