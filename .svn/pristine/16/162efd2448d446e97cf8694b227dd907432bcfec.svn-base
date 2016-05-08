package org.jymf.web.agent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.AgentMonitor;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyBase;
import org.jymf.entity.CompanyCount;
import org.jymf.service.IAgentMonitorService;
import org.jymf.service.ICompanyService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *代理商管理Controller
 * @author lxg
 * @date   2014年7月14日
 */
@Controller
@RequestMapping(value = "/agent")
public class AgentController {

	@Autowired
    private IAgentMonitorService agentMonitorService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	/**
     * 进入密码修改页面
     */
    @RequestMapping(value= "editpwd")
    public String editPassWordMain(){
    	return "/agent/editpwd"; 
    }
	
    /**
     * 验证当前输入的密码是否正确
     */
    @RequestMapping(value="checkOldPwd")
	@ResponseBody
	public String checkOldPwd(HttpServletRequest request,@RequestParam("oldPwd") String oldPwd) {
    	AgentMonitor agent=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
    	// 验证当前登陆用户的密码
		try {
			if(agent.getPwd().equals(MD5.getInstance().encrypt(oldPwd))){
				return "true";
			}else{
				return "false";
			}
		} catch (Exception e) {
			return "false";
		} 
	}
    
    /**
     * 修改代理商用户的密码,修改成功将重新返回登陆画面,重新登陆
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(HttpServletRequest request){
		//获取登陆时的用户信息
    	AgentMonitor aMonitor=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
    	//获取前台新输入的密码
    	aMonitor.setPwd(request.getParameter("newPwd"));
    	agentMonitorService.updateByPrimaryKey(aMonitor);
    	request.getSession().removeAttribute(Constants.SESSION_AGENT);
    	businessLogger.log(String.format("代理商_%s_密码修改成功！", aMonitor.getAccount()));
		return "redirect:/agent/login";
	}
    

	/**
	 * 根据代理商ID ,查找企业信息
	 * @param model
	 * @param company
	 * @param re
	 * @param pageNow
	 * @return
	 */
	@RequestMapping(value = "main")
	public String main(Model model, Company company,HttpServletRequest re,String pageNow) {
		AgentMonitor agent=(AgentMonitor) re.getSession().getAttribute(Constants.SESSION_AGENT);
		PageView pageView = null;
  		if (Common.isEmpty(pageNow)) {
  			pageView = new PageView(1);
  		} else {
  			pageView = new PageView(Integer.parseInt(pageNow));
  		}
  		company.setFlag(new BigDecimal(2));
  		company.setStatus(new BigDecimal(0));
  		
  		if(null==company.getStartDate()){
			company.setStartDate(Common.yesterdayModthFirstday());
			company.setEndDate(Common.yesterday());
		}
  		
  		pageView = companyService.queryAgentCompany(pageView, company,agent.getId().longValue());
  		
  		@SuppressWarnings("unchecked")
		List<Company> list = (List<Company>)pageView.getRecords();
	    for(Company com : list){
			CompanyCount companyCount=new CompanyCount();

			companyCount.setStartDate(company.getStartDate());
			companyCount.setEndDate(company.getEndDate());
			
	    	companyCount.setCompanyId(com.getId());
			companyCount = companyService.labelCountByCompany(companyCount,  com.getWorkMode());
			if(null!=companyCount){
				com.setActiveCnt(companyCount.getActiveCnt());
				com.setPackageCnt(companyCount.getPackageCnt());
			}
	    }
	    
  		model.addAttribute("pageView", pageView);
  		model.addAttribute("company", company);
  		model.addAttribute("workModelMap",Constants.workModelMap);
  		
		return "/agent/main";
	}
	
	
    /**
     * 进入代理商登陆
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest re,AgentMonitor agentMonitor,RedirectAttributes redirectAttributes){
    	// 根据用户名查找代理商信息
    	AgentMonitor agent = agentMonitorService.findAgent(agentMonitor);
    	
		if(null == agent){
			redirectAttributes.addFlashAttribute("message", "提示:用户名或密码错误!");
			return "redirect:/agent/login";
		}else if(agent.getStatus().compareTo(new BigDecimal(1))==0){
			redirectAttributes.addFlashAttribute("message", "提示:账号已停用!");
			return "redirect:/agent/login";
		}else{
			Common.initSession(re.getSession());
			// 保存当前用户
			re.getSession().setAttribute(Constants.SESSION_AGENT, agent);
			businessLogger.log(String.format("代理商_%s_%s 登录成功！", agent.getId(), agent.getName()));
			return "redirect:/agent/main";
		}
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
		CompanyBase companyBase = new CompanyBase();
		companyBase.setId(id);
		companyBase.setWorkMode(workMode);
		session.setAttribute(Constants.SESSION_COMPANY_BASE, companyBase);
		
		return "redirect:/agent/product/main";
	}
	
	/**
	 * 进入企业详细画面
	 * @return
	 */
	@RequestMapping(value = "company/view/{id}", method = RequestMethod.GET)
	public String init(@PathVariable("id") BigDecimal id, Map<String, Object> map) {
		Company company = companyService.findById(id,true);
		map.put("workModelMap",Constants.workModelMap);
				
		map.put("company", company); 
     	return "/agent/view";
	}
}
