package org.jymf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.BatchOutputCountMapper;
import org.jymf.entity.BatchOutputCount;
import org.jymf.service.IBatchOutputCoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("batchOutputCoutService")
public class BatchOutputCoutServiceImpl implements IBatchOutputCoutService {

	private static Logger logger = LoggerFactory.getLogger(BatchOutputCoutServiceImpl.class);
	
	@Autowired
	private BatchOutputCountMapper batchOutPutCntDao;
	
	/**
	 * 标签表为基础的统计执行处理
	 */
	@Override
	public void insertBatchOptputRun(BigDecimal companyId,Boolean isFirst,BigDecimal workMode){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		
		List<Date> dateLst = batchOutPutCntDao.selectTimeSection(map);
		if(null == dateLst){
			return;
		}
				
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar day= Calendar.getInstance();
		day.setTime(new Date());
		day.add(Calendar.DATE, -1);
					
		for(Date date : dateLst){
			// 统计日期为系统日期不操作（只统计今天以前的数据）
			if(sdf.format(date).compareTo(sdf.format(new Date())) == 0)
				continue;
			
			if(isFirst){
				// 首次操作全部insert
				insertBatchCountByDate(companyId,date);
			}else{
				if(sdf.format(date).compareTo(sdf.format(day.getTime())) == 0){
					insertBatchCountByDate(companyId,date);
				}else{
					updateBatchCountByDate(companyId,date);
				}
			}
		}
	}
		
	private void insertBatchCountByDate(BigDecimal companyId,Date date){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.format(date).compareTo(sdf.format(new Date())) == 0)
			return;
		
		map.put("date",sdf.format(date));
		try{
			batchOutPutCntDao.insertBatchCount(map);
			logger.debug("insertBatchCountByDate companyId = " + companyId + " date = " + sdf.format(date));
		}catch(Exception e){
			String errMsg = String.format("name:%s,Value:%s", 
					                      "OutPut_InsertBatchCountByDate",
					                      String.format("%s_%s", companyId,sdf.format(date)));
			
			logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
		}
	}

	private void updateBatchCountByDate(BigDecimal companyId,Date date){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		map.put("date", sdf.format(date));
		List<BatchOutputCount> batcCntLst = batchOutPutCntDao.selectHistoryCount(map);
		if(null == batcCntLst)
			return;
		
		logger.debug("updateBatchCountByDate companyId = " + companyId + " date = " + sdf.format(date));
		for(BatchOutputCount batchCnt : batcCntLst){
			batchCnt.setCompanyId(companyId);
			try{
				batchOutPutCntDao.updateHistoryCount(batchCnt);
			}catch(Exception e){			
				String errMsg = String.format("name:%s,Value:%s", 
	                                          "OutPut_UpdateBatchCountByDate",
	                                          String.format("%s_%s_%s", companyId,sdf.format(date),batchCnt.getProductId()));

                logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
			}
		}
	}
}
