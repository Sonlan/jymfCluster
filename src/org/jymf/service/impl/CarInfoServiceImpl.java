package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.CarInfoMapper;
import org.jymf.entity.CarInfo;
import org.jymf.service.ICarInfoService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;
/**
 * 车辆信息查询实现类
 * @author cqs
 * @date   2015年12月31日
 */
@Service("carInfoService")
public class CarInfoServiceImpl implements ICarInfoService{

	@Autowired
	private CarInfoMapper carInfoDao;
	
	@Override
	public PageView queryCarInfo(PageView pageView, CarInfo carInfo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", carInfo);
		
		List<CarInfo> list = carInfoDao.queryCarInfo(map);
		
	    for(CarInfo cInfo : list){
	    	cInfo.setLabelId(this.getLabelId(cInfo.getLabelId()));
        }
        pageView.setRecords(list);
        
		return pageView;
	}
    
	
	/**
	 * 根据输入的顺序号，获取追溯码
	 * @param id
	 * @return
	 */
	private String getLabelId(String id){
		String labelId = LableUtil.getCheckNum(id);
		return labelId;
	}


	@Override
	public CarInfo getById(BigDecimal id) {
		return carInfoDao.selectById(id);
	}
}
