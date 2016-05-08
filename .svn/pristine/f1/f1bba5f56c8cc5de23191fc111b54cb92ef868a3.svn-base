package org.jymf.web.company;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.entity.CompanyUser;
import org.jymf.menu.MenuCompany;
import org.jymf.service.ICompanyService;
import org.jymf.service.ICompanyUserService;
import org.jymf.service.IEquipmentService;
import org.jymf.service.IInspectDeviceService;
import org.jymf.service.IProductService;
import org.jymf.service.IProductTypeService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.MD5;
import org.jymf.utils.MeassageConfig;
import org.jymf.utils.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 企业用户基本信息管理 
 * @author cqs
 * @date   2014年5月26日
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyManagerController{
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyUserService companyUserService;
		
	@Autowired
	private BusinessLogger businessLogger;
	
    @Autowired
    private IEquipmentService equipmentService;
    
    @Autowired
    private IInspectDeviceService inspectDeviceService;
    
    @Resource
	private SysConfig sysConfig;
	
    @Autowired
	private IProductTypeService productTypeService;
    
    @Autowired
    private IProductService productService;
    
	/**
	 * 进入登录页面
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(CompanyUser companyUser, 
			            HttpSession session, 
			            RedirectAttributes redirectAttributes) {
		/* 企业登录
		 * 1、通过ID&密码判断这个用户是否存在
		 * 2、存在的用户是否启用 status？=0
		 * 3、这个用户所在的公司是否在审核有效期内
		 * 4、这个用户所在的公司的状态是否正常status？=0
		 */
		companyUser = companyUserService.findUser(companyUser);
		
		if(null==companyUser){
			redirectAttributes.addFlashAttribute("message", 
					                             MeassageConfig.getMessage("companyUserNotExist"));
			return "redirect:/company/login";		
		}
		// ToDo
		// companyUser.setAuthority(new BigDecimal(0));
		
		if(0 != companyUser.getStatus().intValue()){
			redirectAttributes.addFlashAttribute("message", 
                                                 MeassageConfig.getMessage("companyUserDisable"));
            return "redirect:/company/login";
		}
		
		Company company=companyService.getAuditble(companyUser.getCompanyId().longValue());
		if(null == company){
			redirectAttributes.addFlashAttribute("message", 
                                                 MeassageConfig.getMessage("companyAuditValid"));
            return "redirect:/company/login";	
		}
		
		if(0 != company.getStatus().intValue()){
			redirectAttributes.addFlashAttribute("message", 
                                                 MeassageConfig.getMessage("companyValid"));
            return "redirect:/company/login";	
		}
		
		businessLogger.log(String.format("%s_%s 登录成功！", companyUser.getId(), companyUser.getCompanyId()));
		
		boolean hasEquipment= equipmentService.isExistInCompany(companyUser.getCompanyId().longValue());
		boolean hasInspect= inspectDeviceService.isExistInCompany(companyUser.getCompanyId(),companyUser.getWorkMode());
		companyUser.setMenus(MenuCompany.loadMenu(companyUser.getCompanyId(),companyUser.getWorkMode(),hasEquipment,hasInspect).getMenus());
		
		Common.initSession(session);
		
		session.setAttribute(Constants.SESSION_COMPANY_USER, companyUser);
		
		//update by wfj 2015.4.28 用于产品类型的操作
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(companyUser.getCompanyId());
		companyBase.setName(companyUser.getCompanyName());
		companyBase.setWorkMode(companyUser.getWorkMode());
		companyBase.setUrl(companyUser.getCompanyUrl());
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		
		return "redirect:/company/main";
	}
	
	/**
	 * 进入企业信息首页面
	 * @return
	 */
	@RequestMapping(value = "main")
	public String main() {
		return "/company/main";
	}
	
	/**
	 * 进入企业更新画面
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String init(HttpSession session,Map<String, Object> map) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		Company company = companyService.findById(companyUser.getCompanyId(),true);
		
		map.put("workModelMap", Constants.workModelMap);
		map.put("company", company); 
		
		businessLogger.log("company_update_before", companyUser.getId(), company);
		
		if(companyUser.getAuthority().equals(new BigDecimal(1))){
			return "/company/edit";
		}else{
			return "/company/view";
		}
	}
	
	/**
	 * 更新企业信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Company company,HttpServletRequest request, HttpSession session) {
		//获取当前项目图片缓存地址 
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFile4 = multipartRequest.getFiles("imgFile4");
		
		if(companyService.update(company,imgFile4,webUrl+Constants.CACHE_FOLDER)){
			CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
			String logStr = String.format("Name:%s",company.getName());
			businessLogger.log("company_update", companyUser.getId(), company.getId(), logStr, company);
		}
		return "/company/main";
	}
	
	/**
	 * 进入企业用户修改密码画面
	 * @return
	 */
	@RequestMapping(value = "updpwd")
	public String updpwd() {
		return "/company/updpwd";
	}
	
	/**
	 * 验证当前用户的密码是否正确
	 * @param session
	 * @param oldPwd
	 * @return
	 */
	@RequestMapping(value="checkOldPwd")
	@ResponseBody
	public String checkOldPwd(HttpSession session,@RequestParam("oldPwd") String oldPwd) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		// 追溯码的验证
		try {
			if(companyUser.getPassword().equals(MD5.getInstance().encrypt(oldPwd))){
				return "true";
			}else{
				return "false";
			}
		} catch (Exception e) {
			return "false";
		} 
	}
	
	/**
	 * 修改当前用户的密码
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(HttpSession session,HttpServletRequest request){
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		String newPwd = request.getParameter("newPwd");
		companyUserService.update(companyUser, newPwd);
		session.removeAttribute(Constants.SESSION_COMPANY_USER);
		return "redirect:/company/login";
	}
}
