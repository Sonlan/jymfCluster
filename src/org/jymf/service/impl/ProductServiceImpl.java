package org.jymf.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.core.modules.mapper.JsonMapper;
import org.jymf.dao.AdminMapper;
import org.jymf.dao.ProductMapper;
import org.jymf.dao.ProductTypeMapper;
import org.jymf.entity.Admin;
import org.jymf.entity.Para;
import org.jymf.entity.Product;
import org.jymf.entity.ProductConfig;
import org.jymf.entity.ProductPara;
import org.jymf.service.IProductService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.FileManager;
import org.jymf.utils.FileUtils;
import org.jymf.utils.ImgFile;
import org.jymf.utils.MailSender;
import org.jymf.utils.MailSenderInfo;
import org.jymf.utils.PageView;
import org.jymf.utils.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * 产品类型操作实现类
 * @author lxg
 * @date   2014年05月20日
 */
@Service("productService")
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productDao;

    @Autowired
    private ProductTypeMapper productTypeDao;
    
	@Resource
	private SysConfig sysConfig;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private AdminMapper adminDao;
	
	// 日志
	private static Logger log = LoggerFactory.getLogger("ProductServiceImpl");
	
    @Override
    public PageView query(PageView pageView, Product product,BigDecimal workMode) {
    	//替换掉名称中的空格
        if(null!=product.getName() && !product.getName().equals("")){
        	product.setName(product.getName().replace(" ", ""));
        }
        String orderBy = product.getOrderBy();
        
        if("1".equals(product.getOrderBy())){
        	product.setOrderBy("LABEL_CNT DESC,");
        }else if("2".equals(product.getOrderBy())){
        	product.setOrderBy("LABEL_CNT ASC,");
        }else{
        	product.setOrderBy("");
        }

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", product);
        if(null != workMode){
        	map.put("workMode",workMode);
        }
        
        List<Product> list = productDao.query(map);
        pageView.setRecords(list);
        product.setOrderBy(orderBy);
        return pageView;
    }
    
    @Override
    public PageView selectProduct(PageView pageView, Product product, BigDecimal workMode) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", product);
 
        List<Product> list = productDao.queryProduct(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Product findById(BigDecimal id, BigDecimal companyId,Boolean isImg, BigDecimal workMode) {
    	Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id);
        map.put("companyId", companyId);
        
        Product product = productDao.selectByPrimaryKey(map);
        
        if(null == product){
        	return null;
        }
        
		if(isImg){
			findImgs(product,id,companyId);
		}
		
		String itemId = String.format("%s_%s_json", companyId,product.getId());
		ProductPara productPara = new ProductPara();
		product.setProductPara(productPara.jsonToPara(fileManager.readJsonFile(itemId)));
		
        return product;
    }

    @Override
    public boolean update(Product product,String cache_folder, BigDecimal workMode) {
    	Product p = findById(product.getId(),product.getCompanyId(),true,workMode);
		
    	// 红酒城参数处理
		if(workMode.compareTo(Constants.WM_HJC)==0){
			setParaWithHjc(product.getProductPara().getGx());
		}
		
    	//更新产品类型表的信息和产品参数信息
        boolean isUpdate = update(product,p);
        //更新产品描述图片和概述图片
        if(updateImg(product,p,cache_folder)){
        	isUpdate = true;
        }
	
		return isUpdate;
    }

    @Override
    public void add(Product product, 
    		        String companyName,
    		        String companyUrl,
    		        String cache_folder,
    		        BigDecimal workMode) {
    	Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("companyId", product.getCompanyId().toString());
		
    	BigDecimal id = productDao.selectMaxId(map);
    	product.setId(id);
    	// 查询结果描述处理
    	String queryDes="";
    	if(null == product.getQueryDes() || product.getQueryDes().equals("")){
    		switch(workMode.intValue())	{
    		    // 0  产品追溯模式
    		  	case 0:  
    		  		//%s生产的%s 
    		  		queryDes = String.format("%s生产的%s。", companyName,product.getName());
    		  		break;
    		  	// 1  厂家追溯模式
    		  	case 1:
    		  		queryDes = String.format("%s生产的产品。", companyName);
    		  		break;
    		  	// 2  商城追溯模式
    		  	case 2:
    		  		queryDes = String.format("经\"%s\"授权%s销售的产品。", companyName,product.getName());
    		  		break;
    		  	// 3  汽配追溯模式
    		  	case 3:
    		  		queryDes = String.format("%s销售的%s。", companyName,product.getName());
    		  		break;
    		  	// 4  出版物追溯模式
    		  	case 4:
    		  		queryDes = String.format("%s出版的%s。", companyName,product.getName());
    		  		break;
    		  	case 10:
    		  		queryDes = String.format("%s销售的%s。", companyName,product.getName());
    		  		break;
    		  	default:
    		  		queryDes=String.format("%s生产的%s。", companyName,product.getName());;
    		  		break;
    		}
    		product.setQueryDes(queryDes);
    	}
    	
    	if(null == product.getUrl() || "".equals(product.getUrl())){
    		product.setUrl(companyUrl);
    	}
    	
    	productDao.insert(product);
    	
		// 保存上传的图片
		try {
			for(int i=0;i<product.getProductImgs().size();i++){
				String fileUrlName = product.getProductImgs().get(i).getFileName();
				if(Common.isEmpty(fileUrlName))
					continue;
				
				// 克隆来的文件
				if(fileUrlName.startsWith(sysConfig.getFileServiceUrl())){
					if(i>0){
						// 产品描述图片
						String itemId = String.format("%s_%s_info", product.getCompanyId(),product.getId());
						if(!fileManager.saveCopyFile(fileUrlName, itemId, i))
							log.error("商品描述上传时失败:" + itemId);
					}else{
						// 产品概述图片
						String itemId = String.format("%s_%s_summ", product.getCompanyId(),product.getId());
						if(!fileManager.saveCopyFile(fileUrlName, itemId, 1))
							log.error("商品概述上传时失败：" + itemId);
					}
				// 直接上传的文件
				}else{
					String suffix = FileUtils.getSuffix(cache_folder, fileUrlName);
					String img_name="";
					if(!Common.isEmpty(fileUrlName))
					    img_name=cache_folder+product.getProductImgs().get(i).getFileName()+suffix;
					
					if(i>0){
						// 产品描述图片
						String itemId = String.format("%s_%s_info", product.getCompanyId(),product.getId());
						if(!fileManager.saveUploadFile(new File(img_name), itemId, i))
							log.error("商品描述上传时失败:" + itemId);
					}else{
						// 产品概述图片
						String itemId = String.format("%s_%s_summ", product.getCompanyId(),product.getId());
						if(!fileManager.saveUploadFile(new File(img_name), itemId, 1))
							log.error("商品概述上传时失败：" + itemId);
					}
				}
			}
			
		} catch (Exception e) {
			log.info("商品图片上传时失败，错误信息：" + e.toString());
		}
		
		// 红酒城参数处理
		if(workMode.compareTo(Constants.WM_HJC)==0){
			setParaWithHjc(product.getProductPara().getGx());
		}
		
		// 保存参数信息到json文件中
		ProductPara para = product.getProductPara();
		if(null != para){
			String jsonStr = para.toJson();	
			String itemId = String.format("%s_%s_json", product.getCompanyId(),product.getId());
			if(!fileManager.saveUploadFile(jsonStr,itemId,"json")){
				log.error(String.format("商品参数信息上传，json=%s,itemId=%s",jsonStr, itemId));
			}
		}
    }
    
    /**
     * 更新产品信息到DB/json中
     * @param product
     * @return
     *        True   有更新
     *       False   无更新
     */
    private boolean update(Product product,Product oldProduct){
    	boolean isUpdate = false;
    	
    	if(!product.equals(oldProduct)){
	    	productDao.updateByPrimaryKeySelective(product);
	    	isUpdate = true;
    	}
		
	    if(!Common.isEquals(product.getProductPara(), oldProduct.getProductPara())){
			// 保存参数信息到json文件中
			ProductPara para = product.getProductPara();
			String itemId = String.format("%s_%s_json", product.getCompanyId(),product.getId());
			if(null != para){
				String jsonStr = para.toJson();	
				if(!fileManager.saveUploadFile(jsonStr,itemId,"json")){
					log.error(String.format("商品参数信息上传失败，json=%s,itemId=%s",jsonStr, itemId));
				}
			}else{
				if(!fileManager.deleteFile(itemId,1)){
					log.error(String.format("商品参数信息删除失败，itemId=%s", itemId));
				}
			}
			isUpdate = true;
    	}
	    
    	return isUpdate;
    }
    
    /**
     * 更新图片
     * @param product
     * @param imgFiles
     * @param summFiles
	 * @return 
	 *        True    文件有上传
	 *       False    文件无上传
     */
    private boolean updateImg(Product product,Product old,String cache_folder){
    	boolean isUpdate = false;
		// 保存上传的图片
    	
    	List<ImgFile> newImg=product.getProductImgs();
		List<ImgFile> oldImg=old.getProductImgs();
    	
    	try {
			String type="";
			int no=0;
			for(int i=0;i<newImg.size();i++){
				if(i>0){
					type="info";
					no=i;
				}else{
					type="summ";
					no=1;
				}

				String suffix = FileUtils.getSuffix(cache_folder, newImg.get(i).getFileName());
				String itemId = String.format("%s_%s_%s", product.getCompanyId(),product.getId(),type);
				
				//原图和新图不一致
				if(!(oldImg.get(i).getFileName().equals(newImg.get(i).getFileName()))){
				
					//原图有、新图为空,删除原图
					if(!Common.isEmpty(oldImg.get(i).getFileName()) 
							&& Common.isEmpty(newImg.get(i).getFileName())){
					    if(!fileManager.deleteFile(itemId, no))
					    	log.error(String.format("商品图片删除失败，itemId=%s", itemId));
					
					}else{
						File file=new File(cache_folder+newImg.get(i).getFileName()+suffix);
						if(!fileManager.saveUploadFile(file, itemId, no))
							log.error(String.format("商品图片更新时失败，itemId=%s", itemId));
					}
				}
			}
			
		} catch (Exception e) {
			isUpdate = false;
			e.printStackTrace();
			log.info("商品图片更新时失败，错误信息：" + e.toString());
		}
		
		return isUpdate;
    }
    
    /**
     * 取得产品的概述图片和描述图片
     */
    private void findImgs(Product product,BigDecimal id, BigDecimal companyId){
    	//产品概述图片
    	String itemId = String.format("%s_%s_summ", companyId, id);
    	Map<Integer,String> map = fileManager.readImgFile(itemId);
    	// 读取失败
    	if(null == map)
    		log.error("商品概述图片获取时失败:" + itemId);
    	
    	List<ImgFile> imgFiles = fileManager.getFiles(map, 1);
    				
    	// 产品描述图片
    	itemId = String.format("%s_%s_info", companyId, id);
    	map = fileManager.readImgFile(itemId);
    	// 读取失败
    	if(null == map)
    		log.error("商品描述图片获取时失败:" + itemId);
    	
		List<ImgFile> imgFile = fileManager.getFiles(map, 3);
		for(int i=0;i<imgFile.size();i++){
			ImgFile img =imgFile.get(i);
			img.setNo(String.valueOf((i+1)));
			imgFiles.add(img);
			if(null != img.getFileName() && !img.getFileName().equals("")){
				product.setIsInfoImg(true);
			}
		}
		
		product.setProductImgs(imgFiles);
    }

	@Override
	public List<BigDecimal> findAllId(BigDecimal companyId,BigDecimal workMode) {
    	Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("companyId", companyId);
		
		return productDao.selectAllId(map);
	}
	
	@Override
	public String findProductName(BigDecimal productId,String companyId,BigDecimal workMode){
		if(null == productId){
			return null;
		}
    	Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", productId);
        map.put("companyId", companyId);
		Product p = productDao.selectByPrimaryKey(map);
		if(null == p){
			return null;
		}
		return p.getName();
	}

	public List<List<String>> getAllProductList(BigDecimal companyId,BigDecimal workMode) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		List<Product> productList   = productDao.findAllProduct(companyId);
		if(null==productList){
			List<String> list = new ArrayList<String>();
			list.add("无数据");
			dataList.add(list);
		}else{
			for(Product p : productList){
				List<String> list = new ArrayList<String>();
				list.add(p.getId().toString());
				list.add(null==p.getType()?"":p.getType().toString());
				list.add(null==p.getName()?"":p.getName());
				String type = String.format("1:%s:%s", p.getBoxCnt(), p.getPalletCnt());
				list.add(type);
				dataList.add(list);
			}
		}
		return dataList;
	}

	@Override
	public ProductConfig getProductPara(long workMode) {
		File cfgFile = null;
		try {		
			String path = String.format("classpath:config/productPara%s.json", workMode);
			cfgFile = ResourceUtils.getFile(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		FileManager fileManager = new FileManager();
		String jsonStr = fileManager.readFile(cfgFile);
		
		JsonMapper json =new JsonMapper();
		ProductConfig productConfig = json.fromJson(jsonStr, ProductConfig.class);
		
		
		return productConfig;
	}

	
	/**
	 * 红酒城参数设置
	 * @param lst 规格参数list
	 */
	private void setParaWithHjc(List<Para> lst){
		setParaValueWithHjc(lst.get(0),"商品类型");
		setParaValueWithHjc(lst.get(1),"容量");
		setParaValueWithHjc(lst.get(2),"葡萄品种");
		setParaValueWithHjc(lst.get(3),"酒精含量");
		setParaValueWithHjc(lst.get(4),"年份");
		setParaValueWithHjc(lst.get(5),"色泽");
		setParaValueWithHjc(lst.get(6),"醒酒时间");
		setParaValueWithHjc(lst.get(7),"品尝温度");
		setParaValueWithHjc(lst.get(8),"进口商");
		setParaValueWithHjc(lst.get(9),"经销商");
	}
	private void setParaValueWithHjc(Para para,String name){
		para.setName(name);
		if(para.getValue().isEmpty()){
			para.setValue("无");
		}
	}
	
	@Override
	public void sendMail(String preStr,String companyName,String productName){
		Map<String,Object> map1 = new HashMap<String, Object>();
		Admin admin = new Admin();
		admin.setRole(2);
		map1.put("t",  admin);
		List<Admin> adminList = adminDao.findAdmins(map1);
		
		for(int i=0;i<adminList.size();i++){
			Admin ad = adminList.get(i);
			if(ad.getAccount().equals("wangfengjiang") || ad.getAccount().equals("liujing")){
				continue;
			}
						
			String mail = ad.getMail();
           		
			if(null !=mail  && !"".equals(mail)){
				//邮件参数
				MailSenderInfo mailInfo = new MailSenderInfo().getFromXML();
		    	String date = Common.fromDateHanzi();
				String subjectPara = String.format("%s_%s_注册新产品请尽快审核",preStr,companyName);
				String contentPara = String.format("%s,您好！<br><br>%s_%s_于%s注册新产品_%s,请尽快审核。<br><br>**********************************************<br>中国产品质量追溯系统运营中心<br>%s<br>**********************************************",
						                           ad.getName(),preStr,companyName,date.toString(),productName,date.toString());
				mailInfo.setSubject(subjectPara);
				mailInfo.setContent(contentPara);
				mailInfo.setToAddress(mail);
				MailSender.sendHtmlMail(mailInfo);
			}
		}
	}

	@Override
	public PageView querySales(PageView pageView, Product product,BigDecimal workMode) {
    	//替换掉名称中的空格
        if(null!=product.getName() && !product.getName().equals("")){
        	product.setName(product.getName().replace(" ", ""));
        }
        
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", product);
        
        List<Product> list = productDao.querySales(map);
        pageView.setRecords(list);
        return pageView;
	}

}
