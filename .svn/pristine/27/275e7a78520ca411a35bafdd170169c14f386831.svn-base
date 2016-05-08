package org.jymf.service;

import org.jymf.entity.LabelIndex;
import org.jymf.utils.PageView;

/**
 * 标签索引操作接口
 * @author cqs
 * @date   2014年05月09日
 */
public interface ILabelIndexService{
    /**
     * 通过主键获取记录
     * @param id
     * @return
     */
    LabelIndex findById(long id);
    /**
     * 分页处理
     * @param pageView
     * @param labelIndex
     * @return
     */
    PageView query(PageView pageView, LabelIndex labelIndex);
    
    /**
     * 查询属于某个指定代理商的标签卷信息，分页处理
     * @param pageView
     * @param labelIndex
     * @param agentId 
     * @return
     */
    PageView query(PageView pageView, LabelIndex labelIndex,long agentId);
    
    /**
     * 标签索引信息
     * @param labelIndex
     */
    void update(LabelIndex labelIndex);
    
    /**
     * 添加标签索引
     * @param labelIndex
     */
    void add(LabelIndex labelIndex);
    
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
