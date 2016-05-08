package org.jymf.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jymf.dao.CompanyCountMapper;
import org.jymf.dao.CompanyMapper;
import org.jymf.dao.CompanyUserMapper;
import org.jymf.dao.ProductMapper;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyCount;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.Depict;
import org.jymf.entity.Product;
import org.jymf.service.ICompanyService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.FileManager;
import org.jymf.utils.FileUtils;
import org.jymf.utils.ImgFile;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.jymf.utils.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
/**
 * 企业操作实现类
 * @author cqs
 * @date   2014年5月7日
 */
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private CompanyMapper companyDao;

	@Autowired
	private CompanyUserMapper companyUserDao;
	
	@Autowired
	private CompanyCountMapper companyCountDao;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ProductMapper  productDao;
	
	@Resource
	private SysConfig sysConfig;
		
	// 日志
	private static Logger log = LoggerFactory.getLogger("CompanyServiceImpl");
	
	/**
	 * 获取所有公司信息（每次获取一页）
	 */
	@Override
	public PageView query(PageView pageView, Company company) {	
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", company);
		
		List<Company> list = companyDao.query(map);
		pageView.setRecords(list);
		
		return pageView;
	}
	
	/**
	 * 监管关系,信息查询,分页
	 * 检索出所有没被指定监管部门（monitorId）下的公司
	 */
	@Override
	public PageView queryCompanyMonitor(PageView pageView, Company company,long monitorId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", company);
		map.put("monitorId", monitorId);
		
		List<Company> list = companyDao.queryCompanyMonitor(map);
		pageView.setRecords(list);
		return pageView;
	}
	
	
	/**
	 * 检索出所有没被指定代理商（agentId）代理的公司
	 */
	@Override
	public PageView queryAgentUpdate(PageView pageView, Company company,long agentId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", company);
		map.put("agentId", agentId);
		
		List<Company> list = companyDao.queryAgentUpdate(map);
		pageView.setRecords(list);
		return pageView;
	}
	

	/**
	 * 查找被代理商（agentId）代理的公司
	 */
	@Override
	public PageView queryAgentCompany(PageView pageView, Company company,long agentId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", company);
		map.put("agentId", agentId);
		
		List<Company> list = companyDao.queryAgentCompany(map);
		pageView.setRecords(list);
		return pageView;
	}
	
	
	/**
	 * 根据监管ID,信息查询,分页
	 */
	@Override
	public PageView queryMonitorCompany(PageView pageView, Company company,long monitorId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", company);
		map.put("monitorId", monitorId);
		
		List<Company> list = companyDao.queryCompany1(map);
		pageView.setRecords(list);
		return pageView;
	}
	

	@Override
	public Company findById(BigDecimal id,Boolean isImg) {
		Company company = companyDao.selectByPrimaryKey(id);
		if(isImg){
			String itemId = String.format("%s_img", company.getId());
			Map<Integer,String> map = fileManager.readImgFile(itemId);
	    	// 读取失败
	    	if(null == map)
	    		log.error("企业图片获取时失败:" + itemId);
	    	
	    	List<ImgFile> imgFiles = fileManager.getFiles(map, 3);
	    	
			company.setComImgs(imgFiles);
			if(company.getWorkMode().equals(Constants.WM_HJC)){
				itemId = String.format("%s_certificate", id);
				map = fileManager.readImgFile(itemId);
				if(null == map)
		    		log.error("许可证图片获取时失败:" + itemId);
				String fileName = String.format("%s%s",sysConfig.getFileServiceUrl(),map.get(1));
				company.setXkzUrl(fileName);
			}
		}
		//获取json文件
		Depict depicts = new Depict();
		String itemId = String.format("%s_json", company.getId());
		company.setDepict(depicts.jsonToPara(fileManager.readJsonFile(itemId)));

		return company;
	}

	@Override
	public boolean update(Company company,List<MultipartFile> imgFile4,String cacheFolder) {
    	
		//公司描述为空的情况改为公司名称 update by wfj 2015.5.8
		if(null==company.getDepict().getDepicts() || "".equals(company.getDepict().getDepicts())){
			Depict depict = new Depict();
			depict.setDepicts(company.getName());
			company.setDepict(depict);
		}
		
		Company old = findById(company.getId(),true);
    	//更新
        boolean isUpdate = update(company,old);
        //更新企业图片
        if(updateImg(company,old,cacheFolder)){
        	isUpdate = true;
        }
        
        //存储红酒模式的卫生许可证
        if(company.getWorkMode().compareTo(Constants.WM_HJC)==0 && null!=imgFile4){
	        try{
	        	String itemId = String.format("%s_certificate", company.getId());
	        	if(!fileManager.saveUploadMultipartFile(imgFile4, itemId))
					log.error(String.format("企业许可证上传时失败，itemId=%s", itemId));
	        	
			}catch(Exception e){
				e.printStackTrace();
				isUpdate = false;
			}
        }
        if(isUpdate){
        	//画面更新产品
        	if(null == company.getName()){
        		company.setName(old.getName());
        	}
        	if(null == company.getWorkMode()){
        		company.setWorkMode(old.getWorkMode());
        	}
        }
          
		return isUpdate;
	}

	private boolean updateImg(Company company,Company old,String cacheFolder){
	   	boolean isUpdate = false;
	   	File dir = new File(cacheFolder);
	   	if(dir.exists()){
		try {
			
			List<ImgFile> newImg=company.getComImgs();
			List<ImgFile> oldImg=old.getComImgs();
			String itemId = String.format("%s_img", company.getId());
			
			for(int i=0;i<newImg.size();i++){
				String suffix = FileUtils.getSuffix(cacheFolder, newImg.get(i).getFileName());
				//原图和新图不一致
				if(!(oldImg.get(i).getFileName().equals(newImg.get(i).getFileName()))){
					//原图有、新图为空,删除原图
					if(!Common.isEmpty(oldImg.get(i).getFileName()) 
							&& Common.isEmpty(newImg.get(i).getFileName())){
						if(!fileManager.deleteFile(itemId, i + 1))
					    	log.error(String.format("商品图片删除失败，itemId=%s", itemId));
						isUpdate=true;
						
					//原图为空，新图有，增加新图
					}else{
						File file=new File(cacheFolder+newImg.get(i).getFileName()+suffix);
						if(!fileManager.saveUploadFile(file, itemId, i + 1))
							log.error("企业描述图片上传时失败:" + itemId);
						isUpdate=true;
					} 
				}
			}
			
		} catch (Exception e) {
			isUpdate = false;
			log.error("企业图片更新时失败，错误信息：" + e.toString());
		}
		}
		return isUpdate;
	}
	
	private boolean update(Company company, Company old) {
		boolean isUpdate = false;
    	if(!company.equals(old)){
    		companyDao.updateByPrimaryKeySelective(company);
    		isUpdate = true;
    	}
		if(!Common.isEquals(company.getDepict(), old.getDepict())){
			// 保存参数信息到json文件中
	    	Depict para = company.getDepict();
	    	String itemId = String.format("%s_json", company.getId());
			if(null!=para){
				String jsonStr = para.toJson();	
				if(!fileManager.saveUploadFile(jsonStr,itemId,"json")){
					log.error(String.format("公司描述信息上传失败，json=%s,item=%s",para, itemId));
				}		

			}else{
				if(!fileManager.deleteFile(itemId, 1))
			    	log.error(String.format("公司描述信息删除失败，itemId=%s", itemId));
			}
				
			isUpdate = true;
    	}
    	return isUpdate;
	}

	@Override
	@Transactional
	public void add(Company company,List<MultipartFile> imgFile4 ,String cacheFolder) {
		log.debug(String.format("添加公司：公司名称%s", company.getName()));
		
		//公司描述为空的情况改为公司名称 update by wfj 2015.5.8
		if(null==company.getDepict().getDepicts() || "".equals(company.getDepict().getDepicts())){
			Depict depict = new Depict();
			depict.setDepicts(company.getName());
			company.setDepict(depict);
		}
		
		BigDecimal id = companyDao.selectMaxId();
		company.setId(id);
		//添加企业时，默认设置
		//已经通过审核 Flg 2
		company.setFlag(new BigDecimal(2));
		//状态 0 正常
		company.setStatus(new BigDecimal(0));
		
		companyDao.insert(company);
		
		// 创建企业信息同时，为企业添加一个默认的管理账户 
		insertUser(company.getId());
		
		//保存公司的描述信息到json中
		Depict para = company.getDepict();
		if(null!=para){
			String jsonStr = para.toJson();	
			String itemId = String.format("%s_json", company.getId());
			if(!fileManager.saveUploadFile(jsonStr,itemId,"json")){
				log.error(String.format("公司描述信息上传失败，json=%s,item=%s",para, itemId));
			}		
		}
		
		try {
			
			//存储企业信息图片
			List<File> fileList=new ArrayList<File>(); 
			for(ImgFile imgFile : company.getComImgs()){
				if(Common.isEmpty(imgFile.getFileName())){
					fileList.add(null);
				}else{
					String suffix=FileUtils.getSuffix(cacheFolder, imgFile.getFileName());
					fileList.add(new File(cacheFolder+imgFile.getFileName()+suffix));
				}
			}
			
			String itemId = String.format("%s_img", company.getId());
			if(!fileManager.saveUploadFile(fileList, itemId))
				log.error("企业描述图片上传时失败:" + itemId);
			
			//存储红酒模式的卫生许可证
			if(company.getWorkMode().compareTo(Constants.WM_HJC)==0 && null!=imgFile4){			
				itemId = String.format("%s_certificate", company.getId());
				if(!fileManager.saveUploadMultipartFile(imgFile4, itemId))
					log.error("许可证图片上传时失败:" + itemId);
			}
			
		} catch (Exception e) {
			log.info("企业图片上传时失败，错误信息：" + e.toString());
			e.printStackTrace();
		}
	}
   
	/**
	 * 为企业创建一个默认用户
	 * @param id
	 */
	private void insertUser(BigDecimal id){
		CompanyUser cu = new CompanyUser();
		cu.setId(String.format("%s_%s", "admin",id));
		try {
			cu.setPassword(MD5.getInstance().encrypt("sr84j?sdjf"));
		} catch (Exception e) {
			cu.setPassword("sr84j?sdjf"); 
		}
		cu.setCompanyId(id);
		// 1:管理权限
		cu.setAuthority(new BigDecimal(1));
		// 0:启用
		cu.setStatus(new BigDecimal(0));
		companyUserDao.insert(cu);
	}

	/**
	 * 动态建表
	 * @param map
	 */
	@Override
	public void createTable(Company company,BigDecimal workMode){	
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("companyId", company.getId().toString());
		map.put("workMode", workMode);
		
		// 创建企业标签信息表
		companyDao.createLabel(map);
		
		// 创建串货信息表
		companyDao.createAcInfo(map);
		
	    // 创建稽查设备终端表
	    companyDao.createDevice(map);
			
	    // 创建企业产品类型表
		companyDao.createProduct(map);
		
	    // 创建操作类型表
		companyDao.createOptActions(map);
	
	    // 创建以标签为基础的统计表 
		companyDao.createCount(map);
	    
	    // 创建以出库为基础的统计表 
		companyDao.createOutPutCount(map);
		
		if(workMode.compareTo(Constants.WM_JL)==0){
	        // 创建虚拟托盘表
			companyDao.createVpallet(map);
		    // 创建虚拟托盘关系表 
			companyDao.createVpalletRelation(map);
		    // 创建产品类型表
			companyDao.createProductType(map);
		}
		if(workMode.compareTo(Constants.WM_HJC)==0){	    
		    // 创建产品类型表
			companyDao.createProductType(map);
			// 货单正品表
			companyDao.createGoodReal(map);
		}
		if(workMode.compareTo(Constants.WM_QP)==0){
		    // 创建入库表
			companyDao.createRuKu(map);
		    // 创建出库表
			companyDao.createChuKu(map);
		    // 创建返库表
			companyDao.createFanKu(map);
		}
		Product product = new Product();
		product.setCompanyId(company.getId());
		product.setId(new BigDecimal(100));
		product.setStatus(new BigDecimal(1));
    	// 查询结果描述处理
    	String queryDes="";
    	if(null == product.getQueryDes() || product.getQueryDes().equals("")){
    		switch(company.getWorkMode().intValue())
    		{
    		  case 0:
    			  //%s生产的%s 
    			  queryDes = String.format("%s生产的产品。", company.getName());
    			  break;
    		  case 1:
    			  queryDes = String.format("%s生产的产品。", company.getName());
    			  break;
    		  case 2:
    			  queryDes = String.format("%s销售的产品。", company.getName());
    			  break;
    		  case 3:
    			  queryDes = String.format("%s销售的产品。", company.getName());
    			  break;
    		  default:
    			  queryDes = String.format("%s生产的产品。", company.getName());
    			  break;
    		}
    	}
    	product.setQueryDes(queryDes);
		productDao.insertSelective(product);
	}

	@Override
	public Company getAuditble(long id) {
		/*
		 * 算法
		 * 1、存在判断（理论上说，是存在的）
		 * 2、审核是否通过 Flg ？= 2
		 * 3、是否在审核有效期内
		 */
		Company company = companyDao.selectByPrimaryKey(new BigDecimal(id));
		if(null==company || null == company.getFlag() ){
			return null;
		}
		
		if(2!=company.getFlag().intValue()){
		    return null;	
		}
		if(null==company.getAuditValid() || null==company.getAuditTime()){
			return company;
		}
		
		return company;
	}
	

	@Override
	public PageView queryCountByCompany(PageView pageView, CompanyCount companyCount,BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", companyCount);
		
		if(Common.IsToday(companyCount.getEndDate())){
			map.put("today", 1);
		}
		
		List<CompanyCount> list = companyCountDao.queryCompanyCount(map);
		pageView.setRecords(list);
		return pageView;
	}
	
	@Override
	public List<Company> findAllCompany(){
		return companyDao.findAll();
	}
	
	@Override
	public CompanyCount labelCountByCompany(CompanyCount companyCount,BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("t", companyCount);
		
		if(Common.IsToday(companyCount.getEndDate())){
			map.put("today", 1);
		}
		
		CompanyCount companyCnt = companyCountDao.labelCompanyCount(map);
		return companyCnt;
	}

	@Override
	public List<BigDecimal> selectAllIdByWorkMode(int workMode) {
		return companyDao.selectAllIdByWorkMode(new BigDecimal(workMode));
	}
}
