package org.jymf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.CordycepsMapper;
import org.jymf.entity.Cordyceps;
import org.jymf.service.ICordycepsService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("cordycepsService")
public class CordycepsServiceImpl implements ICordycepsService {
	
	@Autowired
	private CordycepsMapper cordycepsMapper;

	@Override
	public PageView query(PageView pageView, Cordyceps cordyceps,BigDecimal workMode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(null!=cordyceps.getMaterialBatchId()){
				cordyceps.setMaterialBatchId(cordyceps.getMaterialBatchId().replace("_", "\\_"));
				cordyceps.setMaterialBatchId(cordyceps.getMaterialBatchId().replace("%", "\\%"));
				cordyceps.setMaterialBatchId(cordyceps.getMaterialBatchId().replace("'", "\\'"));
				cordyceps.setMaterialBatchId(cordyceps.getMaterialBatchId().replace("\"", "\\\""));
			}
			if(null!=cordyceps.getFormerLeader()){
				cordyceps.setFormerLeader(cordyceps.getFormerLeader().replace("\\", "\\\\\\"));				
				cordyceps.setFormerLeader(cordyceps.getFormerLeader().replace("_", "\\_"));
				cordyceps.setFormerLeader(cordyceps.getFormerLeader().replace("%", "\\%"));
				cordyceps.setFormerLeader(cordyceps.getFormerLeader().replace("'", "\\'"));
				cordyceps.setFormerLeader(cordyceps.getFormerLeader().replace("\"", "\\\""));
			}
			if(null!=cordyceps.getCultivateLeader()){
				cordyceps.setCultivateLeader(cordyceps.getCultivateLeader().replace("\\", "\\\\\\"));				
				cordyceps.setCultivateLeader(cordyceps.getCultivateLeader().replace("_", "\\_"));
				cordyceps.setCultivateLeader(cordyceps.getCultivateLeader().replace("%", "\\%"));
				cordyceps.setCultivateLeader(cordyceps.getCultivateLeader().replace("'", "\\'"));
				cordyceps.setCultivateLeader(cordyceps.getCultivateLeader().replace("\"", "\\\""));
			}
			if(null!=cordyceps.getPostProcLeader()){
				cordyceps.setPostProcLeader(cordyceps.getPostProcLeader().replace("\\", "\\\\\\"));
				cordyceps.setPostProcLeader(cordyceps.getPostProcLeader().replace("_", "\\_"));
				cordyceps.setPostProcLeader(cordyceps.getPostProcLeader().replace("%", "\\%"));
				cordyceps.setPostProcLeader(cordyceps.getPostProcLeader().replace("'", "\\'"));
				cordyceps.setPostProcLeader(cordyceps.getPostProcLeader().replace("\"", "\\\""));
			}
		} catch (Exception e) {
			
		}
        map.put("paging", pageView);
        map.put("t", cordyceps);
        List<Cordyceps> list = null;
        try {
        	list = cordycepsMapper.cordycepsListquery(map);
		} catch (Exception e) {
			list = null;
		}
        pageView.setRecords(list);
        return pageView;
	}

	@Override
	public int insert(Cordyceps cordyceps, BigDecimal workMode) {
		try {
			return cordycepsMapper.insertSelective(cordyceps);
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	@Override
	public Cordyceps select(String materialBatchId, BigDecimal workMode) {
		try {
			return cordycepsMapper.selectByPrimaryKey(materialBatchId);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int update(Cordyceps cordyceps, BigDecimal workMode) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			cordyceps.setUpdateTime(df.format(new Date()));
			return cordycepsMapper.updateByPrimaryKeySelective(cordyceps);
		} catch (Exception e) {
			return 0;
		}
	}

}
