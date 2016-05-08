package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.jymf.entity.AgentMonitorRelation;

/**
 * 代理商关系信息DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface AgentMonitorRelationMapper {
	
    int insert(AgentMonitorRelation record);

    AgentMonitorRelation selectByAgentId(BigDecimal agentId);
    
    /**
     * 改变当前状态 
     */
    int updateStatus(long id);
    
    /**
     * 查询带分页
     */
    List<AgentMonitorRelation> query(Map<Object, Object> map);
    
    /**
     * 根据公司ID查询代理关系
     * @param map "t"=Company
     */
    List<AgentMonitorRelation> findAngetByCompany(Map<String,Object> map);
}