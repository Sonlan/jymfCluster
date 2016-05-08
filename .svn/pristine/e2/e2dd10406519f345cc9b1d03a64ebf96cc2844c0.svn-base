package org.jymf.web.monitor;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jymf.entity.CarInfo;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyCount;
import org.jymf.entity.CompanyMonitor;
import org.jymf.entity.Document;
import org.jymf.service.DatabaseSynchronize;
import org.jymf.service.ICarInfoService;
import org.jymf.service.ICompanyMonitorService;
import org.jymf.service.ICompanyService;
import org.jymf.service.IDocumentService;
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
 * 监管管理Controller
 * @author lxg
 * @date   2014年7月25日
 */
@Controller
@RequestMapping(value = "/monitor")
public class MonitorLoginController {
	
	@Autowired
    private ICompanyMonitorService companyMonitorService;

    @Autowired
	private ICompanyService companyService;
    
    @Autowired
   	private ICarInfoService carInfoService;
    
    @Autowired
    private IDocumentService documentService;
    
	@Autowired
	private BusinessLogger businessLogger;
	
	@Autowired
	private DatabaseSynchronize dbSynchronizeService;
    /**
     * 进入密码修改页面
     */
    @RequestMapping(value= "editPassWord")
    public String editPassWordMain(){
    	return "/monitor/editPassWord"; 
    }
    
    
    /**
     * 验证当前输入的密码是否正确
     */
    @RequestMapping(value="checkOldPwd")
	@ResponseBody
	public String checkOldPwd(HttpServletRequest request,@RequestParam("oldPwd") String oldPwd) {
    	String pwd=(String) request.getSession().getAttribute("pwd");
    	// 验证当前登陆用户的密码
		try {
			if(pwd.equals(MD5.getInstance().encrypt(oldPwd))){
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
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		// 获取登陆时的用户信息
    	CompanyMonitor aMonitor=(CompanyMonitor) request.getSession().getAttribute("companyMonitors");
    	//获取前台新输入的密码
    	String newPwd = MD5.getInstance().encrypt(request.getParameter("newPwd"));
    	aMonitor.setPwd(newPwd);
    	companyMonitorService.updateByPrimaryKey(aMonitor);
    	businessLogger.log(String.format("监管账户_%s_修改密码成功！", aMonitor.getAccount()));
		return "redirect:/monitor/logins";
	}
    
	/**
	 * 根据监管ID ,查找企业信息
	 */
	@RequestMapping(value ="main")
	public String main(Model model, Company company,HttpServletRequest re,String pageNow,HttpServletResponse response) {
		String index = company.getTname();
		PageView pageView = null;
  		if (Common.isEmpty(pageNow)) {
  			pageView = new PageView(1);
  		} else {
  			pageView = new PageView(Integer.parseInt(pageNow));
  		}
		if(null == index || "0".equals(index)){
			CompanyMonitor companyMonitor = (CompanyMonitor) re.getSession().getAttribute("companyMonitors");
			if(2<companyMonitor.getLevel())
				return "redirect:/monitor/campanyList/"+companyMonitor.getId();
	  		pageView = companyMonitorService.monitorquery(pageView, companyMonitor);
	  		model.addAttribute("pageView", pageView);
	  		model.addAttribute("companyMonitor", companyMonitor);
	  		re.setAttribute("index", "0"); //用于区分当前显示monitor
		}else if("1".equals(index)){
			BigDecimal monitorId=(BigDecimal) re.getSession().getAttribute("monitorId");
	  		company.setFlag(new BigDecimal(2));
	  		company.setStatus(new BigDecimal(0));
	  		
			if(null==company.getStartDate()){
				company.setStartDate(Common.yesterdayModthFirstday());
				company.setEndDate(Common.yesterday());
			}
	  		pageView = companyService.queryMonitorCompany(pageView, company,monitorId.longValue());
	  		
	  		@SuppressWarnings("unchecked")
			List<Company> list = (List<Company>)pageView.getRecords();
		    for(Company com : list){
				CompanyCount companyCount=new CompanyCount();

				companyCount.setStartDate(company.getStartDate());
				companyCount.setEndDate(company.getEndDate());
				
		    	companyCount.setCompanyId(com.getId());
				companyCount = companyService.labelCountByCompany(companyCount, com.getWorkMode());
				if(null!=companyCount){
					com.setActiveCnt(companyCount.getActiveCnt());
					com.setPackageCnt(companyCount.getPackageCnt());
				}
				if(com.getRateCnt()!=0){
					double rate = (com.getRate1()+com.getRate2())/(2*com.getRateCnt());
					com.setRate(rate);
				}
		    }
	  		re.setAttribute("index", "1");//用于区分显示company
	  		model.addAttribute("pageView", pageView);
	  		model.addAttribute("company", company);
		}else if("2".equals(index)){
			CompanyMonitor companyMonitor = (CompanyMonitor) re.getSession().getAttribute("currentMonitor");
	  		pageView = companyMonitorService.monitorquery(pageView, companyMonitor);
	  		model.addAttribute("pageView", pageView);
	  		model.addAttribute("companyMonitor", companyMonitor);
	  		re.setAttribute("index", "2"); //用于区分当前显示monitor
	  		HttpSession session = re.getSession();
			session.setMaxInactiveInterval(3600);
			session.setAttribute("currentMonitor", companyMonitor);
		}else if("3".equals(index)){
			String id = (String) re.getSession().getAttribute("companyList_id");
			pageView = companyMonitorService.companyListquery(pageView, new BigDecimal(id));
	  		model.addAttribute("pageView", pageView);
	  		re.setAttribute("index", "3"); //用于区分当前显示company
	  		HttpSession session = re.getSession();
			session.setMaxInactiveInterval(3600);
			session.setAttribute("companyList_id", id);
		}
		return "/monitor/main";
		
			

	}
	/*
	 * 查找监控单位,下一级
	 */
	@RequestMapping(value = "moreMonitor/{areaCode}/{level}")
	public String moreMonitor(Model model,String pageNow,@PathVariable("areaCode")String areaCode,@PathVariable("level")String level,HttpServletRequest re) {
		CompanyMonitor companyMonitor = new CompanyMonitor();
		companyMonitor.setAreaCode(areaCode);
		companyMonitor.setLevel(Integer.parseInt(level));
		PageView pageView = null;
  		if (Common.isEmpty(pageNow)) {
  			pageView = new PageView(1);
  		} else {
  			pageView = new PageView(Integer.parseInt(pageNow));
  		}
  		pageView = companyMonitorService.monitorquery(pageView, companyMonitor);
  		model.addAttribute("pageView", pageView);
  		model.addAttribute("companyMonitor", companyMonitor);
  		re.setAttribute("index", "2"); //用于区分当前显示monitor
  		HttpSession session = re.getSession();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("currentMonitor", companyMonitor);
		return "/monitor/main";
	}
	/**
	 * 查找该监管单位下的公司列表
	 */
	@RequestMapping(value = "campanyList/{id}")
	public String campanyList(Model model,String pageNow,@PathVariable("id")String id,HttpServletRequest re) {
		PageView pageView = null;
  		if (Common.isEmpty(pageNow)) {
  			pageView = new PageView(1);
  		} else {
  			pageView = new PageView(Integer.parseInt(pageNow));
  		}
  		pageView = companyMonitorService.companyListquery(pageView, new BigDecimal(id));
  		model.addAttribute("pageView", pageView);
  		re.setAttribute("index", "3"); //用于区分当前显示company
  		HttpSession session = re.getSession();
		session.setMaxInactiveInterval(3600);
		session.setAttribute("companyList_id", id);
		return "/monitor/main";
	}
	    
	    
    /**
     * 进入监管部门登陆
     * @param id
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     */
    @RequestMapping(value = "logins", method = RequestMethod.POST)
    public String logins(HttpServletRequest re,HttpServletResponse response,CompanyMonitor companyMonitor,RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	//dbSynchronizeService.dbSynchronize();
    	// password：把前台得到的密码,进行加密
    	String password =  MD5.getInstance().encrypt(companyMonitor.getPwd());
    	// 根据用户名查找监管部门信息
    	CompanyMonitor companyMonitors = companyMonitorService.selectCompanyMonitor(companyMonitor.getAccount());
    	
		if(null != companyMonitors && password.equals(companyMonitors.getPwd())){
			HttpSession session = re.getSession();
			Common.initSession(session);
			session.setMaxInactiveInterval(3600);
			session.setAttribute("monitorId", companyMonitors.getId());
			
			// 登陆成功保存当前密码,在修改密码时将用到
			session.setAttribute("pwd", password);
			// 保存当前用户
			session.setAttribute("companyMonitors", companyMonitors);
			
			businessLogger.log(String.format("监管账户_%s_登录成功！", companyMonitors.getAccount()));
			
			return "redirect:/monitor/main";
		}else{
			redirectAttributes.addFlashAttribute("message", "提示:用户名或密码错误!");
			return "redirect:/monitor/logins";
		}
	}
    
    /**
     * 进入密码修改页面
     */
    @RequestMapping(value= "carinfo")
    public String carInfo(Model model,CarInfo carinfo,HttpServletRequest re,String pageNow){
    	PageView pageView = null;
    	if (Common.isEmpty(pageNow)) {
  			pageView = new PageView(1);
  		} else {
  			pageView = new PageView(Integer.parseInt(pageNow));
  		}

    	if(!Common.isEmpty(carinfo.getCarno()) || !Common.isEmpty(carinfo.getEngineno()) || !Common.isEmpty(carinfo.getVin()) ){
    	    Long monitorId=new Long(re.getSession().getAttribute("monitorId").toString());
    	    carinfo.setMonitorId(monitorId);
    	    pageView = carInfoService.queryCarInfo(pageView, carinfo);
    	}
    	model.addAttribute("pageView", pageView);
  		model.addAttribute("carInfo", carinfo);
    	return "/monitor/car/carinfo"; 
    }
    
    /**
     * 进入合同详细
     */
    @RequestMapping(value= "carInfo/document/{id}")
    public String documentView(@PathVariable BigDecimal id, Model model){
    	CarInfo carInfo = carInfoService.getById(id);
    	
        Document document=new Document();		
		document.setCompanyId(new BigDecimal(carInfo.getCompanyId()));
		BigDecimal lId=new BigDecimal(carInfo.getLabelId());
		document.setId(lId);
		
		document = documentService.selectDetail(document, Constants.WM_QP);
			
		model.addAttribute("document", document);
		model.addAttribute("carInfo", carInfo);
		
    	return "monitor/car/document/view"; 
    }
}
