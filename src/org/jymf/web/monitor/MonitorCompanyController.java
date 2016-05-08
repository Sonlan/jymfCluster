package org.jymf.web.monitor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.entity.Document;
import org.jymf.entity.OptAction;
import org.jymf.entity.Product;
import org.jymf.service.ICompanyService;
import org.jymf.service.IDocumentService;
import org.jymf.service.IOptActionService;
import org.jymf.service.IProductService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 产品类型Controller
 * @author cqs
 * @date   2014年7月29日
 */
@Controller
@RequestMapping(value = "/monitor/company")
public class MonitorCompanyController{
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private IOptActionService optActionService;
	
    @Autowired
	private ICompanyService companyService;
	
    @Autowired
	private IDocumentService documentService;
    
	/**
	 * 进入指定企业画面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String company(@PathVariable("id") BigDecimal id, HttpSession session,Map<String, Object> map) {
		Company company = companyService.findById(id, true);
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(id);
		companyBase.setWorkMode(company.getWorkMode());
		companyBase.setName(company.getName());
		
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		if(company.getRateCnt()!=0){
		    company.setRate1(company.getRate1()/company.getRateCnt());
		    company.setRate2(company.getRate2()/company.getRateCnt());
		}
		map.put("company", company); 
		
		return "/monitor/company/view";
	}
	
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(HttpSession session,Map<String, Object> map) {
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		Company company = companyService.findById(companyBase.getId(), true);
						
		map.put("company", company); 
		
		return "/monitor/company/view";
	}
	
    /**
     * 返回企业列表页面
     */
    @RequestMapping(value= "back")
    public String back(HttpSession session){
    	session.removeAttribute(Constants.SESSION_COMPANY_BASE);
    	session.removeAttribute("productback");
    	return "redirect:/monitor/main"; 
    }
    
    /**
     * 进入合同管理画面
     */
    @RequestMapping(value= "document", method = RequestMethod.GET)
    public String document(Model model,Document document,HttpSession session){
		PageView pageView = new PageView(1);
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		document.setCompanyId(companyBase.getId());
		document.setStartDate(sdf.format(sdate));
		document.setEndDate(sdf.format(new Date()));
		
		pageView = documentService.query(pageView, document, companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("document", document);
		
		long labelCnt = labelCount(pageView.getRecords());
		model.addAttribute("labelCnt",labelCnt);
		
    	return "monitor/company/document/main"; 
    }
    
    /**
     * 进入合同管理画面(查询)
     */
    @RequestMapping(value= "document/query")
    public String documentQuery(Model model,Document document,String pageNow,HttpSession session){
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		document.setCompanyId(companyBase.getId());
		
		pageView = documentService.query(pageView, document, companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("document", document);
		
		long labelCnt = labelCount(pageView.getRecords());
		model.addAttribute("labelCnt",labelCnt);
		                    
    	return "monitor/company/document/main"; 
    }
    
    /**
     * 每页配件数量的统计
     * @param records
     * @return
     */
    private long labelCount(@SuppressWarnings("rawtypes") List records){
    	long labelCnt=0;
    	@SuppressWarnings("unchecked")
		List<Document> list = (List<Document>)records;
    	for(Document doc : list){
    		labelCnt= labelCnt+ doc.getCount().longValue();
    	}
    	return labelCnt;
    }
    /**
     * 进入合同详细
     */
    @RequestMapping(value= "document/view/{id}")
    public String documentView(@PathVariable("id") BigDecimal id,Model model,Document document,HttpSession session){
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		document.setCompanyId(companyBase.getId());
		document.setId(id);
		
		document = documentService.selectDetail(document, companyBase.getWorkMode());
		document.setId(id);
		
		model.addAttribute("document", document);
		
    	return "monitor/company/document/view"; 
    }
    
	/**
	 * 进入产品显示画面
	 * @return
	 */
	@RequestMapping(value = "product")
	public String init(Model model, Product product,HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		product.setCompanyId(companyBase.getId());
		pageView = productService.query(pageView, product,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("product", product);

		//获取当前登录的监管用户
		return String.format("/monitor/company/product/%s/main",companyBase.getWorkMode());
	}

	/**
     * @param model
     * 监管模式下，产品列表查询画面
     * 目前查询条件：产品名称/产品ID
     * @return
     */
    @RequestMapping("product/query")
    public String query(Model model, Product product, String pageNow,HttpSession session) {
    	//获取当前登录的监管用户
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		product.setCompanyId(companyBase.getId());
        pageView = productService.query(pageView, product,companyBase.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("product", product);
        return String.format("/monitor/company/product/%s/main",companyBase.getWorkMode());
    }
   
    /**
     * 进入产品类型查看画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "product/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") BigDecimal id, Model model, HttpSession session) {
    	CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
    	Product product = productService.findById(id,companyBase.getId(),true,companyBase.getWorkMode());
        model.addAttribute("product", product);
        
        return String.format("monitor/company/product/%s/view", companyBase.getWorkMode());
    }
    
    
    /**
     * 查看销售数量信息(初次进入)
     */
    @RequestMapping("product/queryopt/{productId}/{startDate}/{endDate}")
    public String queryOptAction(@PathVariable("productId") BigDecimal productId,
    		                     @PathVariable("startDate") String startDate,
    		                     @PathVariable("endDate") String endDate,
    		                     Model model, 
    		                     OptAction optAction, 
    		                     String pageNow,
    		                     HttpSession session) {
    	//获取当前登录的监管用户
        PageView pageView = null;
        pageView = new PageView(1);
        CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
               
        //保存从上级页面获取的产品ID
        optAction.setProductId(productId);
        optAction.setCompanyId(companyBase.getId());
        
        optAction.setStartDate(startDate);
        optAction.setEndDate(endDate);
        
        pageView = optActionService.queryOptAction(pageView, optAction, companyBase.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("optAction", optAction);
        return "/monitor/company/product/3/sellMain";
    }
    
    /**
     * 查看销售数量信息(分页，上一页/下一页的处理)
     */
    @RequestMapping("product/queryopt")
    public String queryOptAction(Model model, 
    		                     OptAction optAction, 
    		                     String pageNow,
    		                     HttpSession session) {
    	//获取当前登录的监管用户
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
               
        //保存从上级页面获取的产品ID
        optAction.setCompanyId(companyBase.getId());
                      
        pageView = optActionService.queryOptAction(pageView, optAction, companyBase.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("optAction", optAction);
        return "/monitor/company/product/3/sellMain";
    }
    
	/**
	 * 进入产品销售统计画面
	 * @return
	 */
	@RequestMapping(value = "salescnt",method = RequestMethod.GET)
	public String salesCnt(Model model, Product product,HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		product.setCompanyId(companyBase.getId());
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		product.setStartDate(sdf.format(sdate));
		product.setEndDate(sdf.format(new Date()));
		pageView = productService.querySales(pageView, product,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("pro", product);
		
		String productback ="";
        if(null == product.getName()){
            productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),"");
        }else{
        	productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),product.getName());
        }

        productback =String.format("%s&startDate=%s&endDate=%s",productback,product.getStartDate(),product.getEndDate());
	        
		session.setAttribute(Constants.SESSION_PRODUCT_BACK, productback);
		
		 long salesCnt = salesCnt(pageView.getRecords());
	     long queryCnt = queryCnt(pageView.getRecords());
	     model.addAttribute("salesCnt", salesCnt);
	     model.addAttribute("queryCnt", queryCnt);
	        
		//获取当前登录的监管用户
		return String.format("/monitor/company/salescnt",companyBase.getWorkMode());
	}
	
	@RequestMapping(value = "salesCntQuery")
	public String salesCntQuery(Model model, Product product,String pageNow,HttpSession session) {
		PageView pageView = null;
	    if (Common.isEmpty(pageNow)) {
	        pageView = new PageView(1);
	    } else {
	        pageView = new PageView(Integer.parseInt(pageNow));
	    }
	        
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		product.setCompanyId(companyBase.getId());

		pageView = productService.querySales(pageView, product,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("pro", product);
        
        String productback ="";
        if(null == product.getName()){
            productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),"");
        }else{
        	productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),product.getName());
        }

        productback =String.format("%s&startDate=%s&endDate=%s",productback,product.getStartDate(),product.getEndDate());
        
        session.setAttribute(Constants.SESSION_PRODUCT_BACK, productback);
        
        long salesCnt = salesCnt(pageView.getRecords());
        long queryCnt = queryCnt(pageView.getRecords());
        model.addAttribute("salesCnt", salesCnt);
        model.addAttribute("queryCnt", queryCnt);
        
		return String.format("/monitor/company/salescnt",companyBase.getWorkMode());
	}
	
	private long salesCnt(@SuppressWarnings("rawtypes") List records){
    	long salesCnt=0;
    	@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)records;
    	for(Product product : list){
    		salesCnt= salesCnt+ product.getLabelCnt().longValue();
    	}
    	return salesCnt;
	}
	
	private long queryCnt(@SuppressWarnings("rawtypes") List records){
    	long queryCnt=0;
    	@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)records;
    	for(Product product : list){
    		queryCnt= queryCnt+ product.getConsNum().longValue();
    	}
    	return queryCnt;
	}
}
