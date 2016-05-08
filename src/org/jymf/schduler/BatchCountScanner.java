package org.jymf.schduler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.entity.Company;
import org.jymf.service.IBatchCarService;
import org.jymf.service.IBatchCoutService;
import org.jymf.service.IBatchOutputCoutService;
import org.jymf.service.ICompanyService;
import org.jymf.service.ISysSetService;
import org.jymf.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Scheduler反射调用的Service POJO.
 * 
 */
@Component
public class BatchCountScanner {

	private static Logger logger = LoggerFactory.getLogger(BatchCountScanner.class);

	@Autowired
    private IBatchCoutService batchCoutService;

	@Autowired
    private IBatchOutputCoutService batchOutputCoutService;
	
	@Autowired
    private ISysSetService sysSetService;
	
	@Autowired
    private ICompanyService companyService;
	
	@Autowired
    private IBatchCarService batchCarService;
	/**
	 * 定时统计任务
	 */
	public void executeBatchCountJob() {
		// 出库统计
		   batchOutPutCountJob();
		// 标签统计
		   batchCountJob();
		// 汽配合同与配件的绑定
		   updateCarLabel();
		
		//car首次发布
		//insertBatchCarCount();
	}

	private void batchCountJob() {
		logger.info("BatchCount is start rh");

        try{
        	insertBatchCount();
        }
        catch(Exception e){
        	logger.error("BatchCount ErrMsg:" + e.getMessage());
        }
		
		logger.info("BatchCount is end");
	}
	
	/**
	 * 定时统计任务
	 */
	private void batchOutPutCountJob() {
		logger.info("BatchOutputCount is start");
        try{
        	insertBatchOutputCount();
        }
        catch(Exception e){
        	logger.error("BatchOutputCount ErrMsg:" + e.getMessage());
        }
		
		logger.info("BatchOutputCount is end");
	}
	
	private void insertBatchCount() {
		Boolean isFirst = false;
		int status = sysSetService.getRunStatus(Constants.BATCH_LABEL_RUN);
		if(-1==status){
			logger.info("insertBatchCount: 无 BatchCout 处理需求，请确认！");
			return;
		}else{
			isFirst = status==0;
		}
		
        // 获取公司的List
		List<Company> companyLst = companyService.findAllCompany();
		for(Company company : companyLst){
			try{
				batchCoutService.insertBatchRun(company.getId(),isFirst,company.getWorkMode());
			}catch(Exception e){			
				String errMsg = String.format("name:%s,Value:%s", 
	                                          "InsertBatchCount",
	                                          String.format("%s", company.getId()));

                logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
			}
		}
		
		if(isFirst){	
			Map<String, Object> sysMap = new HashMap<String, Object>();
			sysMap.put("name", Constants.BATCH_LABEL_RUN);
			sysMap.put("value", "false");
			sysSetService.updateValue(sysMap);
		}
	}
	
	private void insertBatchOutputCount() {
		Boolean isFirst = false;
		int status = sysSetService.getRunStatus(Constants.BATCH_OUTPUT_RUN);
		if(-1 == status){
			logger.info("insertBatchOutputCount:无 BatchOutPutCout 处理需求，请确认！");
			return;
		}else{
			isFirst = status==0;
		}
		
        // 获取公司的List
		List<Company> companyLst = companyService.findAllCompany();
		
		for(Company company : companyLst){
			// 汽配模式没有出库统计
			if(company.getWorkMode().equals(Constants.WM_QP)){
				continue;
			}
			try{
				batchOutputCoutService.insertBatchOptputRun(company.getId(),isFirst,company.getWorkMode());
			}catch(Exception e){				
				String errMsg = String.format("name:%s,Value:%s", 
                                              "InsertBatchOutputCount",
                                              String.format("%s", company.getId()));

                logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
			}
		}
		
		if(isFirst){
			Map<String, Object> sysMap = new HashMap<String, Object>();
			sysMap.put("name", Constants.BATCH_OUTPUT_RUN);
			sysMap.put("value", "false");
			sysSetService.updateValue(sysMap);
		}
	}
	
	private void updateCarLabel(){
		List<BigDecimal> comLst=companyService.selectAllIdByWorkMode(Constants.WM_QP.intValue());
		for(BigDecimal id : comLst){
			batchCarService.updateBatchCarRun(id, Constants.WM_QP);
		}
	}
	
	
	private void insertBatchCarCount() {
		Boolean isFirst = true;

        // 获取公司的List
		List<Company> companyLst = companyService.findAllCompany();
		for(Company company : companyLst){
			if(company.getWorkMode().intValue()==3){
			try{
				logger.info(company.getId().toString());
				batchCoutService.insertBatchCarRun(company.getId(),isFirst,company.getWorkMode());
			}catch(Exception e){			
				String errMsg = String.format("name:%s,Value:%s", 
	                                          "InsertBatchCount",
	                                          String.format("%s", company.getId()));

                logger.error(String.format("%s, ErrMsg:", errMsg, e.getMessage()));
			}
			}
		}
	}
}
