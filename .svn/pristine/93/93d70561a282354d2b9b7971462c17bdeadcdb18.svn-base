package org.jymf.service;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.Document;
import org.jymf.utils.PageView;
import org.springframework.web.multipart.MultipartFile;
/**
 * 合同管理_汽配模式_包标签ID=合同ID
 * @author cqs
 * @date   2014年11月18日
 */
public interface IDocumentService {

	/**
     * 分页处理
     * @param pageView
     * @param document
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    PageView query(PageView pageView, Document document, BigDecimal workMode);
    
    /**
     * 查询和同详细
     * @param document
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    Document selectDetail(Document document, BigDecimal workMode);
    
    /**
     * 
     * @param companyId
     * @param id
     * @param imgFiles
     * @param workMode:根据追溯模式不同，会访问不同的DB
     */
    void upload(BigDecimal companyId,BigDecimal id,List<MultipartFile> imgFiles, BigDecimal workMode);
}
