package org.jymf.web.company;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jymf.entity.CompanyUser;
import org.jymf.entity.Cordyceps;
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

@Controller
@RequestMapping(value = "/company/cultivation")
public class CultivationController {

	@Resource
	private ICordycepsService cordycepsService;
	
	/**
	 * 初始化页面
	 * @param session
	 * @param cordyceps
	 * @param model
	 * @return
	 */
	@RequestMapping(value="main")
	public String init(HttpSession session, Cordyceps cordyceps, Model model,String pageNow){
		
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
        pageView = cordycepsService.query(pageView, cordyceps, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("cordyceps", cordyceps);
		return "/company/cultivation/main";
	}
	 @RequestMapping("toAdd")
	    public String toAdd() {
	        return "/company/cultivation/add";
	    }
	 @RequestMapping("add")
	    public String add(HttpSession session, Cordyceps cordyceps, Model model) {

			CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);	
	        cordycepsService.insert(cordyceps, companyUser.getWorkMode());
			return "redirect:/company/cultivation/main";
	    }
		/**
		 * 检测输入的materialBatchId是否重复
		 * @param materialBatchId
		 * @return
		 */
		@RequestMapping(value = "checkId")
		@ResponseBody
		public String checkId(@RequestParam("materialBatchId") String materialBatchId, HttpSession session) {
			CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
			String regex = "^[A-Z_a-z0-9]+$";
			if(!materialBatchId.matches(regex)) return "false";
			Cordyceps cordyceps = cordycepsService.select(materialBatchId, companyUser.getWorkMode());
			
			if (null == cordyceps) {
				return "true";
			}else{
				return "false";
			}
		}
	    /**
	     * 进入产品类型更新画面
	     * @param id
	     * @param model
	     * @return
	     * @throws UnsupportedEncodingException 
	     */
		@RequestMapping(value = "update/{materialBatchId}", method = RequestMethod.GET)
	    public String update(@PathVariable("materialBatchId") String  materialBatchId, Model model, HttpSession session,HttpServletRequest request){
	    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
	    	String MBID = materialBatchId;
	    	String CLId = "";
	    	if(materialBatchId.contains("-----")){
	    		CLId = materialBatchId.split("-----")[1];
	    		MBID = materialBatchId.split("-----")[0];
	    	}
	    	Cordyceps cordyceps = cordycepsService.select(MBID, companyUser.getWorkMode());
	    	model.addAttribute("cordyceps",cordyceps);
	    	if(materialBatchId.contains("-----") ) {request.setAttribute("CLId", CLId); return "/company/trace/show";}
			return "/company/cultivation/edit";
	    }
	    
		/**
		 * 更新产品信息
		 */
		@RequestMapping(value = "update", method = RequestMethod.POST)
		public String update(@ModelAttribute("cordyceps") Cordyceps cordyceps, HttpSession session) {
			
			//update by wfj @ 2015.4.21 接收图片文件改为接收图片名称
			CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
			if(cordycepsService.update(cordyceps, companyUser.getWorkMode())!=0)
				return "redirect:/company/cultivation/main";
			else return String.format("redirect:/company/cultivation/update/%s", cordyceps.getMaterialBatchId());
		}
}
