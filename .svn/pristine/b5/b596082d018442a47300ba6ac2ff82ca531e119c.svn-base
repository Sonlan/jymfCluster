package org.jymf.web.admin;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyBase;
import org.jymf.entity.ProductType;
import org.jymf.service.IProductTypeService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wfj
 * 产品类型controller
 */
@Controller
@RequestMapping(value="/admin/productType")
public class ProductTypeController {

	@Autowired
	private IProductTypeService productTypeService;
	
	/**
	 * 进入指定企业的产品类型
	 * @param companyId
	 * @param typepId 类型的父ID 进入初始页面typepId=0
	 * @param model
	 * @return 
	 */
	@RequestMapping(value = "main")
	public String productType(Model model,String pageNow, HttpSession session) {
		
		CompanyBase companyBase=(CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType productType=(ProductType) session.getAttribute(Constants.SESSION_PRODUCT_TYPE);
		
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = productTypeService.queryProductType(pageView,companyBase.getId(),
												   productType.getParentId(),companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", companyBase);
		
		return "/admin/productType/main";
	}
	
	
	/**
	 * 跳入产品类型添加页面
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toAdd(Model model,HttpSession session ){
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		model.addAttribute("company", companyBase);
		return "/admin/productType/add";
	}
	
	
	
	
	/**
	 * 判断类型名是否重复
	 */
	@RequestMapping(value="checkProductTypeRepeat")
	@ResponseBody
	public String checkProductTypeRepeat(@RequestParam("typeName") String typeName,HttpServletRequest request) {
		
		/*String backStr="false";
		String id = request.getParameter("id");
		String companyId = request.getParameter("companyId");
		
		Company company = companyService.findById(new BigDecimal(companyId),false);
		
		ProductType productType=new ProductType();
		productType.setId(new BigDecimal(id));
		productType.setTypeName(name);
		
		
		boolean flag =companyService.ifProductTypeRepeated(company,productType,type,company.getWorkMode());
		if(flag){
			backStr="true";	
		}else{
			backStr="false";
		}*/
		return "true";
	}
	
	/**
	 * 添加产品类型
	 * 
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(ProductType productType,HttpSession session){
		
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType sessionProductType = (ProductType) session.getAttribute(Constants.SESSION_PRODUCT_TYPE);
		
		productType.setParentId(sessionProductType.getParentId());
		
		productTypeService.add(productType,companyBase.getId(),companyBase.getWorkMode());
		
		return "redirect:/admin/productType/main";
	}
	
	/**
	 * 进入产品类型修改页面
	 */
	@RequestMapping(value="edit/{typeId}",method=RequestMethod.GET)
	public String toEdit(Model mode,@PathVariable("typeId")BigDecimal typeId,HttpSession session){

		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType productType = productTypeService.findProductTypeByTypeId(
																typeId,companyBase.getId(),companyBase.getWorkMode());
		mode.addAttribute("productType", productType);
		mode.addAttribute("company", companyBase);
		return "/admin/productType/edit";
	}
	
	
	/**
	 * 修改产品类型信息
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(ProductType productType, HttpSession session){
		
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		productTypeService.edit(productType,companyBase.getId(),companyBase.getWorkMode());
		
		return "redirect:/admin/productType/main";
	}
	
	/**
	 * 进入产品类型的子类型
	 */
	@RequestMapping(value="child/{pid}")
	public String childType(Model model,@PathVariable("pid")BigDecimal pid,HttpSession session){
		
		ProductType productType = new ProductType();
		productType.setParentId(pid);
		session.setAttribute(Constants.SESSION_PRODUCT_TYPE, productType);
		
		CompanyBase companyBase=(CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		PageView pageView = new PageView(1);
		pageView = productTypeService.queryProductType(pageView,companyBase.getId(),
												   pid,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", companyBase);
		
		
		String currentType = productTypeService.findProductTypeByTypeId(pid, companyBase.getId(), companyBase.getWorkMode())
												.getTypeName();
		model.addAttribute("currentType", currentType);
		return "/admin/productType/main";
	}
}
