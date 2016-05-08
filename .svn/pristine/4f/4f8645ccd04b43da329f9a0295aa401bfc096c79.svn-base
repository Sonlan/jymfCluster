package org.jymf.web.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jymf.entity.Admin;
import org.jymf.interceptor.Token;
import org.jymf.service.IAdminService;
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

/**
 * 角色管理Controller
 * @author wfj
 * @date   2015年5月25日
 */
@Controller
@RequestMapping(value = "/admin/roles")
public class RolesController {
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private BusinessLogger businessLogger;
	
	@RequestMapping(value="main")
	public String main(Model model,Admin admin, String pageNow,HttpSession session){
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		
		Admin adminUser = (Admin) session.getAttribute(Constants.SESSION_ADMINUSER);
		admin.setRole(adminUser.getRole());
		admin.setAccount(adminUser.getAccount());
		pageView=adminService.query(pageView, admin);
		
		model.addAttribute("admin",admin);
		model.addAttribute("pageView", pageView);
		return "/admin/roles/main";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	@Token(save=true)
	public String beforeAdd(Model model, Map<String, Object> map,HttpServletRequest request,HttpSession session){
		
		Admin sessionAdmin = (Admin) session.getAttribute(Constants.SESSION_ADMINUSER);
		
		Map<String,Object> roleMap = new HashMap<String ,Object>();
		if(sessionAdmin.getRole()<=1){
			roleMap.put("1", "高级管理员");
		}
		roleMap.put("2", "普通管理员");
		map.put("roleMap", roleMap);
		
		model.addAttribute("admin",new Admin());
		return "admin/roles/add";
	}
	
	
	@RequestMapping("checkAccountAdd")
	@ResponseBody
	public String checkAccountAdd(String account,HttpSession session){
		String flag="false";
		if(adminService.checkAccount(account)){
			flag="true";
		}
		return flag;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@Token(remove=true)
	public String add(Admin admin, Model model){
		adminService.addAdmin(admin);
		return "redirect:/admin/roles/main";
	}
	
	@RequestMapping(value="initPwd/{id}", method=RequestMethod.GET)
	public String initPwd(@PathVariable("id")String id,Map<String,Object> map,Model model,HttpSession session){
		Admin admin = new Admin();
		admin.setId(id);
		admin.setPassword("123456");
    	adminService.updatePwd(admin);
        return "redirect:/admin/roles/main";
	}	
	
	
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	public String updatePwd(HttpSession session,HttpServletRequest request){
		Admin admin = (Admin)session.getAttribute(Constants.SESSION_ADMINUSER);
		String newPwd = request.getParameter("newPwd");
		admin.setPassword(newPwd);
		adminService.updatePwd(admin);
		
		session.removeAttribute(Constants.SESSION_ADMIN);
		session.removeAttribute(Constants.SESSION_ADMINUSER);
		
		return "redirect:/admin/login";
	}
	
	/**
	 * 用户修改密码画面
	 * @return
	 */
	@RequestMapping(value = "updpwd")
	public String updpwd() {
		return "/admin/roles/updpwd";
	}
	
	/**
	 * 验证当前用户的密码是否正确
	 * @param session
	 * @param oldPwd
	 * @return
	 */
	@RequestMapping(value="checkOldPwd")
	@ResponseBody
	public String checkOldPwd(HttpSession session,@RequestParam("oldPwd") String oldPwd) {
		Admin admin = (Admin)session.getAttribute(Constants.SESSION_ADMINUSER);
		// 追溯码的验证
		try {
			if(admin.getPassword().equals(MD5.getInstance().encrypt(oldPwd))){
				return "true";
			}else{
				return "false";
			}
		} catch (Exception e) {
			return "false";
		} 
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String beforeEdit(@PathVariable("id")String id,Map<String,Object> map,Model model,HttpSession session){
		Admin admin = adminService.findById(id);
		Map<String,Object> roleMap = new HashMap<String, Object>();
		if(admin.getRole()<=1){
			roleMap.put("1", "高级管理员");
		}
		roleMap.put("2", "普通管理员");
		map.put("roleMap", roleMap);
		
		Map<String,Object> statusMap = new HashMap<String, Object>();
		statusMap.put("0", "正常");
		statusMap.put("1", "禁用");
		map.put("statusMap", statusMap);
		
		model.addAttribute("admin",admin);
		
		return "/admin/roles/edit";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(Admin admin){
		admin.setPassword(null);
		adminService.editAdmin(admin);
		return "redirect:/admin/roles/main";
	}
	
}
