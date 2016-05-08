package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.Cordyceps;
import org.jymf.utils.PageView;

public interface ICordycepsService {

    /**
     * 分页处理
     * @param pageView
     * @param agentMonitorRelation
     * @return
     */
    PageView query(PageView pageView, Cordyceps cordyceps,BigDecimal workMode);
    /**
     * 插入记录
     * @param pageView
     * @param cordyceps
     * @param workMode
     * @return
     */
    int insert(Cordyceps cordyceps,BigDecimal workMode);
    /**
     * 选择一条记录
     * @param materialBatchId
     * @param workMode
     * @return
     */
    Cordyceps select(String materialBatchId,BigDecimal workMode);
    
    int update(Cordyceps cordyceps, BigDecimal workMode);
}
