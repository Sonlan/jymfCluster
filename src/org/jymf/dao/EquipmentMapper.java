package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
	List<Equipment> query(Map<Object, Object> map);
	
	BigDecimal selectCountBycompany(String companyId);
}