package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.LogsMapper;
import org.jymf.entity.Logs;
import org.jymf.service.ILogsService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志操作实现类
 * @author lxg
 * @date   2014年7月29日
 */
@Service("logsService")
public class LogsServiceImpl implements ILogsService{

	@Autowired
	private LogsMapper logsDao;
	
	/**
	 * 
	 * 日志信息,分页,模糊查询
	 */
	@Override
	public PageView query(PageView pageView, Logs logs) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", logs);
		
		List<Logs> list = logsDao.query(map);
		pageView.setRecords(list);
		return pageView;
	}
}
