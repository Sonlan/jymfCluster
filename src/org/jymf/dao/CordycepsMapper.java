package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.Cordyceps;

public interface CordycepsMapper {

    int insert(Cordyceps record);

    int insertSelective(Cordyceps record);

    Cordyceps selectByPrimaryKey(String materialbatchid);

    int updateByPrimaryKeySelective(Cordyceps record);

    int updateByPrimaryKey(Cordyceps record);
    
    List<Cordyceps> cordycepsListquery(Map<String, Object> map);
}