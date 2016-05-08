package org.jymf.web.admin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jymf.entity.Area;
import org.jymf.entity.CompanyMonitor;
import org.jymf.entity.MonitorRelation;
import org.jymf.service.IAreaService;
import org.jymf.service.ICompanyMonitorService;
import org.jymf.service.IMonitorRelationService;
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
 * 监管关系Controller
 * @author lxg
 * @date   2014年7月7日
 */
@Controller
@RequestMapping(value = "/admin/relation")
public class MonitorRelationController {
	@Autowired
	private IMonitorRelationService monitorRelationService;
	@Autowired
	private ICompanyMonitorService companyMonitorService;
    @Autowired
    private IAreaService areaService;  
  	/**
	 * 进入企业显示画面
	 * @return
	 *@param pageNow　当前页
	 */
	@RequestMapping(value="view/{monitorId}/{pagNow}", method = RequestMethod.GET)
	public String view(@PathVariable("monitorId") BigDecimal monitorId,
					   @PathVariable("pagNow") int pagNow,
					   Model model, MonitorRelation monitor) {
		PageView pageView = new PageView(pagNow);
		monitor.setMonitorId(monitorId);
		pageView = monitorRelationService.query(pageView, monitor);
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("monitor", monitor);
		return "admin/monitor/view";
	}
	
	
	 /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, MonitorRelation monitor, String pageNow) {
    	PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = monitorRelationService.query(pageView, monitor);
        model.addAttribute("pageView", pageView);
        model.addAttribute("monitor", monitor);
        return "admin/monitor/view";
    }
    
    /**
     * @param model
     * 更改当前操作状
     * @return
     *  @param pageNow　当前页
     */
    @RequestMapping("updateStatus/{id}/{monitorId}/{pagNow}")
    public String updateStatus(@PathVariable("id") BigDecimal id,
    						   @PathVariable("pagNow") int pagNow,
    		                   @PathVariable("monitorId") BigDecimal monitorId,
    		                   HttpServletRequest request){
    	monitorRelationService.updateStatus(id.longValue());
    	return String.format("redirect:/admin/relation/view/%s/%s",monitorId,pagNow);
    }
    
    
    /**
     * @param model
     * 添加信息,监管信息,监管关系信息
     * @return
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     */
    @RequestMapping("add")
    public String add(HttpServletRequest request,CompanyMonitor companyMonitor) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	 //状态默认为0:正常
    	companyMonitor.setStatus( new BigDecimal(0));
    	Area area = areaService.areaQueryByName(companyMonitor.getAreaCode());
    	if(null != area){
    		companyMonitor.setAreaCode(area.getId().toString());
    		companyMonitor.setLevel(area.getLevel());
    	}else return "redirect:/admin/monitor/add";
    	//MD5密码加密:默认密码为：123456
    	String password = MD5.getInstance().encrypt("123456");
    	companyMonitor.setPwd(password);
    	//添加监管管理信息
		companyMonitorService.add(companyMonitor);
		//查询company_monitor表里最大的iD值,新插入的值得ID
    	int maxId=companyMonitorService.findMaxId();
    	String [] companyId = request.getParameterValues("companyId");
    	MonitorRelation monitorRelation = new MonitorRelation();
    	try {
    		if(null != companyId && 0 != companyId.length){
	    		for(int i=0;i<companyId.length;i++){
	    			//监管部门ID
	    			monitorRelation.setMonitorId(new BigDecimal(maxId));
	    			//被监管企业ID
	    			monitorRelation.setCompanyId( new BigDecimal(companyId[i]));
	    			//开始日期
	    			monitorRelation.setStartDate(new Date());
	    			//结束日期
	    			monitorRelation.setEndDate(new Date());
	    			//当前状态,默认为0:正常
	    			monitorRelation.setStatus(new BigDecimal(0));
	    			//添加监管关系信息
	    			monitorRelationService.add(monitorRelation);
		    	}
		    	return "redirect:/admin/monitor/main";
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/monitor/main";
    	
    }
}
