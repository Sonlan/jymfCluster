package org.jymf.web.admin;

import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyBase;
import org.jymf.entity.CompanyCount;
import org.jymf.service.ICompanyService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业用户管理Controller
 * 
 * @author cqs
 * @date 2014年7月16日
 */
@Controller
@RequestMapping(value = "/admin/product/count")
public class ProductCountController {

	@Autowired
	private BusinessLogger businessLogger;

	@Autowired
	private ICompanyService companyService;

	/**
	 * 进入企业用户一览显示画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "main")
	public String init(Model model, CompanyCount companyCount,HttpSession session) {

		PageView pageView = new PageView(1);
		CompanyBase companyBase = (CompanyBase) session.getAttribute(Constants.SESSION_COMPANY_BASE);

		companyCount.setCompanyId(companyBase.getId());
		companyCount.setStartDate(Common.yesterdayModthFirstday());
		companyCount.setEndDate(Common.yesterday());

		pageView = companyService.queryCountByCompany(pageView, companyCount, companyBase.getWorkMode());
		model.addAttribute("pageView", pageView);

		model.addAttribute("companyCount", companyCount);

		return "/admin/product/count";
	}

	/**
	 * 检索查询
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, CompanyCount companyCount, String pageNow,	HttpSession session) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}

		CompanyBase companyBase = (CompanyBase) session
				.getAttribute(Constants.SESSION_COMPANY_BASE);
		companyCount.setCompanyId(companyBase.getId());

		pageView = companyService.queryCountByCompany(pageView, companyCount, companyBase.getWorkMode());

		model.addAttribute("companyCount", companyCount);
		model.addAttribute("pageView", pageView);

		return "/admin/product/count";
	}
}
