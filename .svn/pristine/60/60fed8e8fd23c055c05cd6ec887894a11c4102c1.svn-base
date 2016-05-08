package org.jymf.service.impl;

import java.math.BigDecimal;

import org.core.modules.mapper.JsonMapper;
import org.jymf.dao.LogsMapper;
import org.jymf.entity.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BusinessLogger{
	// 业务日志的logger
	private static Logger businessLogger = LoggerFactory.getLogger("business");
	private final JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
    @Autowired
    private LogsMapper logsDao;
    	
	public void log(String action, String user,BigDecimal companyId, String logStr, Object bean) {
		businessLogger.info("{},{},{}", action, user, jsonMapper.toJson(bean));
		Logs record = new Logs();
		record.setUserId(user);
		record.setEvent(action);
		record.setDescription(logStr);
		record.setCompanyId(companyId);
		
		logsDao.insert(record);
	}
	
	public void log(String logStr) {
		businessLogger.info(logStr);
	}
	
	public void log(String action, String user, Object bean) {
		businessLogger.info("{},{},{}", action, user, jsonMapper.toJson(bean));
	}
}
