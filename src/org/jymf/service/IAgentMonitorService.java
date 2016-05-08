package org.jymf.service;

import java.util.List;

import org.jymf.entity.AgentMonitor;
import org.jymf.utils.PageView;


/**
 * 代理商管理操作接口
 * @author lxg
 * @date   2014年07月16日
 */
public interface IAgentMonitorService {
	 /**
     * 通过主键获取记录
     * @param id
     * @return
     */
	AgentMonitor findById(long id);
    /**
     * 分页处理
     * @param pageView
     * @param agentMonitor
     * @return
     */
    PageView query(PageView pageView, AgentMonitor agentMonitor);
    /**
     * 监管管理信息
     * @param agentMonitor
     */
    void update(AgentMonitor agentMonitor);
    
    /**
     * 添加监管管理
     * @param agentMonitor
     */
    void add(AgentMonitor agentMonitor);
    
    /**
     * 查询表中最大的ID
     */
    int findMaxId();
    	
	/**
	 * 通过用户名查找信息
	 */
	AgentMonitor selectAgentMonitor(String account);
	
	/**
	 * 修改当前用户的密码
	 */
    void updateByPrimaryKey(AgentMonitor agentMonitor);
    
    /**
     * 查找用户，通过用户名和密码   
     * @param agentMonitor
     * @return
     */
    AgentMonitor findAgent(AgentMonitor agentMonitor);
	
    /**
     * 查询全部代理
     */
    List<AgentMonitor> findAll();
	
}
