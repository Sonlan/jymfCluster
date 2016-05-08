package org.jymf.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.jymf.entity.CompanyUser;
import org.jymf.service.ICompanyUserService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.PageView;

/**
 * 企业用户Controller
 * @author cqs
 * @date   2014年5月7日
 */
@Controller
@RequestMapping(value = "/admin/companyUser")
public class CompanyUserController {
    @Autowired
    private ICompanyUserService companyUserService;
    
	@Autowired
	private BusinessLogger businessLogger;
	
    @RequestMapping(value = "main")
    public String init() {
        return "/admin/companyUser/main";
    }

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, CompanyUser companyUser, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = companyUserService.query(pageView, companyUser);
        model.addAttribute("pageView", pageView);
        model.addAttribute("companyUser", companyUser);
        
        return "/admin/companyUser/main";
    }
    
    /**
     * 进入企业用户添加画面
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add() {
        return "admin/companyUser/add";
    }

    /**
     * 将企业用户画面添加内容写入数据库
     * @param companyUser
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(CompanyUser companyUser, RedirectAttributes redirectAttributes) {
        try{
            companyUserService.add(companyUser);
    		businessLogger.log("company_user_add", " ",companyUser);
    		
            return "redirect:/admin/companyUser/main";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "企业用户添加失败，请确认！");
            return "admin/companyUser/add";
        }
    }

    /**
     * 进入企业用户更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model) {
        model.addAttribute("companyUser", companyUserService.findById(id));
        return "admin/companyUser/edit";
    }

    /**
     * 更新企业用户信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("companyUser") CompanyUser companyUser) {
        companyUserService.update(companyUser,"");
        return "redirect:/admin/companyUser/main";
    }
}
