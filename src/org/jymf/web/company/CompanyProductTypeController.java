package org.jymf.web.company;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyBase;
import org.jymf.entity.ProductType;
import org.jymf.interceptor.Token;
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
 * @author wfj 2015-4-27
 * 企业用户下产品类型controller
 */
@Controller
@RequestMapping(value="/company/productType")
public class CompanyProductTypeController {
	
	@Autowired
	private IProductTypeService productTypeService;
	
	/**
	 * 进入指定企业的产品类型
	 * @param companyId
	 * @param typepId 类型的父ID 进入初始页面typepId=0
	 * @param model
	 * @return 
	 */
	@RequestMapping(value = "main/{pid}")
	public String productType(Model model,@PathVariable("pid")BigDecimal pid,String pageNow, HttpSession session) {
		
		CompanyBase companyBase=(CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType productType=new ProductType();
		productType.setParentId(pid);
		session.setAttribute(Constants.SESSION_PRODUCT_TYPE,productType);
		
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = productTypeService.queryProductType(pageView,companyBase.getId(),
												   pid,companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", companyBase);
		
		//用于显示产品类型页面的导航
		String currentType = productTypeService.findProductTypeByTypeId(pid, companyBase.getId(), companyBase.getWorkMode())
											   .getTypeName();
		model.addAttribute("currentType", currentType);
		model.addAttribute("pid", pid);
		
		return "/company/productType/main";
	}
	
	
	/**
	 * 跳入产品类型添加页面
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	@Token(save=true)
	public String toAdd(Model model,HttpSession session){
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		model.addAttribute("company", companyBase);
		
		return "/company/productType/add";
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
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@Token(remove=true)
	public String add(ProductType productType,HttpSession session){
		
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType sessionProductType = (ProductType) session.getAttribute(Constants.SESSION_PRODUCT_TYPE);
		
		productType.setParentId(sessionProductType.getParentId());
		
		productTypeService.add(productType,companyBase.getId(),companyBase.getWorkMode());
		
		return String.format("redirect:/company/productType/main/%s",String.valueOf(sessionProductType.getParentId()));
	}
	
	/**
	 * 进入产品类型修改页面
	 */
	@RequestMapping(value="edit/{typeId}",method=RequestMethod.GET)
	public String toEdit(Model model,@PathVariable("typeId")BigDecimal typeId,HttpSession session){

		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType productType = productTypeService.findProductTypeByTypeId(
																typeId,companyBase.getId(),companyBase.getWorkMode());
		model.addAttribute("productType", productType);
		model.addAttribute("company", companyBase);
		return "/company/productType/edit";
	}
	
	
	/**
	 * 修改产品类型信息
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(ProductType productType, HttpSession session){
		
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);
		ProductType sessionProductType = (ProductType) session.getAttribute(Constants.SESSION_PRODUCT_TYPE);
		productTypeService.edit(productType,companyBase.getId(),companyBase.getWorkMode());
		
		return   String.format("redirect:/company/productType/main/%s",String.valueOf(sessionProductType.getParentId()) );
	}
}
