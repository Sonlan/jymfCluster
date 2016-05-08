package org.jymf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jymf.dao.BatchCountMapper;
import org.jymf.entity.BatchCount;
import org.jymf.service.IBatchCoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("batchCoutService")
public class BatchCoutServiceImpl implements IBatchCoutService {

	private static Logger logger = LoggerFactory.getLogger(BatchCoutServiceImpl.class);
	
	@Autowired
	private BatchCountMapper batchCntDao;
		
	/**
	 * 标签表为基础的统计执行处理
	 */
	@Override
	public void insertBatchRun(BigDecimal companyId,Boolean isFirst,BigDecimal workMode){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		
		BatchCount batchCnt = batchCntDao.selectTimeSection(map);
		
		if(null == batchCnt || null == batchCnt.getMinTime()){
			return;
		}
					
		// 统计的开始日期
		Date startDate = batchCnt.getMinTime();
		// 统计的结束日期
		Date endDate = getMaxDate(batchCnt);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
		if(isFirst){	
			while(startDate.compareTo(endDate) <= 0){
				insertBatchCountByDate(companyId,startDate);
				
				Calendar day= Calendar.getInstance();
				day.setTime(startDate);
				day.add(Calendar.DATE, 1);
				
				startDate=day.getTime();
			}
		}
		
		Calendar day= Calendar.getInstance();
		day.setTime(new Date());
		day.add(Calendar.DATE, -1);
		
		if(sdf.format(endDate).compareTo(sdf.format(day.getTime())) == 0 && !isFirst){
			insertBatchCountByDate(companyId,day.getTime());
		}

	}
	
	/**
	 * 获取最大的结束日期
	 * @param batchCnt
	 * @return
	 */
	private Date getMaxDate(BatchCount batchCnt){
		Date date= batchCnt.getMaxTime();
		if(null != batchCnt.getMaxConsTime() && date.compareTo(batchCnt.getMaxConsTime())<0)
			date = batchCnt.getMaxConsTime();
		
		if(null != batchCnt.getMaxPackTime() && date.compareTo(batchCnt.getMaxPackTime())<0)
			date=batchCnt.getMaxPackTime();
		
		if(null != batchCnt.getMaxOptTime() && date.compareTo(batchCnt.getMaxOptTime())<0)
			date=batchCnt.getMaxOptTime();
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.format(date).compareTo(sdf.format(new Date())) == 0){
			Calendar day= Calendar.getInstance();
			day.setTime(new Date());
			day.add(Calendar.DATE, -1);
			date=day.getTime();
		}
				
		return date;
	}
	
	private void insertBatchCountByDate(BigDecimal companyId,Date date){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if(sdf.format(date).compareTo(sdf.format(new Date())) == 0)
			return;
		
		map.put("date",sdf.format(date));
		try{
			batchCntDao.insertBatchCount(map);
		}catch(Exception e){
			String errMsg = String.format("name:%s,Value:%s", 
                                          "Count_InsertBatchCountByDate",
                                           String.format("%s_%s", companyId,sdf.format(date)));
			logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
		}
		logger.debug("insertBatchCountByDate companyId = " + companyId + " date = " + sdf.format(date));
	}
	
	/**
	 * 标签表为基础的统计执行处理
	 */
	@Override
	public void insertBatchCarRun(BigDecimal companyId,Boolean isFirst,BigDecimal workMode){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		
		BatchCount batchCnt = batchCntDao.selectTimeSection(map);
		
		if(null == batchCnt || null == batchCnt.getMinTime()){
			return;
		}
					
		// 统计的开始日期
		Date startDate = batchCnt.getMinTime();
		// 统计的结束日期
		Date endDate = getMaxDate(batchCnt);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
		if(isFirst){	
			while(startDate.compareTo(endDate) <= 0){
				insertBatchCarCountByDate(companyId,startDate);
				
				Calendar day= Calendar.getInstance();
				day.setTime(startDate);
				day.add(Calendar.DATE, 1);
				
				startDate=day.getTime();
			}
		}

	}
	
	private void insertBatchCarCountByDate(BigDecimal companyId,Date date){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("companyId", companyId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if(sdf.format(date).compareTo(sdf.format(new Date())) == 0)
			return;
		
		map.put("date",sdf.format(date));
		try{
			batchCntDao.insertBatchCarCount(map);
		}catch(Exception e){
			String errMsg = String.format("name:%s,Value:%s", 
                                          "Count_InsertBatchCountByDate",
                                           String.format("%s_%s", companyId,sdf.format(date)));
			logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
		}
		logger.debug("insertBatchCountByDate companyId = " + companyId + " date = " + sdf.format(date));
	}
}
