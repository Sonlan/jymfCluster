package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.OutPutCountMapper;
import org.jymf.entity.OutPutCount;
import org.jymf.service.IOutPutCountService;
import org.jymf.utils.Common;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 标签操作实现类
 * @author lxg
 * @date   2014年8月1日
 */
@Service("outPutCountService")
public class OutPutCountServiceImpl implements IOutPutCountService{
	
	@Autowired
	private OutPutCountMapper outPutCountDao;
	
	@Override
	public PageView queryOutPutCount(PageView pageView, OutPutCount outPutCount, BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("t", outPutCount);
		map.put("paging", pageView);
		
		if(Common.IsToday(outPutCount.getEndDate())){
			map.put("today", 1);
		}
		
		List<OutPutCount> list = outPutCountDao.queryOutPutCount(map);
		pageView.setRecords(list);
        return pageView;
	}
}
