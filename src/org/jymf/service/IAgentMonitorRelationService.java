package org.jymf.service;

import org.jymf.entity.AgentMonitorRelation;
import org.jymf.utils.PageView;

/**
 * 代理商关系操作接口
 * @author lxg
 * @date   2014年07月16日
 */
public interface IAgentMonitorRelationService {

    /**
     * 分页处理
     * @param pageView
     * @param agentMonitorRelation
     * @return
     */
    PageView query(PageView pageView, AgentMonitorRelation agentMonitorRelation);
    
    /**
     * 添加
     * @param agentMonitorRelation
     */
    void add(AgentMonitorRelation agentMonitorRelation);
    
    /**
     * 更改当前状态
     * @param id
     * @return
     */
    int updateStatus(long id);
}
