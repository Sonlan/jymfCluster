package org.jymf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.CordycepsLogisticMapper;
import org.jymf.entity.CordycepsLogistic;
import org.jymf.service.ICordycepsLogisService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;
@Service("cordycepsLogisService")
public class CordycepsLogisServiceImpl implements ICordycepsLogisService {
	@Autowired
	private CordycepsLogisticMapper cordycepsLogisticMapper;
	@Override
	public PageView query(PageView pageView, CordycepsLogistic cordycepsLogistic, BigDecimal workMode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(""!=cordycepsLogistic.getStartID()&&null!=cordycepsLogistic.getStartID())
				cordycepsLogistic.setStartID(cordycepsLogistic.getStartID().substring(0,cordycepsLogistic.getStartID().length()-4));
			if(""!=cordycepsLogistic.getEndID()&&null!=cordycepsLogistic.getEndID())
				cordycepsLogistic.setEndID(cordycepsLogistic.getEndID().substring(0,cordycepsLogistic.getEndID().length()-4));
			if(""!=cordycepsLogistic.getProName()&&null!=cordycepsLogistic.getProName()){
				cordycepsLogistic.setProName(cordycepsLogistic.getProName().replace("\\", "\\\\\\"));				
				cordycepsLogistic.setProName(cordycepsLogistic.getProName().replace("_", "\\_"));
				cordycepsLogistic.setProName(cordycepsLogistic.getProName().replace("%", "\\%"));
				cordycepsLogistic.setProName(cordycepsLogistic.getProName().replace("'", "\\'"));
				cordycepsLogistic.setProName(cordycepsLogistic.getProName().replace("\"", "\\\""));
			}
			if(""!=cordycepsLogistic.getProBatchId()&&null!=cordycepsLogistic.getProBatchId()){
				cordycepsLogistic.setProBatchId(cordycepsLogistic.getProBatchId().replace("\\", "\\\\\\"));
				cordycepsLogistic.setProBatchId(cordycepsLogistic.getProBatchId().replace("_", "\\_"));
				cordycepsLogistic.setProBatchId(cordycepsLogistic.getProBatchId().replace("%", "\\%"));
				cordycepsLogistic.setProBatchId(cordycepsLogistic.getProBatchId().replace("'", "\\'"));
				cordycepsLogistic.setProBatchId(cordycepsLogistic.getProBatchId().replace("\"", "\\\""));
			}
		} catch (Exception e) {
			cordycepsLogistic.setStartID("");
			cordycepsLogistic.setEndID("");
		}
		map.put("paging", pageView);
        map.put("t", cordycepsLogistic);
        List<CordycepsLogistic> list = null;
        try {
        	list = cordycepsLogisticMapper.cordycepsLogisListquery(map);
            for(CordycepsLogistic cordy : list){
            	cordy.setStartID(this.getLabelId(cordy.getStartID()));
            	cordy.setEndID(this.getLabelId(cordy.getEndID()));
            }
		} catch (Exception e) {
			list = null;
		}
        
        
        pageView.setRecords(list);
        return pageView;	
    }

	@Override
	public int insert(CordycepsLogistic cordycepsLogistic, BigDecimal workMode) {
		try {
			int len = cordycepsLogistic.getStartID().length();
			cordycepsLogistic.setStartID(cordycepsLogistic.getStartID().substring(0,len-4));
			cordycepsLogistic.setEndID(cordycepsLogistic.getEndID().substring(0,len-4));
			return cordycepsLogisticMapper.insertSelective(cordycepsLogistic);
		} catch (Exception e) {
			return 0;
		}
		

	}

	@Override
	public CordycepsLogistic select(int id, BigDecimal workMode) {
		try {
			CordycepsLogistic cordy  = cordycepsLogisticMapper.selectByPrimaryKey(id);
			cordy.setStartID(this.getLabelId(cordy.getStartID()));
			cordy.setEndID(this.getLabelId(cordy.getEndID()));
	    	return cordy;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int update(CordycepsLogistic cordycepsLogistic, BigDecimal workMode) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			cordycepsLogistic.setUpdateTime(df.format(new Date()));
			int len = cordycepsLogistic.getStartID().length();
			cordycepsLogistic.setStartID(cordycepsLogistic.getStartID().substring(0,len-4));
			cordycepsLogistic.setEndID(cordycepsLogistic.getEndID().substring(0,len-4));
			return cordycepsLogisticMapper.updateByPrimaryKeySelective(cordycepsLogistic);
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public boolean getCorLogisBySEId(long startID, long endID, long id,BigDecimal workMode) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("startID", startID);
	        map.put("endID", endID);
	        map.put("id", id);
	        
	        List<CordycepsLogistic> list = 
			cordycepsLogisticMapper.getCorLogisBySEId(map);
	        return (list.isEmpty());
		} catch (Exception e) {
			return false;
		}
		
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
	public boolean getCorLogisByTraceId(long traceId, long id, BigDecimal workMode) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("traceID", traceId);
	        map.put("id", id);
	        
	        CordycepsLogistic cordyLogis = 
			cordycepsLogisticMapper.getCorLogisByTraceId(map);
	        if(null == cordyLogis) return true;
	        else return false;
		} catch (Exception e) {
			return false;
		}
		
	}
	

}
