package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.CordycepsLogistic;
import org.jymf.utils.PageView;

public interface ICordycepsLogisService {
    /**
     * 分页处理
     * @param pageView
     * @param agentMonitorRelation
     * @return
     */
    PageView query(PageView pageView, CordycepsLogistic cordycepsLogistic,BigDecimal workMode);
    /**
     * 插入记录
     * @param pageView
     * @param cordyceps
     * @param workMode
     * @return
     */
    int insert(CordycepsLogistic cordycepsLogistic,BigDecimal workMode);
    /**
     * 选择一条记录
     * @param materialBatchId
     * @param workMode
     * @return
     */
    CordycepsLogistic select(int id,BigDecimal workMode);
    
    int update(CordycepsLogistic cordycepsLogistic, BigDecimal workMode);
    /**
     * 校验溯源码段
     * @param startID
     * @param endID
     * @param id 编辑时，该条信息本身的id
     * @return
     */
    boolean getCorLogisBySEId(long startID,long endID,long id,BigDecimal workMode);
    /**
     * 根据溯源码查询包含该溯源码的码段
     * @param traceId
     * @param id 区分新增与修改
     * @param workMode
     * @return
     */
    boolean getCorLogisByTraceId(long traceId,long id,BigDecimal workMode);
}
