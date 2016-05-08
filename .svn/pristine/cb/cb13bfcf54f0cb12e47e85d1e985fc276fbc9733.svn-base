package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AgentMonitorMapper;
import org.jymf.entity.AgentMonitor;
import org.jymf.service.IAgentMonitorService;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代理商管理操作实现类
 * @author lxg
 * @date   2014年07月16日
 */
@Service("agentMonitorService")
public class AgentMonitorServiceImpl implements IAgentMonitorService{

	@Autowired
	private AgentMonitorMapper agentMonitorDao;

    @Override
    public PageView query(PageView pageView, AgentMonitor agentMonitor) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", agentMonitor);
        
        List<AgentMonitor> list = agentMonitorDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public AgentMonitor findById(long id) {
        return agentMonitorDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(AgentMonitor agentMonitor) {
    	agentMonitorDao.updateByPrimaryKeySelective(agentMonitor);
    }

    @Override
    public void add(AgentMonitor agentMonitor) {
    	agentMonitorDao.insert(agentMonitor);
    }
    
    @Override
    public int findMaxId(){
    	return agentMonitorDao.findMaxId();
    }

    /**
     * 通过用户名查询密码
     */
    @Override
    public AgentMonitor findAgent(AgentMonitor agentMonitor){
		try {
			agentMonitor.setPwd(MD5.getInstance().encrypt(agentMonitor.getPwd()));
		} catch (Exception e) {
			return null;
		}  		
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("account", agentMonitor.getAccount());
		map.put("pwd", agentMonitor.getPwd());
	
		return agentMonitorDao.selectByAccountAndPassword(map);	    	
    }
    
    /**
     * 通过用户名查找用户
     */
    @Override
    public AgentMonitor selectAgentMonitor(String account){
    	return agentMonitorDao.selectByAgentMonitor(account);
    }
    
    /**
     * 修改当前用户的密码
     */
    @Override
    public void updateByPrimaryKey(AgentMonitor agentMonitor){
		try {
			agentMonitor.setPwd(MD5.getInstance().encrypt(agentMonitor.getPwd()));
		}catch(Exception e){
			return;
		}
    	agentMonitorDao.updateByPrimaryKey(agentMonitor);
    }

	@Override
	public List<AgentMonitor> findAll() {
		return agentMonitorDao.findAll();
	}
}
