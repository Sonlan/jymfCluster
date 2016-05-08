package org.jymf.service;

import org.jymf.entity.MonitorRelation;
import org.jymf.utils.PageView;


/**
 * 监管关系操作接口
 * @author lxg
 * @date   2014年07月07日
 */
public interface IMonitorRelationService {
	 /**
     * 通过主键获取记录
     * @param id
     * @return
     */
    MonitorRelation findById(long id);
    /**
     * 分页处理
     * @param pageView
     * @param monitorRelation
     * @return
     */
    PageView query(PageView pageView, MonitorRelation monitorRelation);
    
    /**
     * 更新
     * @param monitorRelation
     */
    void update(MonitorRelation monitorRelation);
    
    /**
     * 添加
     * @param monitorRelation
     */
    void add(MonitorRelation monitorRelation);
    
    /**
     * 更改当前状态
     * @param id
     * @return
     */
    int updateStatus(long id);
    
}
