package org.jymf.service.impl;

import java.util.Map;

import org.jymf.dao.SysSetMapper;
import org.jymf.service.ISysSetService;
import org.jymf.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysSetService")
public class SysSetServiceImpl implements ISysSetService {

	@Autowired
	private SysSetMapper sysSetDao;
	
	@Override
	public int getRunStatus(String name) {
		
		String value = sysSetDao.selectByName(Constants.BATCH_LABEL_RUN);
		if(null == value || value.equals("") || value.equals("close")){
			return -1;
		}else{
			if(value.equals("true")){
				return 0;
			}else{
				return 1;
			}
		}
		
	}

	@Override
	public void updateValue(Map<String, Object> map) {
		sysSetDao.updateValue(map);
	}

	@Override
	public void insert(Map<String, Object> map) {
		sysSetDao.insert(map);
	}

	
}
