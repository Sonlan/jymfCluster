package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jymf.dao.CertificateMapper;
import org.jymf.dao.LabelInfoMapper;
import org.jymf.entity.Certificate;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.LabelInfo;
import org.jymf.service.ICertificateService;
import org.jymf.utils.Common;
import org.jymf.utils.FileManager;
import org.jymf.utils.PageView;
import org.jymf.utils.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("certificateService")
public class CertificateServiceImpl implements ICertificateService {

	@Autowired
	private CertificateMapper certificateDao;
	
	@Autowired
	private LabelInfoMapper labelInfoDao;
	
	@Resource
	private SysConfig sysConfig;
	
	@Autowired
	private FileManager fileManager;
	
	private static Logger log = LoggerFactory.getLogger("CertificateServiceImpl");
	
	@Override
	public PageView query(PageView pageView, Certificate cer, CompanyUser companyUser, BigDecimal workMode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyUser.getCompanyId());
		map.put("t", cer);
		map.put("paging", pageView);
		
		List<Certificate> list = certificateDao.query(map);
        
		for(Certificate c : list){
			Map<String,Object> cerMap = new HashMap<String, Object>();
			cerMap.put("companyId", companyUser.getCompanyId());
			cerMap.put("goodId", c.getId());
			List<LabelInfo> labelInfoList = labelInfoDao.findByCondition(cerMap);
			if(null==labelInfoList || labelInfoList.size()==0){
				c.setActive(false);
			}else{
				c.setActive(true);
			}
		}
		
		pageView.setRecords(list);
        return pageView;
	}

	@Override
	public void add(Certificate certificate, List<MultipartFile> imgFile1,
			List<MultipartFile> imgFile2, CompanyUser companyUser,
			BigDecimal workMode) {

			certificate.setCompanyId(companyUser.getCompanyId());
			certificateDao.add(certificate);
			
			//保存图片
			try {
				String itemId = String.format("%s_order_%s_jyz", companyUser.getCompanyId(),certificate.getId());
				if(!fileManager.saveUploadMultipartFile(imgFile1, itemId))
					log.error("检疫证上传时失败:" + itemId);
				
				itemId = String.format("%s_order_%s_rgd", companyUser.getCompanyId(),certificate.getId());
				if(!fileManager.saveUploadMultipartFile(imgFile2, itemId))
					log.error("入关单上传时失败:" + itemId);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
	}

	@Override
	public Certificate findById(Integer id,CompanyUser companyUser,BigDecimal workmode) {
		Certificate certificate = new Certificate();
		certificate.setId(id);
		certificate.setCompanyId(companyUser.getCompanyId());
		certificate = certificateDao.findByCondition(certificate); 
		
		//入关单
		String itemId = String.format("%s_order_%s_rgd", companyUser.getCompanyId(), id);
		Map<Integer,String> mapPicPath = fileManager.readImgFile(itemId);
    	// 读取失败
    	if(null == mapPicPath)
    		log.error("入关单链接获取时失败:" + itemId);
    	if(mapPicPath.isEmpty())
    		certificate.setBgd("");
    	else
    		certificate.setBgd(String.format("%s%s",sysConfig.getFileServiceUrl(),mapPicPath.get(1)));
    	
    	//检疫证
    	itemId = String.format("%s_order_%s_jyz", companyUser.getCompanyId(), id);
    	mapPicPath = fileManager.readImgFile(itemId);
    	// 读取失败
    	if(null == mapPicPath)
    		log.error("入关单链接获取时失败:" + itemId);
    	if(mapPicPath.isEmpty())
    		certificate.setJyz("");
    	else
    		certificate.setJyz(String.format("%s%s",sysConfig.getFileServiceUrl(),mapPicPath.get(1)));
    	    	
		certificate.setCompanyId(companyUser.getCompanyId());
		return certificate;
	}

	@Override
	public void edit(Certificate certificate, List<MultipartFile> imgFile1, List<MultipartFile> imgFile2,
			CompanyUser companyUser,BigDecimal workMode) {
		
		try {		
			String itemId = String.format("%s_order_%s_jyz", companyUser.getCompanyId(),certificate.getId());
			if(Common.isEmpty(certificate.getJyz())){
				if(!fileManager.deleteFile(itemId, 1))
			    	log.error(String.format("检疫证图片删除失败，itemId=%s", itemId));
			}else{
				if(!fileManager.saveUploadMultipartFile(imgFile1, itemId))
					log.error("检疫证上传时失败:" + itemId);
			}
			
			itemId = String.format("%s_order_%s_rgd", companyUser.getCompanyId(),certificate.getId());
			if(Common.isEmpty(certificate.getBgd())){
				if(!fileManager.deleteFile(itemId, 1))
			    	log.error(String.format("入关单图片删除失败，itemId=%s", itemId));
			}else{
				if(!fileManager.saveUploadMultipartFile(imgFile2, itemId))
					log.error("入关单上传时失败:" + itemId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		certificate.setCompanyId(companyUser.getCompanyId());
		certificateDao.edit(certificate);
		
	}

}
