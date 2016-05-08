package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.jymf.entity.MonitorRelation;

/**
 * 监管关系信息DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface MonitorRelationMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(MonitorRelation record);

    int insertSelective(MonitorRelation record);

    MonitorRelation selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(MonitorRelation record);

    int updateByPrimaryKey(MonitorRelation record);
    
    /**
     * 改变当前状态 
     */
    int updateStatus(BigDecimal id);
    
    /**
     * 查询带分页
     */
    List<MonitorRelation> query(Map<Object, Object> map);
   

    
}