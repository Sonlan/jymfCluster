package org.jymf.web.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jymf.entity.LabelIndex;
import org.jymf.service.ILabelIndexService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.MeassageConfig;
import org.jymf.utils.PageView;

import com.jymf.common.LableUtil;

/**
 * 标签索引Controller
 * @author cqs
 * @date   2014年5月9日
 */
@Controller
@RequestMapping(value = "/admin/labelIndex")
public class LabelIndexController {
    @Autowired
    private ILabelIndexService labelIndexService;
    @Autowired
    private BusinessLogger businessLogger;
    
    @RequestMapping(value = "main")
    public String init(Model model, LabelIndex labelIndex) {
		PageView pageView = new PageView(1);
		pageView = labelIndexService.query(pageView, labelIndex);
		model.addAttribute("pageView", pageView);
		model.addAttribute("company", labelIndex);
		model.addAttribute("workModelMap",Constants.workModelMap);

        return "/admin/labelIndex/main";
    }

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, LabelIndex labelIndex, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = labelIndexService.query(pageView, labelIndex);
        model.addAttribute("pageView", pageView);
        model.addAttribute("labelIndex", labelIndex);
        model.addAttribute("workModelMap",Constants.workModelMap);
        return "/admin/labelIndex/main";
    }
    
    /**
     * 进入标签索引添加画面
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add() {
        return "admin/labelIndex/add";
    }

    /**
     * 将标签索引画面添加内容写入数据库
     * @param labelIndex
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model,LabelIndex labelIndex, HttpSession session) {
    	// 起始追溯凭证 ？> 结束追溯凭证
    	// 起始标签的位数是否相等
    	if(labelIndex.getStartTid().compareTo(labelIndex.getEndTid())>0 ||
    			labelIndex.getStartTid().length() != labelIndex.getEndTid().length()){
            model.addAttribute("message", MeassageConfig.getMessage("labelIndexCheck"));
            model.addAttribute("labelIndex", labelIndex);
            return "/admin/labelIndex/add";
    	}
    	
    	// 开始追溯凭证是否被注册过
    	if(labelIndexService.checkLableId(labelIndex.getStartTid(),0)){
    		 model.addAttribute("message", MeassageConfig.getMessage("startTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/labelIndex/add";
    	}
    	// 结束追溯凭证是否被注册过
    	if(labelIndexService.checkLableId(labelIndex.getEndTid(),0)){
    		 model.addAttribute("message", MeassageConfig.getMessage("endTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/labelIndex/add";
    	}
    	
    	// 标签状态默认为1，有效
    	labelIndex.setStatus(new BigDecimal(1));
        labelIndexService.add(labelIndex);
		String logStr = String.format("StartId:%s,EndId:%s", labelIndex.getStartTid(), labelIndex.getEndTid());
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("label_index_add", user, labelIndex.getCompanyId(), logStr, labelIndex);
        return "redirect:/admin/labelIndex/main";
    }

    /**
     * 进入标签索引更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, Map<String, Object> map, HttpSession session) {
    	LabelIndex labelIndex = labelIndexService.findById(id.longValue());
		// 当前状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "无 效"); 
		statusMap.put(1, "有 效");
		map.put("statusMap", statusMap);
		map.put("labelIndex", labelIndex);
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
        businessLogger.log("label_index_update_before", user, labelIndex);

		return "admin/labelIndex/edit";
    }

    /**
     * 更新标签索引信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model,@ModelAttribute("labelIndex") LabelIndex labelIndex, HttpSession session) {
    	// 起始追溯凭证 ？> 结束追溯凭证
    	// 起始标签的位数是否相等
    	if(labelIndex.getStartTid().compareTo(labelIndex.getEndTid())>0||
    			labelIndex.getStartTid().length() != labelIndex.getEndTid().length()){
            model.addAttribute("message", MeassageConfig.getMessage("labelIndexCheck"));
            model.addAttribute("labelIndex", labelIndex);
            return "/admin/labelIndex/add";
    	}
    	
    	// 开始追溯凭证是否被注册过
    	if(labelIndexService.checkLableId(labelIndex.getStartTid(),labelIndex.getId().longValue())){
    		 model.addAttribute("message", MeassageConfig.getMessage("startTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/labelIndex/add";
    	}
    	// 结束追溯凭证是否被注册过
    	if(labelIndexService.checkLableId(labelIndex.getEndTid(),labelIndex.getId().longValue())){
    		 model.addAttribute("message", MeassageConfig.getMessage("endTidValid"));
    		 model.addAttribute("labelIndex", labelIndex);
    		 return "/admin/labelIndex/add";
    	}
    	
    	String logStr = String.format("StartId:%s,EndId:%s", labelIndex.getStartTid(), labelIndex.getEndTid());
    	
        labelIndexService.update(labelIndex);
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
        businessLogger.log("label_index_update", user, labelIndex.getCompanyId(), logStr, labelIndex);
        return "redirect:/admin/labelIndex/main";
    }
    
	/**
	 * @param id 16位追溯码,验证
	 * @return
	 */
	@RequestMapping(value="startChk")
	@ResponseBody
	public String startCheck(@RequestParam("startTid") String id) {
		// 追溯码的验证
		if(LableUtil.checkLabel16(id).equals("0")){
			return "true";
		}else{
			return "false";
		}
	}
	
	@RequestMapping(value="endChk")
	@ResponseBody
	public String endCheck(@RequestParam("endTid") String id) {
		// 追溯码的验证
		if(LableUtil.checkLabel16(id).equals("0")){
			return "true";
		}else{
			return "false";
		}
	}
}
