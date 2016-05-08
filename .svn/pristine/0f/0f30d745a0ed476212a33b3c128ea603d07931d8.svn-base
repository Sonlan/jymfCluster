package org.jymf.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.jymf.entity.Admin;
import org.jymf.service.DatabaseSynchronize;
import org.jymf.service.IAdminService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;

/**
 * 企业用户Controller.
 * 
 * @author
 */
@Controller
@RequestMapping(value = "/admin")
@SessionAttributes({Constants.SESSION_ADMIN})
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	
	@RequestMapping(value = "main")
	public String init() {
		return "/admin/main";
	}

	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(Admin admin, HttpSession session,RedirectAttributes redirectAttributes) {
		if(adminService.findByAccountAndPassword(admin.getAccount(),admin.getPassword())){
			Common.initSession(session);
			session.setAttribute(Constants.SESSION_ADMIN, admin.getAccount());

			session.setAttribute(Constants.SESSION_ADMINUSER, adminService.findByAccount(admin));
			businessLogger.log(String.format("%s 登录成功！", admin.getAccount()));
			
			return "redirect:/admin/main";
		}else{
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/admin/login";
		}
	}
	
}
