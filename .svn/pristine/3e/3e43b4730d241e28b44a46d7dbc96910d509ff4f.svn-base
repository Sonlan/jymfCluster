package org.jymf.web.company;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyUser;
import org.jymf.entity.Document;
import org.jymf.service.IDocumentService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author cqs
 * @date   2014年11月20日
 */
@Controller
@RequestMapping(value = "/company/document")
public class DocumentController{
    
    @Autowired
	private IDocumentService documentService;
    
    /**
     * 进入合同管理画面
     */
    @RequestMapping(value= "main", method = RequestMethod.GET)
    public String main(Model model,Document document,HttpSession session){
		PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		document.setCompanyId(companyUser.getCompanyId());
		document.setStartDate(sdf.format(sdate));
		document.setEndDate(sdf.format(new Date()));
		
		pageView = documentService.query(pageView, document, companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("document", document);
		
    	return "company/document/main"; 
    }
    
    /**
     * 进入合同管理画面(查询)
     */
    @RequestMapping(value= "query")
    public String query(Model model,Document document,String pageNow,HttpSession session){
    	PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		document.setCompanyId(companyUser.getCompanyId());
		
		pageView = documentService.query(pageView, document, companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("document", document);
		
    	return "company/document/main"; 
    }
    
    /**
     * 进入合同详细
     */
    @RequestMapping(value= "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") BigDecimal id,Model model,Document document,HttpSession session){
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		document.setCompanyId(companyUser.getCompanyId());
		document.setId(id);
		
		document = documentService.selectDetail(document, companyUser.getWorkMode());
		document.setId(id);
		
		model.addAttribute("document", document);
		if(null == document.getImgs() || document.getImgs().size()==0){
			return "company/document/add";
		}else{
			return "company/document/view";
		}
    }
           
    /**
     * 进入合同添加画面
     */
    @RequestMapping(value= "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("document") Document document,HttpServletRequest request,HttpSession session){
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFiles = multipartRequest.getFiles("docImgs");
	    documentService.upload(companyUser.getCompanyId(), document.getId(), imgFiles, companyUser.getWorkMode());
    	return "redirect:/company/document/main";
    	
    }
 
}
