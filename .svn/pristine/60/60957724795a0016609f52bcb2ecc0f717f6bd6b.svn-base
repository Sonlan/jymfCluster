package org.jymf.web.admin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jymf.entity.AgentMonitor;
import org.jymf.entity.AgentMonitorRelation;
import org.jymf.interceptor.Token;
import org.jymf.service.IAgentMonitorRelationService;
import org.jymf.service.IAgentMonitorService;
import org.jymf.utils.Common;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 代理商关系Controller
 * @author lxg
 * @date   2014年7月16日
 */
@Controller
@RequestMapping(value = "/admin/agentRelation")
public class AgentRelationController {

	@Autowired
	private IAgentMonitorRelationService agentMonitorRelationService;
	
	@Autowired
	private IAgentMonitorService agentMonitorService;
	  
  	/**
  	 * 进入企业显示画面
  	 * @return
  	 */
	@RequestMapping(value="view/{agentId}/{pagNow}", method = RequestMethod.GET)
	public String view(@PathVariable("agentId") BigDecimal agentId,
					   @PathVariable("pagNow") int pagNow,
			           Model model,AgentMonitorRelation relation) {
		PageView pageView = new PageView(pagNow);
		relation.setAgentId(agentId);
		pageView = agentMonitorRelationService.query(pageView, relation);
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("relation", relation);
		return "admin/agent/view";
	}
	
	 /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, AgentMonitorRelation relation, String pageNow) {
    	PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = agentMonitorRelationService.query(pageView, relation);
        model.addAttribute("pageView", pageView);
        model.addAttribute("relation", relation);
        return "admin/agent/view";
    }
    
    /**
     * @param model
     * 更改当前操作状
     * @return
     */
    @RequestMapping("updateStatus/{id}/{agentId}/{pagNow}")
    public String updateStatus(@PathVariable("id") BigDecimal id,
    						   @PathVariable("pagNow") int pagNow,
    		                   @PathVariable("agentId") BigDecimal agentId,
    		                   HttpServletRequest request){
    	agentMonitorRelationService.updateStatus(id.longValue());
    	return String.format("redirect:/admin/agentRelation/view/%s/%s",agentId,pagNow);
    }
    
    
    /**
     * @param model
     * 添加信息,代理商信息,代理商关系信息
     * @return
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     */
    @RequestMapping("add")
    @Token(remove=true)
    public String add(HttpServletRequest request,AgentMonitor agentMonitor) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	 //状态默认为0:正常
    	agentMonitor.setStatus( new BigDecimal(0));
    	
    	//MD5密码加密:默认密码为：123456
    	String password = MD5.getInstance().encrypt("123456");
    	agentMonitor.setPwd(password);
    	//添加监管管理信息
    	agentMonitorService.add(agentMonitor);
    	//查询company_monitor表里最大的iD值,新插入的值得ID
    	int maxId=agentMonitorService.findMaxId();
    	String [] companyId = request.getParameterValues("companyId");
    	AgentMonitorRelation agentMonitorRelation = new AgentMonitorRelation();
    	try {
    		if(0 != companyId.length || null != companyId){
	    		for(int i=0;i<companyId.length;i++){
	    			//监管部门ID
	    			agentMonitorRelation.setAgentId(new BigDecimal(maxId));
	    			//被监管企业ID
	    			agentMonitorRelation.setCompanyId( new BigDecimal(companyId[i]));
	    			//开始日期
	    			agentMonitorRelation.setStartDate(new Date());
	    			//结束日期
	    			agentMonitorRelation.setEndDate(new Date());
	    			//当前状态,默认为0:正常
	    			agentMonitorRelation.setStatus(new BigDecimal(0));
	    			//添加监管关系信息
	    			agentMonitorRelationService.add(agentMonitorRelation);
		    	}
		    	return "redirect:/admin/agent/main";
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/agent/main";
    }
}
