package org.jymf.web.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
import org.jymf.entity.Equipment;
import org.jymf.interceptor.Token;
import org.jymf.service.IEquipmentService;
import org.jymf.service.IProductService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 设备Controller
 * @author cqs
 * @date   2014年5月9日
 */
@Controller
@RequestMapping(value = "/admin/equipment")
public class EquipmentController {
    @Autowired
    private IEquipmentService equipmentService;
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    private BusinessLogger businessLogger;
    
	@RequestMapping(value = "main")
	@SuppressWarnings("unchecked")
	public String init(Model model, Equipment equipment) {
		
		PageView pageView = new PageView(1);
		pageView = equipmentService.query(pageView, equipment);
		List<Equipment> list = (List<Equipment>)pageView.getRecords();
		setProductName(list);
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("equipment", equipment);
		model.addAttribute("workModelMap",Constants.workModelMap);

		return "/admin/equipment/main";
	}

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    @SuppressWarnings("unchecked")
    public String query(Model model, Equipment equipment, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = equipmentService.query(pageView, equipment);
    	List<Equipment> list = (List<Equipment>)pageView.getRecords();
		setProductName(list);
		
        model.addAttribute("pageView", pageView);
        model.addAttribute("equipment", equipment);
        model.addAttribute("workModelMap",Constants.workModelMap);
        return "/admin/equipment/main";
    }
    
    /**
     * 进入设备添加画面
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    @Token(save=true)
    public String add() {
        return "admin/equipment/add";
    }

    /**
     * 将设备画面添加内容写入数据库
     * @param equipment
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    @Token(remove=true)
    public String add(Equipment equipment, HttpSession session) {
		// 启用
		equipment.setDevStatus(new BigDecimal(1));
		// 所有权限
		equipment.setLimit(new BigDecimal(31));
		equipment.setDevInfo(equipment.getName());
	    equipmentService.add(equipment);
	    
		String logStr = String.format("EquipmentId:%s", equipment.getId());
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("equipment_add", user, new BigDecimal(equipment.getCompanyId()),logStr,equipment);
	    return "redirect:/admin/equipment/main";
    }

    /**
     * 进入设备更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Map<String, Object> map, HttpSession session) {
    	Equipment equipment = equipmentService.findById(id);
    	setProductName(equipment);
    	
    	map.put("equipment", equipment);
    	// 设备状态
		Map<Integer, String> devStatusMap = new HashMap<Integer, String>(); 
		devStatusMap.put(0, "挂 起"); 
		devStatusMap.put(1, "启 用");
		devStatusMap.put(2, "作 废");
		map.put("devStatusMap", devStatusMap);
		
		// 操作权限
		Map<Integer, String> limitMap = new HashMap<Integer, String>(); 
		limitMap.put(0, "挂 起"); 
		limitMap.put(31, "所有权限");
		map.put("limitMap", limitMap);
		
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("equipment_update_before", user,equipment);
		
        return "admin/equipment/edit";
    }

    /**
     * 更新设备信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("equipment") Equipment equipment, HttpSession session) {
        equipmentService.update(equipment);
        
		String logStr = String.format("EquipmentId:%s", equipment.getId());
		String user= (String)session.getAttribute(Constants.SESSION_ADMIN);
		businessLogger.log("equipment_update", user, new BigDecimal(equipment.getCompanyId()), logStr,equipment);
        return "redirect:/admin/equipment/main";
    }   
    
	@RequestMapping(value = "checkId")
	@ResponseBody
	public String checkId(@RequestParam("id") String id) {
		Equipment equipment = equipmentService.findById(id);
		
		if (null == equipment) {
			return "true";
		}else{
			return "false";
		}
	}
	
    private void setProductName(List<Equipment> list){
    	for(Equipment eq : list){
           	if(eq.getWorkMode().compareTo(Constants.WM_SHCH)==0){
        		String productName = productService.findProductName(eq.getProductId(),eq.getCompanyId(),Constants.WM_SHCH);
        		eq.setProductName(productName);
        	}
        }
    }
    
    private void setProductName(Equipment eq){
       	if(eq.getWorkMode().compareTo(Constants.WM_SHCH)==0){
    		String productName = productService.findProductName(eq.getProductId(),eq.getCompanyId(),Constants.WM_SHCH);
    		eq.setProductName(productName);
       	}
    }
}
