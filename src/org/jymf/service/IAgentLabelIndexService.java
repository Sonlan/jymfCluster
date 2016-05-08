package org.jymf.service;

import org.jymf.entity.AgentLabelIndex;
import org.jymf.utils.PageView;

/**
 * 代理商标签索引操作接口
 * @author cqs
 * @date   2016年03月10日
 */
public interface IAgentLabelIndexService{
    /**
     * 通过主键获取记录
     */
    AgentLabelIndex findById(long id);
    
    /**
     * 分页处理
     */
    PageView query(PageView pageView, String agentId);
    
    /**
     * 标签索引信息
     */
    void update(AgentLabelIndex labelIndex);
    
    /**
     * 添加标签索引
     */
    void add(AgentLabelIndex labelIndex);
    
    /**
     * 判断标签是否被注册过
     * @param labelId
     * @param id 当前记录的ID，Add=0，Update=当前记录
     * @return
     *        True 注册过
     *       False 没有被注册过||当前更新的记录
     */
    boolean checkLableId(String labelId,long id);
}
