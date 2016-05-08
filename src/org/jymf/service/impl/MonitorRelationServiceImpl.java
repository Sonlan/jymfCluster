package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.MonitorRelationMapper;
import org.jymf.entity.MonitorRelation;
import org.jymf.service.IMonitorRelationService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 监管管理操作实现类
 * @author cqs
 * @date   2014年05月09日
 */
@Service("monitorRelationService")
public class MonitorRelationServiceImpl implements IMonitorRelationService {

	@Autowired
	private MonitorRelationMapper monitorRelationDao;

	@Override
	public MonitorRelation findById(long id) {
		 return monitorRelationDao.selectByPrimaryKey(new BigDecimal(id));
	}

	@Override
	public PageView query(PageView pageView, MonitorRelation monitor) {
		 Map<Object, Object> map = new HashMap<Object, Object>();
	        map.put("paging", pageView);
	        map.put("t", monitor);
	        
	        List<MonitorRelation> list = monitorRelationDao.query(map);
	        pageView.setRecords(list);
	        return pageView;
	}

	@Override
	public void update(MonitorRelation monitorRelation) {
		monitorRelationDao.updateByPrimaryKeySelective(monitorRelation);
	}

	@Override
	public void add(MonitorRelation monitorRelation) {
		monitorRelationDao.insert(monitorRelation);
	}
	
	/**
	 * 更改当前状态0
	 */
	@Override
	public int updateStatus(long id){        
		return monitorRelationDao.updateStatus(new BigDecimal(id));
	}
}
