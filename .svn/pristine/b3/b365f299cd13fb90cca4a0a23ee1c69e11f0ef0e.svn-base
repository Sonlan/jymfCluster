package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.CordycepsLogistic;

public interface CordycepsLogisticMapper {

    int insert(CordycepsLogistic record);

    int insertSelective(CordycepsLogistic record);

    CordycepsLogistic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CordycepsLogistic record);
    
    List<CordycepsLogistic> cordycepsLogisListquery(Map<String, Object> map);
    List<CordycepsLogistic> getCorLogisBySEId(Map<String  , Object > map);
    CordycepsLogistic getCorLogisByTraceId(Map<String  , Object > map);
}