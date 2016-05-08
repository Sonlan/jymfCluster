package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    /**
     * 查询所有二级区域
     */
    List<Area> findAll();
    /**
     * 查询所有地区名称
     * @return
     */
    List<String> queryAll();
    /**
     * 根据地区名称信息，查询地区信息
     * @param name
     * @return
     */
    Area queryAreaByName(String name);
    /**
     * 根据地区编号查找地区信息
     * @param name
     * @return
     */
    Area queryAreaById(BigDecimal id);
    
    List<Area> getAllObjects();
}