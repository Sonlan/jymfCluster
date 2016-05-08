package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.InspectDevice;

public interface InspectDeviceMapper {
    int insert(InspectDevice record);

    int insertSelective(InspectDevice record);
    
	List<InspectDevice> query(Map<Object, Object> map);

	void updateByPrimaryKeySelective(InspectDevice inspectDevice);
	
	InspectDevice selectByPrimaryKey(InspectDevice record);
	
	BigDecimal selectCount(Map<Object, Object> map);
}