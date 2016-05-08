package org.jymf.web.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jymf.entity.AgentMonitor;
import org.jymf.entity.LabelIndex;
import org.jymf.service.ILabelIndexService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 标签索引Controller
 * @author cqs
 * @date   2014年11月11日
 */
@Controller
@RequestMapping(value = "/agent/labelIndex")
public class AgentLabelIndexController {
    @Autowired
    private ILabelIndexService labelIndexService;
    
    @RequestMapping(value = "main")
    public String init(Model model, LabelIndex labelIndex,HttpServletRequest request) {
		PageView pageView = new PageView(1);
		AgentMonitor agent=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
		pageView = labelIndexService.query(pageView, labelIndex,agent.getId().longValue());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", labelIndex);
		model.addAttribute("workModelMap",Constants.workModelMap);
		
        return "/agent/labelIndex/main";
    }

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, LabelIndex labelIndex, String pageNow,HttpServletRequest request) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        AgentMonitor agent=(AgentMonitor) request.getSession().getAttribute(Constants.SESSION_AGENT);
        pageView = labelIndexService.query(pageView, labelIndex,agent.getId().longValue());
        model.addAttribute("pageView", pageView);
        model.addAttribute("labelIndex", labelIndex);
        model.addAttribute("workModelMap",Constants.workModelMap);
        
        return "/agent/labelIndex/main";
    }
 }
