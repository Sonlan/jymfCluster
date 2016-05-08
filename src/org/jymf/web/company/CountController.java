package org.jymf.web.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.jymf.entity.AcInfo;
import org.jymf.entity.CompanyCount;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.OutPutCount;
import org.jymf.entity.Product;
import org.jymf.service.IAcInfoService;
import org.jymf.service.ICompanyService;
import org.jymf.service.IOutPutCountService;
import org.jymf.service.IProductService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 企业用户管理Controller
 * @author cqs
 * @date   2014年7月16日
 */
@Controller
@RequestMapping(value = "/company/count")
public class CountController{

	@Autowired
    private ICompanyService companyService;
	
	@Autowired
    private IOutPutCountService outPutCountService;
		
	@Autowired
    private IProductService productService;

    @Autowired
    private IAcInfoService acInfoService;
	
	/**
	 * 进入企业用户一览显示画面
	 * @return
	 */
	@RequestMapping(value = "main")
	public String init(Model model,CompanyCount companyCount, HttpSession session) {

		PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		companyCount.setCompanyId(companyUser.getCompanyId());
		companyCount.setStartDate(Common.yesterdayModthFirstday());
		companyCount.setEndDate(Common.yesterday());
		
		pageView = companyService.queryCountByCompany(pageView, companyCount, companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
				
		model.addAttribute("companyCount", companyCount);

        return String.format("/company/count/%s/main",companyUser.getWorkMode());
	}
	
	/**
	 * 检索查询
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model,CompanyCount companyCount, String pageNow,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }

        CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		companyCount.setCompanyId(companyUser.getCompanyId());
			
		pageView = companyService.queryCountByCompany(pageView, companyCount, companyUser.getWorkMode());
				
		model.addAttribute("companyCount", companyCount);
        model.addAttribute("pageView", pageView);
    	
        return String.format("/company/count/%s/main",companyUser.getWorkMode());
    }
    
    
    
    /**
	 * 进入企业用户一企业,出库关联的统计
	 * @return
	 */
	@RequestMapping(value = "outPutMain")
	public String outPutMain(Model model,OutPutCount outPutCount, HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		outPutCount.setCompanyId(companyUser.getCompanyId());
		outPutCount.setStartDate(Common.yesterdayModthFirstday());
		outPutCount.setEndDate(Common.yesterday());
		
		pageView = outPutCountService.queryOutPutCount(pageView,outPutCount, companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("outPutCount", outPutCount);
		
        return String.format("/company/count/%s/outputcnt",companyUser.getWorkMode());
	}
	
	/**
	 * 变换查询条件后检索结果
	 * @return
	 */
	@RequestMapping(value = "queryOutPutCnt")
	public String queryOutPutCount(Model model,OutPutCount outPutCount,String pageNow, HttpSession session) {
	    PageView pageView = null;
	    if (Common.isEmpty(pageNow)) {
	        pageView = new PageView(1);
	    } else {
	        pageView = new PageView(Integer.parseInt(pageNow));
	    }
	        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		outPutCount.setCompanyId(companyUser.getCompanyId());
		
		pageView = outPutCountService.queryOutPutCount(pageView,outPutCount, companyUser.getWorkMode());
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("outPutCount", outPutCount);
		
		return String.format("/company/count/%s/outputcnt",companyUser.getWorkMode());
	}
	
	
    /**
     * 窜货统计查询
     * @param model
     * @return
     */
    @RequestMapping("acCntMain")
    public String acCntMain(Model model, AcInfo acInfo, String pageNow ,HttpSession session) {
    	PageView pageView = new PageView(1);
    	acInfo.setStartDate(Common.yesterdayModthFirstday());
    	acInfo.setEndDate(Common.yesterday());
        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		acInfo.setCompanyId(companyUser.getCompanyId());
		
        pageView = acInfoService.queryCnt(pageView, acInfo, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("acInfo", acInfo);
        
        return String.format("/company/count/%s/accnt",companyUser.getWorkMode());
    }
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("queryaccnt")
    public String queryAcCnt(Model model, AcInfo acInfo, String pageNow ,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		acInfo.setCompanyId(companyUser.getCompanyId());
		
        pageView = acInfoService.queryCnt(pageView, acInfo, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("acInfo", acInfo);
        
        return String.format("/company/count/%s/accnt",companyUser.getWorkMode());
    }
    
    
	/**
	 * 进入产品销售统计画面
	 * @return
	 */
	@RequestMapping(value = "salescnt",method = RequestMethod.GET)
	public String salesCnt(Model model, Product product,HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		product.setCompanyId(companyUser.getCompanyId());
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		product.setStartDate(sdf.format(sdate));
		product.setEndDate(sdf.format(new Date()));
		pageView = productService.querySales(pageView, product,companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("product", product);
		
        String productback ="";
        if(null == product.getName()){
            productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),"");
        }else{
        	productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),product.getName());
        }
        	 	
        productback =String.format("%s&startDate=%s&endDate=%s",productback,product.getStartDate(),product.getEndDate());
        
        session.setAttribute(Constants.SESSION_PRODUCT_BACK, productback);
		//获取当前登录的监管用户
		return String.format("/company/count/%s/salescnt",companyUser.getWorkMode());
	}
	
	@RequestMapping(value = "salesCntQuery")
	public String salesCntQuery(Model model, Product product,String pageNow,HttpSession session) {
		PageView pageView = null;
	    if (Common.isEmpty(pageNow)) {
	        pageView = new PageView(1);
	    } else {
	        pageView = new PageView(Integer.parseInt(pageNow));
	    }
	        
	    CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		product.setCompanyId(companyUser.getCompanyId());

		pageView = productService.querySales(pageView, product,companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("product", product);
        
        String productback ="";
        if(null == product.getName()){
            productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),"");
        }else{
        	productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),product.getName());
        }
        	 	
        productback =String.format("%s&startDate=%s&endDate=%s",productback,product.getStartDate(),product.getEndDate());
        
        session.setAttribute(Constants.SESSION_PRODUCT_BACK, productback);
        
        return String.format("/company/count/%s/salescnt",companyUser.getWorkMode());
	}
}
