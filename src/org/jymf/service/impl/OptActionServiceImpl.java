package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.OptActionMapper;
import org.jymf.entity.OptAction;
import org.jymf.service.IOptActionService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;


/**
 * 操作实现类
 * @author lxg
 * @date   2014年10月29日
 */
@Service("optActionService")
public class OptActionServiceImpl implements IOptActionService{
	
	@Autowired
	private OptActionMapper optActionDao;
	
	@Override
    public PageView queryOptAction(PageView pageView, OptAction optAction, BigDecimal workMode) {
		  Map<Object, Object> map = new HashMap<Object, Object>();
	        map.put("paging", pageView);
	        map.put("t", optAction);
	        List<OptAction> list = optActionDao.queryOptAction(map);
	        
	        for(OptAction action : list){
	        	action.setLabelId(this.getLabelId(action.getLabelId()));
	        }
	        
	        pageView.setRecords(list);
	        return pageView;
	}
	
	private BigDecimal getLabelId(BigDecimal id){
		String labelId = LableUtil.getCheckNum(id.toString());
		return new BigDecimal(labelId);
	}
}
