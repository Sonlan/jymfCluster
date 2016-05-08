package org.jymf.web.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jymf.entity.Logs;
import org.jymf.service.ILogsService;
import org.jymf.utils.Common;
import org.jymf.utils.PageView;

/**
 * 日志Controller.
 * 
 * @author
 */
@Controller
@RequestMapping(value = "/admin/logs")
public class LogsController {
	@Autowired
	private ILogsService logsService;
	
	/**
	 * 
	 * 日志主页面
	 * null 第一次到main页面开始日期和结束日期的值设置为空
	 */
	@RequestMapping(value = "main")
	public String init(Model model,Logs logs) {

		PageView pageView = new PageView(1);

		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		logs.setStartDate(sdf.format(sdate));
		logs.setEndDate(sdf.format(new Date()));
		
		pageView = logsService.query(pageView, logs);
		
		model.addAttribute("pageView", pageView);
				
		model.addAttribute("logs", logs);
		return "/admin/logs/main";
	}
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, Logs logs, String pageNow) {
		//获取前台开始日期和结束日期
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = logsService.query(pageView, logs);
		model.addAttribute("pageView", pageView);
		model.addAttribute("logs", logs);
		return "/admin/logs/main";
	}
	
}
