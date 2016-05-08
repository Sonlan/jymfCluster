package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.AgentMonitor;
/**
 * 代理商管理信息DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface AgentMonitorMapper {

    int insert(AgentMonitor record);

    int insertSelective(AgentMonitor record);

    AgentMonitor selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(AgentMonitor record);

	List<AgentMonitor> query(Map<Object, Object> map);
	
	/**
	 * 查出最大的ID信息
	 */
	int findMaxId();
	
  
	/**
	 * 查询用户，通过用户名/密码
	 * @param map
	 * @return
	 */
    AgentMonitor selectByAccountAndPassword(Map<String, Object> map);
    
    /**
     * 根据用户名查找信息
     */
    AgentMonitor selectByAgentMonitor(String account);
    
    /**
     * 修改当前用户的密码
     */
    int updateByPrimaryKey(AgentMonitor record);

    
    /**
     * 查询全部代理商
     */
	List<AgentMonitor> findAll();
}