package org.jymf.web.admin;
import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jymf.entity.Admin;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.entity.Product;
import org.jymf.entity.ProductConfig;
import org.jymf.entity.ProductType;
import org.jymf.interceptor.Token;
import org.jymf.service.ICompanyService;
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
 * @date   2014年7月29日
 */
@Controller
@RequestMapping(value = "/admin/product")
public class AdminProductController{
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	/**
	 * 进入产品显示画面
	 * @return
	 */
	@RequestMapping(value = "main")
	public String init(Model model, Product product,HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		product.setCompanyId(companyBase.getId());
		pageView = productService.query(pageView, product,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("product", product);
		model.addAttribute("company", companyBase);
        return String.format("/admin/product/%s/main",companyBase.getWorkMode());
	}

	/**
	 * 进入规格参数录入画面
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save() {
		return "/admin/product/save";
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
        
        return String.format("/admin/product/%s/main",companyBase.getWorkMode());
    }

	/**
	 * 进入产品添加画面
	 * @return
	 */
	@RequestMapping(value = "add")
	@Token(save=true)
	public String add(Model mode,HttpSession session,Map<String, Object> map) {
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0 ||
				companyBase.getWorkMode().compareTo(Constants.WM_HJC)==0 ){
			String typeJson = productTypeService.getAllJsonType(companyBase.getId(),companyBase.getWorkMode());
			mode.addAttribute("productType",typeJson);
		}
		
		if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0){
			ProductConfig productConfig = productService.getProductPara(companyBase.getWorkMode().longValue());
			if(productConfig.getCompanyId().contains(companyBase.getId().toString())){
    			mode.addAttribute("productConfig",productConfig);
    		}else{
    			mode.addAttribute("productConfig",null);
    		}
		}
		// 消费模式
		map.put("consumeTypeMap", Constants.consumeTypeMap);
		map.put("product", new Product());
		return String.format("/admin/product/%s/add",companyBase.getWorkMode());
	}
	
	/**
	 * 将产品添加画面添加内容写入数据库
	 * @param company
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.POST)
	@Token(remove=true)
	public String add(Product product, HttpSession session) {
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		product.setCompanyId(companyBase.getId());
		Admin user= (Admin)session.getAttribute(Constants.SESSION_ADMINUSER);
		
		product.setAuditUser(user.getName());
		product.setStatus(new BigDecimal(1));
		
		productService.add(product,
				           companyBase.getName(),
				           companyBase.getUrl(),
				           webUrl+Constants.CACHE_FOLDER,
				           companyBase.getWorkMode());
		
		String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
		businessLogger.log("product_add", user.getAccount(), companyBase.getId(),logStr,product);

        return "redirect:/admin/product/main";
	}
    
    /**
     * 进入产品更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, Model model, HttpSession session) {
    	CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
    	Product product = productService.findById(id,companyBase.getId(),true,companyBase.getWorkMode());
        
    	// 匹配产品类型
    	ProductType productType=new ProductType();
    	if(null != product.getType() && !"".equals(product.getType())){
    		productType = productTypeService.findProductTypeByTypeId(
        										product.getType(),
        										companyBase.getId(),
        										companyBase.getWorkMode());
        }

    	model.addAttribute("productTypeName", productType.getTypeName());
    	
    	if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0||
				companyBase.getWorkMode().compareTo(Constants.WM_HJC)==0){
    		String typeJson = productTypeService.getAllJsonType(companyBase.getId(),companyBase.getWorkMode());
    		model.addAttribute("productType",typeJson);
    	}
    	
    	model.addAttribute("product", product);
        
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
    	
    	String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("product_update_before", user, product);
		
        return String.format("/admin/product/%s/edit",companyBase.getWorkMode());
    }
    
	/**
	 * 更新产品信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product, HttpSession session) {
		//接收图片文件改为接收图片名称
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		product.setCompanyId(companyBase.getId());
		Admin admin= (Admin)session.getAttribute(Constants.SESSION_ADMINUSER);
		if(null != product.getStatus() && product.getStatus().equals(new BigDecimal(1))){
			product.setAuditUser(admin.getName());
		}
		if(productService.update(product,webUrl+Constants.CACHE_FOLDER,companyBase.getWorkMode())) {
			String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
			businessLogger.log("product_update", admin.getAccount(), companyBase.getId(), logStr, product);
			businessLogger.log("product_update", admin.getAccount(), product);
		}
        return "redirect:/admin/product/main";
	}
	
	/**
	 * 进入产品类型添加页面
	 * @author wfj 2014.4.27
	 */
	@RequestMapping(value="productType/{companyId}/{typePid}/{workMode}",method=RequestMethod.GET)
	public String productType(@PathVariable("companyId")BigDecimal companyId,
							  @PathVariable("typePid")BigDecimal typePid,
							  @PathVariable("workMode")BigDecimal workMode,
							  HttpSession session
							 ) {
		Company company = companyService.findById(companyId,false);
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyId);
		companyBase.setWorkMode(workMode);
		companyBase.setName(company.getName());
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		
		ProductType productType=new ProductType();
		productType.setParentId(typePid);
		session.setAttribute(Constants.SESSION_PRODUCT_TYPE, productType);
		
		return "redirect:/admin/productType/main";
	}
	
    /**
     * 进入产品克隆画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "clone/{id}", method = RequestMethod.GET)
    @Token(save=true)
    public String clone(@PathVariable("id") BigDecimal id, Model model, HttpSession session) {
    	CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
    	Product product = productService.findById(id,companyBase.getId(),true,companyBase.getWorkMode());
           	
    	// 匹配产品类型
    	ProductType productType=new ProductType();
    	if(null != product.getType() && !"".equals(product.getType())){
    		productType = productTypeService.findProductTypeByTypeId(
        										product.getType(),
        										companyBase.getId(),
        										companyBase.getWorkMode());
        }

    	model.addAttribute("productTypeName", productType.getTypeName());
    	
    	if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0 ||
				companyBase.getWorkMode().compareTo(Constants.WM_HJC)==0){
    		String typeJson = productTypeService.getAllJsonType(companyBase.getId(),companyBase.getWorkMode());
    		model.addAttribute("productType",typeJson);
    	}
    	
    	model.addAttribute("product", product);
        
    	if(companyBase.getWorkMode().compareTo(Constants.WM_JL)==0){
    		ProductConfig productConfig = productService.getProductPara(companyBase.getWorkMode().longValue());
    		if(productConfig.getCompanyId().contains(companyBase.getId().toString())){
    			model.addAttribute("productConfig",productConfig);
    		}else{
    			model.addAttribute("productConfig",null);
    		}
    	}
    	
    	String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("product_clone", user, product);
		
		// 消费模式
		model.addAttribute("consumeTypeMap", Constants.consumeTypeMap);
		
        return String.format("/admin/product/%s/clone",companyBase.getWorkMode());
    }
	
    /**
	 * 将产品添加画面添加内容写入数据库
	 * @param company
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="cloneAdd", method = RequestMethod.POST)
	@Token(remove=true)
	public String cloneAdd(Product product, HttpSession session) {
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyBase companyBase = (CompanyBase)session.getAttribute(Constants.SESSION_COMPANY_BASE);
		//新增页面sum=3 修改页面sum=0，要修改顺序
		product.setCompanyId(companyBase.getId());
		
		Admin admin= (Admin)session.getAttribute(Constants.SESSION_ADMINUSER);
		product.setAuditUser(admin.getName());
		product.setStatus(new BigDecimal(1));
		productService.add(product,
				           companyBase.getName(),
				           companyBase.getUrl(),
				           webUrl+Constants.CACHE_FOLDER,
				           companyBase.getWorkMode());
		
		String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
		businessLogger.log("product_clone", admin.getAccount(), companyBase.getId(),logStr,product);
		businessLogger.log("product_clone", admin.getAccount(), product);
		
        return "redirect:/admin/product/main";
	}
    
    
}
