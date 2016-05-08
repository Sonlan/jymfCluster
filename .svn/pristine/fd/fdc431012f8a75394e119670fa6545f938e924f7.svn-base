package org.jymf.web.admin;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.AgentMonitor;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.interceptor.Token;
import org.jymf.service.IAgentMonitorService;
import org.jymf.service.ICompanyService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 企业用户Controller
 * @author cqs
 * @date   2014年5月7日
 */
@Controller
@RequestMapping(value = "/admin/company")
public class CompanyController {
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IAgentMonitorService agentMonitorService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	@RequestMapping(value = "main")
	public String init(Model model, Company company) {
		
		PageView pageView = new PageView(1);
		pageView = companyService.query(pageView, company);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap",Constants.workModelMap);
		
		List<AgentMonitor> agents = agentMonitorService.findAll();
		model.addAttribute("agents",agents);
		
		return "/admin/company/main";
	}

	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, Company company, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = companyService.query(pageView, company);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap", Constants.workModelMap);
		
		List<AgentMonitor> agents = agentMonitorService.findAll();
		model.addAttribute("agents",agents);
		
		return "/admin/company/main";
	}
	
	/**
	 * 进入企业添加画面
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.GET)
	@Token(save=true)
	public String add(Map<String, Object> map) {
		Company company = new Company();
		 
		map.put("workModelMap", Constants.workModelMap);
		map.put("company", company); 	
		return "admin/company/add";
	}
	
	/**
	 * 进入企业显示画面
	 * @return
	 */
	@RequestMapping(value="view", method = RequestMethod.GET)
	public String view() {
		return "admin/company/view";
	}

	/**
	 * 将企业画面添加内容写入数据库
	 * @param company
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.POST)
	@Token(remove=true)
	public String add(Company company, HttpSession session, HttpServletRequest request) {
		//获取当前项目根路径
		String webUrl=session.getServletContext().getRealPath(File.separator);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFile4 = multipartRequest.getFiles("imgFile4");
		
		companyService.add(company,imgFile4,webUrl+Constants.CACHE_FOLDER);
		companyService.createTable(company,company.getWorkMode());
		
		String logStr = String.format("Name:%s", company.getName());
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("company_add", user, company.getId(), logStr,company);
		return "redirect:/admin/company/main";
	}

	/**
	 * 进入企业更新画面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") BigDecimal id, Map<String, Object> map, HttpSession session) {
		Company company = companyService.findById(id,true);
		
		// 当前状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "正 常"); 
		statusMap.put(1, "挂 起");
		map.put("statusMap", statusMap);
		map.put("company", company); 	
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("company_update_before", user, company);
		
		return "admin/company/edit";
	}

	/**
	 * 更新企业信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("company") Company company,HttpServletRequest request, HttpSession session) {

		//获取当前项目图片缓存地址
		String webUrl=session.getServletContext().getRealPath(File.separator);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFile4 = multipartRequest.getFiles("imgFile4");
		
		
		if(companyService.update(company,imgFile4,webUrl+Constants.CACHE_FOLDER)){
			String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
			String logStr = String.format("Name:%s",company.getName());
			businessLogger.log("company_update", user,company.getId(), logStr, company);
		}
		
		return "redirect:/admin/company/main";
	}
	
	/**
	 * 企业单选画面
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
    @RequestMapping(value = "querySelect")
	public String querySelect(Model model, Company company, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		company.setFlag(new BigDecimal(2));
		company.setStatus(new BigDecimal(0));
		
		pageView = companyService.query(pageView, company);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		
		return "redirect:/admin/dialog/selectCompany";
	} 
    
	/**
	 * 进入企业统计画面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "count/{id}/{workMode}", method = RequestMethod.GET)
	public String count(@PathVariable("id") BigDecimal id, 
			            @PathVariable("workMode") BigDecimal workMode,
			            HttpSession session) {
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(id);
		companyBase.setWorkMode(workMode);
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		
		return "redirect:/admin/product/count/main";
	}
	
    
	/**
	 * 进入指定企业的产品维护画面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "product/{id}/{workMode}", method = RequestMethod.GET)
	public String product(@PathVariable("id") BigDecimal id, 
			              @PathVariable("workMode") BigDecimal workMode,
			              HttpSession session) {
		Company company = companyService.findById(id,false);
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(id);
		companyBase.setWorkMode(workMode);
		companyBase.setName(company.getName());
		companyBase.setUrl(company.getUrl());
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		
		return "redirect:/admin/product/main";
	}
}
