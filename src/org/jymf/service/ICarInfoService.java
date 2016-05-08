package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.CarInfo;
import org.jymf.utils.PageView;

/**
 * 车辆信息接口
 * @author cqs
 * @date   2015年12月31日
 */
public interface ICarInfoService{
	/**
	 * 查询车辆信息
	 * @param pageView
	 * @param carInfo
	 * @return
	 */
	PageView queryCarInfo(PageView pageView,CarInfo carInfo);
	
	CarInfo getById(BigDecimal id);
}
