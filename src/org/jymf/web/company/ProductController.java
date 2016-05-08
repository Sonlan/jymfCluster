package org.jymf.web.company;
import java.io.File;
import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyBase;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.OptAction;
import org.jymf.entity.Product;
import org.jymf.entity.ProductConfig;
import org.jymf.entity.ProductType;
import org.jymf.interceptor.Token;
import org.jymf.service.IOptActionService;
import org.jymf.service.IProductService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.service.impl.ProductTypeServiceImpl;
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
 * @author lxg
 * @date   2014年5月19日
 */
@Controller
@RequestMapping(value = "/company/product")
public class ProductController{
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private ProductTypeServiceImpl productTypeService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	@Autowired
	private IOptActionService optActionService;
	
	/**
	 * 进入产品显示画面
	 * @return
	 */
	@RequestMapping(value = "main")
	public String init(Model model, Product product,HttpSession session) {
		PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
		
		pageView = productService.query(pageView, product,companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("product", product);
		model.addAttribute("orderByMap", Constants.orderByMap);
		
		return String.format("/company/product/%s/main", companyUser.getWorkMode());
	}

	/**
	 * 进入规格参数录入画面
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save() {
		return "/company/product/save";
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
        CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
        pageView = productService.query(pageView, product,companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("product", product);
        model.addAttribute("orderByMap", Constants.orderByMap);
        
        return String.format("/company/product/%s/main", companyUser.getWorkMode());
    }

	/**
	 * 进入产品添加画面
	 * @return
	 */
	@RequestMapping(value = "add")
	@Token(save=true)
	public String add(Model model,HttpSession session) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
/*		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyUser.getCompanyId());
		companyBase.setName(companyUser.getCompanyName());
		companyBase.setWorkMode(companyUser.getWorkMode());*/
		
		if(companyUser.getWorkMode().compareTo(Constants.WM_JL)==0 ||
				companyUser.getWorkMode().compareTo(Constants.WM_HJC)==0){
			String typeJson = productTypeService.getAllJsonType(companyUser.getCompanyId(),companyUser.getWorkMode());
			model.addAttribute("productType",typeJson);
		}
		
		if(companyUser.getWorkMode().compareTo(Constants.WM_JL)==0){
			ProductConfig productConfig = productService.getProductPara(companyUser.getWorkMode().longValue());
			if(productConfig.getCompanyId().contains(companyUser.getCompanyId().toString())){
    			model.addAttribute("productConfig",productConfig);
    		}else{
    			model.addAttribute("productConfig",null);
    		}
		}
		// 消费模式
		model.addAttribute("consumeTypeMap", Constants.consumeTypeMap);
		model.addAttribute("product", new Product());
		return String.format("/company/product/%s/add", companyUser.getWorkMode());
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
		
		// wfj  update at 2015.4.21 修改上传文件为post的文件名 
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
		businessLogger.log("product_add", companyUser.getId(), companyUser.getCompanyId(), logStr,product);

		productService.sendMail("企业", companyUser.getCompanyName(), product.getName());
		
		return "redirect:/company/product/main";
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
        
        //取得产品原类型 update by wfj 2015.4.28
/*        CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyUser.getCompanyId());
		companyBase.setName(companyUser.getCompanyName());
		companyBase.setWorkMode(companyUser.getWorkMode());*/
        ProductType productType=new ProductType();
    	if(null != product.getType() && !"".equals(product.getType())){
    		productType = productTypeService.findProductTypeByTypeId(
        												product.getType(),
        												companyUser.getCompanyId(),
        												companyUser.getWorkMode());
        }

    	model.addAttribute("productTypeName", productType.getTypeName());
    	
    	if(companyUser.getWorkMode().compareTo(Constants.WM_JL)==0||
    			companyUser.getWorkMode().compareTo(Constants.WM_HJC)==0 ){
    		String typeJson = productTypeService.getAllJsonType(companyUser.getCompanyId(), companyUser.getWorkMode());
        	model.addAttribute("productType",typeJson);
    	}
    	
    	if(companyUser.getWorkMode().compareTo(Constants.WM_JL)==0){
	    	ProductConfig productConfig = productService.getProductPara(companyUser.getWorkMode().longValue());
	    	if(productConfig.getCompanyId().contains(companyUser.getCompanyId().toString())){
    			model.addAttribute("productConfig",productConfig);
    		}else{
    			model.addAttribute("productConfig",null);
    		}
    	}
        //end 2015.4.28

		businessLogger.log("product_update_before", companyUser.getId(), product);
		
    	// 消费模式
    	model.addAttribute("consumeTypeMap", Constants.consumeTypeMap);
       
		return String.format("/company/product/%s/edit", companyUser.getWorkMode());
    }
    
	/**
	 * 更新产品信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product, HttpSession session) {
		
		//update by wfj @ 2015.4.21 接收图片文件改为接收图片名称
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
		if(productService.update(product,webUrl+Constants.CACHE_FOLDER,companyUser.getWorkMode())){		
			String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
			businessLogger.log("product_update", companyUser.getId(), companyUser.getCompanyId(), logStr,product);
			businessLogger.log("product_update", companyUser.getId(), product);
		}
		return "redirect:/company/product/main";
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
    
        return String.format("/company/product/%s/view", companyUser.getWorkMode());
    }
    
	/**
	 * 商户单选画面,用于设备管理 商户ID选择
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "selectProduct")
	public String selectProduct(Model model, Product product, String pageNow,HttpSession session) {
		
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		product.setCompanyId(companyUser.getCompanyId());
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = productService.selectProduct(pageView, product,companyUser.getWorkMode());

		model.addAttribute("product", product);
		model.addAttribute("pageView", pageView);
		return "/company/dialog/selectProductQuery";
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
        CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
               
        //保存从上级页面获取的产品ID
        optAction.setProductId(productId);
        optAction.setCompanyId(companyUser.getCompanyId());
               
        pageView = optActionService.queryOptAction(pageView, optAction, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("optAction", optAction);
        return "/company/count/3/sellMain";
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
        CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
               
        //保存从上级页面获取的产品ID
        optAction.setCompanyId(companyUser.getCompanyId());
               
        pageView = optActionService.queryOptAction(pageView, optAction, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("optAction", optAction);
        return "/company/count/3/sellMain";
    }
    
    /**
     * 进入产品克隆画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "clone/{id}", method = RequestMethod.GET)
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
		
        return String.format("/company/product/%s/clone",companyBase.getWorkMode());
    }
	
    /**
	 * 将产品添加画面添加内容写入数据库
	 * @param company
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="cloneAdd", method = RequestMethod.POST)
	public String cloneAdd(Product product, HttpSession session) {
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		//新增页面sum=3 修改页面sum=0，要修改顺序
    	product.setCompanyId(companyUser.getCompanyId());
		product.setStatus(new BigDecimal(0));
	
		productService.add(product,
		           companyUser.getCompanyName(),
		           companyUser.getCompanyUrl(),
		           webUrl+Constants.CACHE_FOLDER,
		           companyUser.getWorkMode());
		String logStr = String.format("ProductId:%s,Name:%s", product.getId(), product.getName());
		businessLogger.log("product_add", companyUser.getId(), companyUser.getCompanyId(), logStr,product);
		
		productService.sendMail("企业", companyUser.getCompanyName(), product.getName());
		
        return "redirect:/company/product/main";
	}
	
}
