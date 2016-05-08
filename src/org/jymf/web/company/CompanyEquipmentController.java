package org.jymf.web.company;

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
import org.jymf.entity.CompanyUser;
import org.jymf.entity.Equipment;
import org.jymf.service.IEquipmentService;
import org.jymf.service.IProductService;
import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 企业的设备管理
 * @author cqs
 * @date   2014年11月26日
 */
@Controller
@RequestMapping(value = "/company/equipment")
public class CompanyEquipmentController {
    @Autowired
    private IEquipmentService equipmentService;
    
    @Autowired
    private BusinessLogger businessLogger;
    
    @Autowired
    private IProductService productService;
    
	@RequestMapping(value = "main")
	@SuppressWarnings("unchecked")
	public String init(Model model, Equipment equipment,HttpSession session) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		equipment.setCompanyId(companyUser.getCompanyId().toString());
		PageView pageView = new PageView(1);
		pageView = equipmentService.query(pageView, equipment);
		
		List<Equipment> list = (List<Equipment>)pageView.getRecords();
		setProductName(list);
		
		model.addAttribute("pageView", pageView);
		equipment.setWorkMode(companyUser.getWorkMode());
		model.addAttribute("equipment", equipment);
		
		return "/company/equipment/main";
	}

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    @SuppressWarnings("unchecked")
    public String query(Model model, Equipment equipment, String pageNow,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		equipment.setCompanyId(companyUser.getCompanyId().toString());
		
        pageView = equipmentService.query(pageView, equipment);
        List<Equipment> list = (List<Equipment>)pageView.getRecords();
		setProductName(list);
		
        model.addAttribute("pageView", pageView);
        equipment.setWorkMode(companyUser.getWorkMode());
        model.addAttribute("equipment", equipment);
        return "/company/equipment/main";
    }
    
    /**
     * 进入设备更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Map<String, Object> map,HttpSession session) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
    	Equipment equipment = equipmentService.findById(id);
    	setProductName(equipment);
    	
    	map.put("equipment", equipment);

		businessLogger.log("equipment_update_before", 
				           String.format("%s_%s", companyUser.getId(), companyUser.getCompanyId()), 
				           equipment);
		
        return "company/equipment/edit";
    }

    /**
     * 更新设备信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("equipment") Equipment equipment,HttpSession session) {
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
        equipmentService.update(equipment);
		String logStr = String.format("EquipmentId:%s", equipment.getCompanyId(),equipment.getId());
		businessLogger.log("equipment_update", 
				            String.format("%s_%s", companyUser.getId(), companyUser.getCompanyId()), 
				            companyUser.getCompanyId(), logStr,equipment);
		
        return "redirect:/company/equipment/main";
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
