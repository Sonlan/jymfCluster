package org.jymf.web.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jymf.entity.AgentMonitor;
import org.jymf.entity.Equipment;
import org.jymf.service.IEquipmentService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 设备Controller
 * @author cqs
 * @date   2014年11月11日
 */
@Controller
@RequestMapping(value = "/agent/equipment")
public class AgentEquipmentController {
    @Autowired
    private IEquipmentService equipmentService;
    
	@RequestMapping(value = "main")
	public String init(Model model, Equipment equipment,HttpServletRequest request) {
		PageView pageView = new PageView(1);
		AgentMonitor agent=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
		pageView = equipmentService.query(pageView, equipment,agent.getId().longValue());
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("equipment", equipment);
		
		return "/agent/equipment/main";
	}

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, Equipment equipment, String pageNow,HttpServletRequest request) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        AgentMonitor agent=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
		pageView = equipmentService.query(pageView, equipment,agent.getId().longValue());
		
        model.addAttribute("pageView", pageView);
        model.addAttribute("equipment", equipment);
        return "/agent/equipment/main";
    }
}
