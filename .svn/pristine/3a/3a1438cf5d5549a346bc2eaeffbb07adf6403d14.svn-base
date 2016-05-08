package org.jymf.web.admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.jymf.entity.Area;
import org.jymf.entity.CompanyMonitor;
import org.jymf.entity.MonitorRelation;
import org.jymf.interceptor.Token;
import org.jymf.service.IAreaService;
import org.jymf.service.ICompanyMonitorService;
import org.jymf.service.IMonitorRelationService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 监管管理Controller
 * @author cqs
 * @date   2014年5月9日
 */
@Controller
@RequestMapping(value = "/admin/monitor")
@SessionAttributes("companyMonitor")
public class CompanyMonitorController {
    @Autowired
    private ICompanyMonitorService companyMonitorService;
    
    @Autowired
    private IAreaService areaService;
	
	@Autowired
	private IMonitorRelationService monitorRelationService;
    
    @RequestMapping(value = "main")
    public String init(ModelMap map) {
    	CompanyMonitor companyMonitor = new CompanyMonitor();
    	PageView pageView = new PageView(1);
		pageView = companyMonitorService.query(pageView, companyMonitor);
		map.put("pageView", pageView);
    	map.put("companyMonitor", companyMonitor);
        return "/admin/monitor/main";
    }

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, CompanyMonitor companyMonitor, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = companyMonitorService.query(pageView, companyMonitor);
        model.addAttribute("pageView", pageView);
        model.addAttribute("monitor", companyMonitor);
        
        return "/admin/monitor/main";
    }
    
    /**
     * 进入监管管理添加画面
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    @Token(save=true)
    public String add(Map<String, Object> map,HttpServletRequest request) {
    	map.put("monitorModeMap", Constants.monitorModeMap);
    	List<String> list = areaService.areaNameQuery();
    	request.setAttribute("areaNames", list);
        return "admin/monitor/add";
    }
    
    /**
     * 将监管管理画面添加内容写入数据库
     * @param companyMonitor
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    @Token(remove=true)
    public String add(CompanyMonitor companyMonitor, RedirectAttributes redirectAttributes) {
        try{
            companyMonitorService.add(companyMonitor);
            return "redirect:/admin/monitor/main";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "监管管理添加失败，请确认！");
            return "admin/monitor/add";
        }
    }

    /**
     * 进入监管管理更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, Model model,Map<String, Object> map,HttpServletRequest request) {
    	// 当前状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "正 常"); 
		statusMap.put(1, "挂 起");
		map.put("statusMap", statusMap);
		map.put("monitorModeMap", Constants.monitorModeMap);
    	request.setAttribute("areaNames", areaService.areaNameQuery());
    	CompanyMonitor monitor = companyMonitorService.findById(id.longValue());
    	Area area = areaService.areaQueryById(new BigDecimal(monitor.getAreaCode()),new BigDecimal(-1));
    	if(null != area){
    		request.setAttribute("areaName", area.getName());
    	}else{
    		request.setAttribute("areaName", "");
    	}
        model.addAttribute("monitor",monitor );
        return "admin/monitor/edit";
    }

    /**
     * 更新监管管理信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("monitor") CompanyMonitor companyMonitor,RedirectAttributes redirectAttributes,HttpServletRequest request) {
    	Area area = areaService.areaQueryByName(companyMonitor.getAreaCode());
    	if(null != area){
    		companyMonitor.setAreaCode(area.getId().toString());
    		companyMonitor.setLevel(area.getLevel());
    	}else return "redirect:/admin/monitor/update/"+companyMonitor.getId();
    	companyMonitorService.update(companyMonitor);
    	
    	
    	String [] companyId = request.getParameterValues("companyId");
    	MonitorRelation monitorRelation = new MonitorRelation();
    	try {
    		if(null != companyId){
	    		for(int i=0;i<companyId.length;i++){
	    			//监管部门ID
	    			monitorRelation.setMonitorId(companyMonitor.getId());
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
    
    /**
     * 通过用户名查询信息
     */
    @RequestMapping(value = "checkAccount")
	@ResponseBody
	public String checkAccount(@RequestParam("account") String account) {
		CompanyMonitor companyMonitor = companyMonitorService.selectCompanyMonitor(account);
		if (null == companyMonitor) {
			return "true";
		}else{
			return "false";
		}
	}
}
