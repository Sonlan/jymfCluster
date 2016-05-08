package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AcInfoMapper;
import org.jymf.entity.AcInfo;
import org.jymf.service.IAcInfoService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;

@Service("acInfoService")
public class AcInfoServiceImpl implements IAcInfoService {

	@Autowired
	private AcInfoMapper acInfoDao;
	
	@Override
	public PageView query(PageView pageView, AcInfo acInfo, BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("t", acInfo);
		map.put("paging", pageView);
		
		List<AcInfo> list = acInfoDao.queryAcInfo(map);
		
        for(AcInfo ac : list){
        	ac.setLabelId(this.getLabelId(ac.getLabelId()));
        }
        
		pageView.setRecords(list);
        return pageView;
	}

	private BigDecimal getLabelId(BigDecimal id){
		String labelId = LableUtil.getCheckNum(id.toString());
		return new BigDecimal(labelId);
	}

	@Override
	public PageView queryCnt(PageView pageView, AcInfo acInfo, BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("t", acInfo);
		map.put("paging", pageView);
		
		List<AcInfo> list = acInfoDao.queryAcCnt(map);
        
		pageView.setRecords(list);
        return pageView;
	}
	
}
