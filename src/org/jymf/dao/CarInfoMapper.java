package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.CarInfo;

public interface CarInfoMapper {
	List<CarInfo> queryCarInfo(Map<Object, Object> map);

	CarInfo selectById(BigDecimal id);
}