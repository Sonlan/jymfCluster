package org.jymf.web.admin;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jymf.entity.Company;
import org.jymf.entity.Product;
import org.jymf.service.ICompanyService;
import org.jymf.service.IProductService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 企业用户Controller
 * 
 * @author cqs
 * @date 2014年5月21日
 */
@Controller
@RequestMapping(value = "/admin/dialog")
public class SelectCompanyController {
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IProductService productService;

	/**
	 * 企业单选画面
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "querySelect")
	public String querySelect(Model model, Company company, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		company.setFlag(new BigDecimal(2));
		company.setStatus(new BigDecimal(0));

		pageView = companyService.query(pageView, company);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap",Constants.workModelMap);
		return "/admin/dialog/selectCompany";
	}

	/**
	 * 企业单选画面,用于监管关系操作,添加
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "querySelectrelation")
	public String querySelectrelation(Model model, Company company, String pageNow) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		company.setFlag(new BigDecimal(2));
		company.setStatus(new BigDecimal(0));

		pageView = companyService.query(pageView, company);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap",Constants.workModelMap);
		
		
		return "/admin/dialog/selectRelationCompany";
	}

	/**
	 * 企业单选画面,用于监管关系操作,更新
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "queryUpdate/{monitorId}")
	public String queryUpdate(@PathVariable("monitorId") BigDecimal monitorId,
			                  HttpServletRequest request, Model model, Company company, String pageNow) {
		request.getSession().setAttribute("monitorId", monitorId);
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		company.setFlag(new BigDecimal(2));
		company.setStatus(new BigDecimal(0));

		pageView = companyService.queryCompanyMonitor(pageView, company, monitorId.longValue());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap",Constants.workModelMap);
		return "/admin/dialog/selectRelationQuery";
	}

	/**
	 * 企业单选画面,用于代理商关系操作,更新
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "agentQueryUpdate/{monitorId}")
	public String queryAgentUpdate(@PathVariable("monitorId") BigDecimal monitorId,
			                       HttpServletRequest request, Model model, Company company, String pageNow) {
		request.getSession().setAttribute("monitorId", monitorId);
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		company.setFlag(new BigDecimal(2));
		company.setStatus(new BigDecimal(0));

		pageView = companyService.queryAgentUpdate(pageView, company, monitorId.longValue());
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", company);
		model.addAttribute("workModelMap",Constants.workModelMap);
		return "/admin/dialog/selectAgentRelationQuery";
	}

	/**
	 * 商户单选画面,用于设备管理 商户ID选择
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "selectProduct/{companyId}")
	public String selectProduct(Model model,
			                    @PathVariable("companyId") String companyId, Product product, String pageNow) {
		product.setCompanyId(new BigDecimal(companyId));
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = productService.selectProduct(pageView, product, Constants.WM_SHCH);

		model.addAttribute("product", product);
		model.addAttribute("pageView", pageView);

		return "/admin/dialog/selectProductQuery";
	}
}
