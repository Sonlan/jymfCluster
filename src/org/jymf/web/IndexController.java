package org.jymf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value = "/monitor/login")
	public String montitorLogin() {
		return "monitor/login";
	}
	
	@RequestMapping(value = "/admin/login")
	public String adminLogin() {
		return "admin/login";
	}
	
	@RequestMapping(value = "/company/login")
	public String companyLogin() {
		return "company/login";
	}
	
	@RequestMapping(value = "/agent/login")
	public String agentMonitorLogin() {
		return "agent/login";
	}
	
	@RequestMapping(value = "/companyMonitor/login")
	public String companyMonitorLogin() {
		return "companyMonitor/login";
	}
}