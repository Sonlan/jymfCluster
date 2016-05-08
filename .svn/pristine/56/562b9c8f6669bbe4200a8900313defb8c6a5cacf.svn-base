package org.jymf.web.company;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyUser;
import org.jymf.entity.Cordyceps;
import org.jymf.entity.CordycepsLogistic;
import org.jymf.service.ICordycepsLogisService;
import org.jymf.service.ICordycepsService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jymf.common.LableUtil;

/**
 * 虫草培育物流信息管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/company/trace")
public class CordycepsLogisController {
	@Resource
	private ICordycepsLogisService cordycepsLogisService;
	@Resource
	private ICordycepsService cordycepsService;

	/**
	 * 初始化页面
	 * 
	 * @param session
	 * @param cordycepsLogistic
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "main")
	public String init(HttpSession session,
			CordycepsLogistic cordycepsLogistic, String pageNow, Model model) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		CompanyUser companyUser = (CompanyUser) session
				.getAttribute(Constants.SESSION_COMPANY_USER);

		pageView = cordycepsLogisService.query(pageView, cordycepsLogistic,
				companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("cordycepsLogistic", cordycepsLogistic);
		return "/company/trace/main";
	}

	/**
	 * 校验溯源码段，不在任意溯源码区间，即可行
	 * 
	 * @return
	 */
	@RequestMapping("checkStart")
	@ResponseBody
	public String checkStart(@RequestParam("startID") String startID,
			@RequestParam("endID") String endID, @RequestParam("id") String id,
			HttpSession session) {
		CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
		if (null == startID || "".equals(startID))
			return "false";
		if (!LableUtil.checkLabel16(startID).equals("0"))
			return "false";
		long sID = Long.parseLong(startID.substring(0, startID.length()-4));
		long ids = Long.parseLong(id);
		if(!cordycepsLogisService.getCorLogisByTraceId(sID,ids,companyUser.getWorkMode())) return "false";
		if (LableUtil.checkLabel16(endID).equals("0")){
			long eID = Long.parseLong(endID.substring(0, endID.length()-4));	
			if(!cordycepsLogisService.getCorLogisByTraceId(eID,ids,companyUser.getWorkMode())) return "true";
			if (sID >= eID)
				return "false";
			else {
				if (cordycepsLogisService.getCorLogisBySEId(sID, eID, ids,companyUser.getWorkMode()))
					return "true";
				else
					return "false";
			}
		}else return "true";
	}

	@RequestMapping("checktID")
	@ResponseBody
	public String checksID(@RequestParam("startID") String startID,
			@RequestParam("endID") String endID, @RequestParam("id") String id,
			HttpSession session) {
		if ("".equals(endID) || null == endID)
			return "false";

		if (!LableUtil.checkLabel16(endID).equals("0"))
			return "false";
		CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
		long eID = Long.parseLong(endID.substring(0, endID.length()-4));
		long ids = Long.parseLong(id);
		if(!cordycepsLogisService.getCorLogisByTraceId(eID,ids,companyUser.getWorkMode())) return "false";
		if (LableUtil.checkLabel16(startID).equals("0")){
			long sID = Long.parseLong(startID.substring(0, startID.length()-4));
			if(!cordycepsLogisService.getCorLogisByTraceId(sID,ids,companyUser.getWorkMode())) return "true";
			if (sID >= eID)
				return "false";
			else {
				if (cordycepsLogisService.getCorLogisBySEId(sID, eID, ids,companyUser.getWorkMode()))
					return "true";
				else
					return "false";
			}
		}else return "true";
	}

	@RequestMapping("toAdd")
	public String toAdd() {
		return "/company/trace/add";
	}

	@RequestMapping("add")
	public String add(HttpSession session, CordycepsLogistic cordycepsLogistic,	Model model) {
		CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
		cordycepsLogisService.insert(cordycepsLogistic,companyUser.getWorkMode());
		return "redirect:/company/trace/main";

	}

	/**
	 * 新增materialBid弹出框
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(Model model, HttpSession session) {
		return "/company/trace/save";
	}

	/**
	 * 详查MBID弹出框
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(Model model, HttpSession session) {
		return "/company/trace/detail";
	}

	/**
	 * 进入产品类型更新画面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model,
			HttpSession session) {
		CompanyUser companyUser = (CompanyUser) session
				.getAttribute(Constants.SESSION_COMPANY_USER);
		CordycepsLogistic cordycepsLogistic = cordycepsLogisService.select(id,
				companyUser.getWorkMode());
		model.addAttribute("cordycepsLogistic", cordycepsLogistic);

		return "/company/trace/edit";
	}

	/**
	 * 更新产品信息
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("cordycepsLogistic") CordycepsLogistic cordycepsLogistic,
			HttpSession session) {

		// update by wfj @ 2015.4.21 接收图片文件改为接收图片名称
		CompanyUser companyUser = (CompanyUser) session
				.getAttribute(Constants.SESSION_COMPANY_USER);
		if (cordycepsLogisService.update(cordycepsLogistic,
				companyUser.getWorkMode()) != 0)
			return "redirect:/company/trace/main";
		else
			return String.format("redirect:/company/trace/update/%s",
					cordycepsLogistic.getMaterialBatchId());
	}

	/**
	 * 原料批次单选画面,添加原料批次项
	 * 
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping(value = "selectRelationCordyceps")
	public String querySelectrelation(Model model, Cordyceps cordyceps,
			String pageNow, HttpSession session) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		CompanyUser companyUser = (CompanyUser) session
				.getAttribute(Constants.SESSION_COMPANY_USER);
		pageView = cordycepsService.query(pageView, cordyceps,
				companyUser.getWorkMode());
		model.addAttribute("pageView", pageView);
		model.addAttribute("cordyceps", cordyceps);

		return "/company/trace/selectRelationCordyceps";
	}

}
