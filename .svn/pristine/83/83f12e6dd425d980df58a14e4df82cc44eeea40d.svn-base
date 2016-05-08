package org.jymf.web.admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.AgentLabelIndex;
import org.jymf.entity.AgentMonitor;
import org.jymf.entity.AgentMonitorRelation;
import org.jymf.service.IAgentLabelIndexService;
import org.jymf.interceptor.Token;
import org.jymf.service.IAgentMonitorRelationService;
import org.jymf.service.IAgentMonitorService;
import org.jymf.service.ICompanyService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.MeassageConfig;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jymf.common.LableUtil;


/**
 *代理商管理Controller
 * @author lxg
 * @date   2014年7月14日
 */
@Controller
@RequestMapping(value = "/admin/agent")
@SessionAttributes("agentMonitor")
public class AgentMonitorController {
	@Autowired
	private IAgentMonitorService agentMonitorService;
	    
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IAgentMonitorRelationService agentMonitorRelationService;
	
	@Autowired
	private IAgentLabelIndexService agentLabelIndexService;
	@Autowired
	private BusinessLogger businessLogger;
	    
	@RequestMapping(value = "main")
	public String init(ModelMap map) {
		AgentMonitor agentMonitor = new AgentMonitor();
		PageView pageView = new PageView(1);
		pageView = agentMonitorService.query(pageView, agentMonitor);
		map.put("pageView", pageView);
		map.put("agentMonitor", agentMonitor);
	    return "/admin/agent/main";
	}
	
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, AgentMonitor agentMonitor, String pageNow) {
	    PageView pageView = null;
	    if (Common.isEmpty(pageNow)) {
	        pageView = new PageView(1);
	    } else {
	        pageView = new PageView(Integer.parseInt(pageNow));
	    }
	    pageView = agentMonitorService.query(pageView, agentMonitor);
	    model.addAttribute("pageView", pageView);
	    model.addAttribute("agent", agentMonitor);
	    
	    return "/admin/agent/main";
	}
	
	/**
	 * 进入监管管理添加画面
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.GET)
	@Token(save=true)
	public String add(HttpServletRequest request) {
	    return "admin/agent/add";
	}
	
	/**
	 * 将监管管理画面添加内容写入数据库
	 * @param companyMonitor
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add(AgentMonitor agentMonitor, RedirectAttributes redirectAttributes) {
	    try{
	    	agentMonitorService.add(agentMonitor);
	        return "redirect:/admin/agent/main";
	    }catch(Exception e){
	        redirectAttributes.addFlashAttribute("message", "监管管理添加失败，请确认！");
	        return "admin/agent/add";
	    }
	}
	
	/**
	 * 进入代理商管理更新画面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") BigDecimal id, Model model,Map<String, Object> map) {
	
		// 当前状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "正 常"); 
		statusMap.put(1, "挂 起");
		map.put("statusMap", statusMap);
		
	    model.addAttribute("agent", agentMonitorService.findById(id.longValue()));
	    return "admin/agent/edit";
	}
	
	/**
	 * 更新代理商管理信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("agentMonitor") AgentMonitor agentMonitor,HttpServletRequest request) {
		agentMonitorService.update(agentMonitor);
		//获取前台动态添加的列
		String [] companyId = request.getParameterValues("companyId");
		AgentMonitorRelation monitorRelation = new AgentMonitorRelation();
		try {
			if(null !=companyId){
	    		for(int i=0;i<companyId.length;i++){
	    			//代理商ID
	    			monitorRelation.setAgentId(agentMonitor.getId());
	    			//被监管企业ID
	    			monitorRelation.setCompanyId( new BigDecimal(companyId[i]));
	    			//开始日期
	    			monitorRelation.setStartDate(new Date());
	    			//结束日期
	    			monitorRelation.setEndDate(new Date());
	    			//当前状态,默认为0:正常
	    			monitorRelation.setStatus(new BigDecimal(0));
	    			//添加监管关系信息
	    			agentMonitorRelationService.add(monitorRelation);
		    	}
		    	return "redirect:/admin/agent/main";
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/agent/main";
		
	}
	
	/*
	 * 通过用户名查询信息
	 * */
	@RequestMapping(value = "checkAccount")
	@ResponseBody
	public String checkAccount(@RequestParam("account") String account) {
		AgentMonitor agentMonitor = agentMonitorService.selectAgentMonitor(account);
		if (null == agentMonitor) {
			return "true";
		}else{
			return "false";
		}
	}
	
	//TODO================================================================
     
    /**
     * 查看代理商标签卷的分配情况
     */
    @RequestMapping("labelindex/{id}")
    public String query(Model model, @PathVariable("id") String id, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = agentLabelIndexService.query(pageView, id);
        model.addAttribute("pageView", pageView);

        return "/admin/agent/labelIndex/main";
    }
    
    /**
     * 进入标签索引添加画面
     * @return
     */
    @RequestMapping(value="labelindex/add", method = RequestMethod.GET)
    public String add() {
        return "admin/agent/labelIndex/add";
    }

    /**
     * 将标签索引画面添加内容写入数据库
     * @param labelIndex
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="labelindex/add", method = RequestMethod.POST)
    public String add(Model model,AgentLabelIndex labelIndex, HttpSession session) {
    	// 起始追溯凭证 ？> 结束追溯凭证
    	// 起始标签的位数要相等
    	if(labelIndex.getStartTid().compareTo(labelIndex.getEndTid())>0||
    			labelIndex.getStartTid().length() != labelIndex.getEndTid().length()){
            model.addAttribute("message", MeassageConfig.getMessage("labelIndexCheck"));
            model.addAttribute("labelIndex", labelIndex);
            return "/admin/agent/labelIndex/add";
    	}
    	
    	// 开始追溯凭证是否被注册过
    	if(agentLabelIndexService.checkLableId(labelIndex.getStartTid(),0)){
    		 model.addAttribute("message", MeassageConfig.getMessage("startTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/agent/labelIndex/add";
    	}
    	// 结束追溯凭证是否被注册过
    	if(agentLabelIndexService.checkLableId(labelIndex.getEndTid(),0)){
    		 model.addAttribute("message", MeassageConfig.getMessage("endTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/agent/labelIndex/add";
    	}
    	
    	// 标签状态默认为1，有效
    	labelIndex.setStatus(new BigDecimal(1));
        agentLabelIndexService.add(labelIndex);
		String logStr = String.format("StartId:%s,EndId:%s", labelIndex.getStartTid(), labelIndex.getEndTid());
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("agent_label_index_add", user, new BigDecimal(labelIndex.getAgentId()), logStr, labelIndex);
        return "redirect:/admin/agent/labelIndex/main";
    }

    /**
     * 进入标签索引更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "labelindex/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, Map<String, Object> map, HttpSession session) {
    	AgentLabelIndex labelIndex = agentLabelIndexService.findById(id.longValue());
		// 当前状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "无 效"); 
		statusMap.put(1, "有 效");
		map.put("statusMap", statusMap);
		map.put("labelIndex", labelIndex);
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
        businessLogger.log("agent_label_index_update_before", user, labelIndex);

		return "admin/labelIndex/edit";
    }

    /**
     * 更新标签索引信息
     */
    @RequestMapping(value = "labelindex/update", method = RequestMethod.POST)
    public String update(Model model,@ModelAttribute("labelIndex") AgentLabelIndex labelIndex, HttpSession session) {
    	// 起始追溯凭证 ？> 结束追溯凭证
    	if(labelIndex.getStartTid().compareTo(labelIndex.getEndTid())>0 ||
    			labelIndex.getStartTid().length() != labelIndex.getEndTid().length()){
            model.addAttribute("message", MeassageConfig.getMessage("labelIndexCheck"));
            model.addAttribute("labelIndex", labelIndex);
            return "/admin/agent/labelIndex/add";
    	}
    	
    	// 开始追溯凭证是否被注册过
    	if(agentLabelIndexService.checkLableId(labelIndex.getStartTid(),labelIndex.getId().longValue())){
    		 model.addAttribute("message", MeassageConfig.getMessage("startTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/agent/labelIndex/add";
    	}
    	// 结束追溯凭证是否被注册过
    	if(agentLabelIndexService.checkLableId(labelIndex.getEndTid(),labelIndex.getId().longValue())){
    		 model.addAttribute("message", MeassageConfig.getMessage("endTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/agent/labelIndex/add";
    	}
    	
    	String logStr = String.format("StartId:%s,EndId:%s", labelIndex.getStartTid(), labelIndex.getEndTid());
    	
        agentLabelIndexService.update(labelIndex);
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
        businessLogger.log("label_index_update", user, new BigDecimal(labelIndex.getAgentId()), logStr, labelIndex);
        return "redirect:/admin/labelIndex/main";
    }
    
	/**
	 * @param id 16位追溯码,验证
	 * @return
	 */
	@RequestMapping(value="startChk")
	@ResponseBody
	public String startCheck(@RequestParam("startTid") String id) {
		// 追溯码的验证
		if(LableUtil.checkLabel16(id).equals("0")){
			return "true";
		}else{
			return "false";
		}
	}
	
	@RequestMapping(value="endChk")
	@ResponseBody
	public String endCheck(@RequestParam("endTid") String id) {
		// 追溯码的验证
		if(LableUtil.checkLabel16(id).equals("0")){
			return "true";
		}else{
			return "false";
		}
	}
}
