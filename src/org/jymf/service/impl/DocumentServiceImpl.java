package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jymf.dao.DocumentMapper;
import org.jymf.entity.Document;
import org.jymf.entity.DocumentDetail;
import org.jymf.service.IDocumentService;
import org.jymf.utils.FileManager;
import org.jymf.utils.ImgFile;
import org.jymf.utils.PageView;
import org.jymf.utils.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jymf.common.LableUtil;

@Service("documentService")
public class DocumentServiceImpl implements IDocumentService {
	
	@Autowired
	private DocumentMapper documentDao;
	
	@Resource
	private SysConfig sysConfig;
	
	@Autowired
	private FileManager fileManager;
	
	private static Logger log = LoggerFactory.getLogger("DocumentServiceImpl");
	
	@Override
	public PageView query(PageView pageView, Document document, BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
	    map.put("paging", pageView);
	    map.put("t", document);
	    List<Document> list = documentDao.query(map);
	        
	    for(Document doc : list){
	        doc.setId((this.getLabelId(doc.getId())));
	    }
	        
        pageView.setRecords(list);
        return pageView;
	}


	@Override
	public Document selectDetail(Document document, BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		BigDecimal labelId=new BigDecimal(document.getId().toString().substring(0, 12));
		document.setId(labelId);
	    map.put("t", document);
	    
	    // 获取合同基本信息
	    Document doc = documentDao.selectById(map);
	    if(null == doc){
	    	return null;
	    }

	    // 获取合同明细
	    List<DocumentDetail> docList= documentDao.selectDetail(map);
	    for(DocumentDetail docs : docList){
	    	docs.setId((this.getLabelId(docs.getId())));
	    }
	    doc.setLabels(docList);
	    
		// 获取合同图片
		String itemId = String.format("%s_document_%s", document.getCompanyId(), labelId);
    	Map<Integer,String> mapPicPath = fileManager.readImgFile(itemId);
    	// 读取失败
    	if(null == mapPicPath)
    		log.error("合同图片获取时失败:" + itemId);
    	
	    List<ImgFile> imgFiles = fileManager.getFiles(mapPicPath);
		doc.setImgs(imgFiles);
		
	    return doc;
	}
	
	private BigDecimal getLabelId(BigDecimal id){
		String labelId = LableUtil.getCheckNum(id.toString());
		return new BigDecimal(labelId);
	}

	@Override
	public void upload(BigDecimal companyId, BigDecimal id,List<MultipartFile> imgFiles, BigDecimal workMode) {
		BigDecimal labelId=new BigDecimal(id.toString().substring(0, 12));
		String itemId = String.format("%s_document_%s", companyId, labelId);
		if(!fileManager.saveUploadMultipartFile(imgFiles, itemId))
			log.error("合同上传时失败:" + itemId);
	}
}
