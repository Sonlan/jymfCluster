package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AgentMonitorRelationMapper;
import org.jymf.entity.AgentMonitorRelation;
import org.jymf.service.IAgentMonitorRelationService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标签索引操作实现类
 * @author lxg
 * @date   2014年05月09日
 */
@Service("agentMonitorRelationService")
public class AgentMonitorRelationServiceImpl implements IAgentMonitorRelationService{

	@Autowired
    private AgentMonitorRelationMapper agentMonitorRelationDao;
	
	@Override
	public PageView query(PageView pageView, AgentMonitorRelation aentMonitorRelation) {
		 Map<Object, Object> map = new HashMap<Object, Object>();
	     map.put("paging", pageView);
	     map.put("t", aentMonitorRelation);
	        
	     List<AgentMonitorRelation> list = agentMonitorRelationDao.query(map);
	     pageView.setRecords(list);
	     return pageView;
	}
	
	/**
	 * 添加代理商关系
	 */
	@Override
	public void add(AgentMonitorRelation agentMonitorRelation) {
		agentMonitorRelationDao.insert(agentMonitorRelation);
	}
		
	/**
	 * 更改当前状态
	 */
	@Override
	public int updateStatus(long id){        
		return agentMonitorRelationDao.updateStatus(id);
	}
}
