package org.jymf.web.agent;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.jymf.entity.AgentMonitor;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.OptAction;
import org.jymf.entity.Product;
import org.jymf.entity.ProductConfig;
import org.jymf.entity.ProductType;
import org.jymf.interceptor.Token;
import org.jymf.service.ICompanyService;
import org.jymf.service.IOptActionService;
import org.jymf.service.IProductService;
import org.jymf.service.IProductTypeService;
import org.jymf.service.impl.BusinessLogger;
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

/**
 * 产品类型Controller
 * @author cqs
 * @date   2014年11月11日
 */
@Controller
@RequestMapping(value = "/agent/product")
public class AgentProductController{
	
	@Autowired
    private IProductService productService;

	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	@Autowired
	private IOptActionService optActionService;
	
	/**
	 * 进入产品显示画面
	 * @return
	 */
	@RequestMapping(value = "main/{companyId}")
	public String init(Model model, Product product,@PathVariable("companyId")BigDecimal companyId,
					   String pageNow,HttpSession session) {
		AgentMonitor agent = (AgentMonitor) session.getAttribute(Constants.SESSION_AGENT);
		Company company = companyService.findById(companyId,false);
		
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId(String.valueOf(agent.getId()));
		companyUser.setCompanyId(companyId);
		companyUser.setWorkMode(company.getWorkMode());
		companyUser.setCompanyName(company.getName());
		companyUser.setCompanyUrl(company.getUrl());
		session.setAttribute(Constants.SESSION_COMPANY_USER, companyUser);
		
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyId);
		companyBase.setWorkMode(company.getWorkMode());
		session.setAttribute(Constants.SESSION_COMPANY_BASE,companyBase);
		
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
	        pageView = new PageView(1);
	    } else {
	        pageView = new PageView(Integer.parseInt(pageNow));
	    }
		
		product.setCompanyId(companyId);
		pageView = productService.query(pageView, product,company.getWorkMode());
		
		model.addAttribute("pageView", pageView);

        return String.format("/agent/product/%s/main",company.getWorkMode());
	}
	
	/**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, Product product, String pageNow,HttpSession session) {
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
        
        return String.format("/agent/product/%s/main",companyBase.getWorkMode());
    }
    
    /**
	 * 进入产品添加画面
	 * @return
	 */
	@RequestMapping(value = "add")
	@Token(save=true)
	public String add(Model model,HttpSession session) {
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		
		if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0||
				companyBase.getWorkMode().compareTo(Constants.WM_HJC)==0){
			String typeJson = productTypeService.getAllJsonType(companyBase.getId(),companyBase.getWorkMode());
			model.addAttribute("productType",typeJson);
		}
		
		if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0){
			ProductConfig productConfig = productService.getProductPara(companyBase.getWorkMode().longValue());
			if(productConfig.getCompanyId().contains(companyBase.getId().toString())){
    			model.addAttribute("productConfig",productConfig);
    		}else{
    			model.addAttribute("productConfig",null);
    		}
		}
		// 消费模式
		model.addAttribute("consumeTypeMap", Constants.consumeTypeMap);
		model.addAttribute("product", new Product());
		return String.format("/agent/product/%s/add", companyBase.getWorkMode());
	}
    
	/**
	 * 将产品添加画面添加内容写入数据库
	 */
	@RequestMapping(value="add", method = RequestMethod.POST)
	@Token(remove=true)
	public String add(Product product, HttpSession session) {
		
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
				
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
		product.setStatus(new BigDecimal(0));
		
		productService.add(product,
				           companyUser.getCompanyName(),
				           companyUser.getCompanyUrl(),
				           webUrl+Constants.CACHE_FOLDER,
				           companyUser.getWorkMode());
		
		String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
		businessLogger.log("product_add", 
				           String.format("%s_%s", companyUser.getId(), companyUser.getCompanyId()), 
				           companyUser.getCompanyId(),
				           logStr,product);
			
		AgentMonitor agent =  (AgentMonitor)session.getAttribute(Constants.SESSION_AGENT);
		productService.sendMail(String.format("代理商_%s,为企业",agent.getName()), companyUser.getCompanyName(), product.getName());
		
		return String.format("redirect:/agent/product/main/%s", companyUser.getCompanyId());
	}
    
	/**
     * 进入产品类型查看画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") BigDecimal id, Model model, HttpSession session) {
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
    	Product product = productService.findById(id,companyUser.getCompanyId(),true,companyUser.getWorkMode());
    	product.setConsumeTypeName(Constants.consumeTypeMap.get(product.getConsumeType()==null?"0":product.getConsumeType().toString()));
        model.addAttribute("product", product);
    
        return String.format("/agent/product/%s/view", companyUser.getWorkMode());
    }
    
    /**
     * 进入产品类型更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, Model model, HttpSession session) {
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
    	Product product = productService.findById(id,companyUser.getCompanyId(),true,companyUser.getWorkMode());
        model.addAttribute("product", product);
        
        //取得产品原类型 
        CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyUser.getCompanyId());
		companyBase.setName(companyUser.getCompanyName());
		companyBase.setWorkMode(companyUser.getWorkMode());
        ProductType productType=new ProductType();
    	if(null != product.getType() && !"".equals(product.getType())){
    		productType = productTypeService.findProductTypeByTypeId(
        												product.getType(),
        												companyBase.getId(),
        												companyBase.getWorkMode());
        }

    	model.addAttribute("productTypeName", productType.getTypeName());
    	
    	if(companyUser.getWorkMode().compareTo(Constants.WM_JL)==0 ||
				companyBase.getWorkMode().compareTo(Constants.WM_HJC)==0){
    		String typeJson = productTypeService.getAllJsonType(companyBase.getId(), companyBase.getWorkMode());
        	model.addAttribute("productType",typeJson);
    	}
    	
    	if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0){
	    	ProductConfig productConfig = productService.getProductPara(companyBase.getWorkMode().longValue());
	    	if(productConfig.getCompanyId().contains(companyBase.getId().toString())){
    			model.addAttribute("productConfig",productConfig);
    		}else{
    			model.addAttribute("productConfig",null);
    		}
    	}

		businessLogger.log("product_update_before", 
				           String.format("%s_%s", companyUser.getId(), companyUser.getCompanyId()), 
				           product);
    	// 消费模式
    	model.addAttribute("consumeTypeMap", Constants.consumeTypeMap);
       
		return String.format("/agent/product/%s/edit", companyUser.getWorkMode());
    }
    
	/**
	 * 更新产品信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product, HttpSession session) {
		
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
		if(productService.update(product,webUrl+Constants.CACHE_FOLDER,companyUser.getWorkMode())){		
			String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
			businessLogger.log("product_update", 
					           String.format("%s_%s", companyUser.getId(), companyUser.getCompanyId()), 
			                   companyUser.getCompanyId(), logStr,product);
			businessLogger.log("product_update", companyUser.getId(), product);
		}
		return String.format("redirect:/agent/product/main/%s", companyUser.getCompanyId());
	}
	
	/**
     * 查看销售数量信息(初次进入)
     */
    @RequestMapping("queryopt/{productId}")
    public String queryOptAction(@PathVariable("productId") BigDecimal productId,
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
               
        pageView = optActionService.queryOptAction(pageView, optAction, companyBase.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("optAction", optAction);
        return "/agent/product/3/sellMain";
    }
    
    /**
     * 查看销售数量信息(分页，上一页/下一页的处理)
     */
    @RequestMapping("queryopt")
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
        return "/agent/product/3/sellMain";
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
		return String.format("/agent/product/%s/salescnt",companyBase.getWorkMode());
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
		model.addAttribute("product", product);
        
        String productback ="";
        if(null == product.getName()){
            productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),"");
        }else{
        	productback =String.format("?pageNow=%s&name=%s",pageView.getPageNow(),product.getName());
        }
         	
        productback =String.format("%s&startDate=%s&endDate=%s",productback,product.getStartDate(),product.getEndDate());
        
        session.setAttribute(Constants.SESSION_PRODUCT_BACK, productback);
        
        return String.format("/agent/product/%s/salescnt",companyBase.getWorkMode());
	}
}
