package org.jymf.web.company;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jymf.entity.CompanyUser;
import org.jymf.entity.InspectDevice;
import org.jymf.interceptor.Token;
import org.jymf.service.IInspectDeviceService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 企业稽查终端Controller
 * @author cqs
 * @date   2014年06月18日
 */
@Controller
@RequestMapping(value = "/company/inspectDevice")
public class InspectDeviceController {
    @Autowired
    private IInspectDeviceService inspectDeviceService;
    
    @RequestMapping(value = "main")
    public String init(Model model, InspectDevice inspectDevice,HttpSession session) {
    	PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		
        pageView = inspectDeviceService.query(pageView, inspectDevice, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("inspectDevice", inspectDevice);
        
        return "/company/inspectDevice/main";
    }

    /**
     * @param model
     * 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, InspectDevice inspectDevice, String pageNow ,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		
        pageView = inspectDeviceService.query(pageView, inspectDevice, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("inspectDevice", inspectDevice);
        
        return "/company/inspectDevice/main";
    }
    
    /**
     * 进入企业稽查终端添加画面
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    @Token(save=true)
    public String add() {
        return "company/inspectDevice/add";
    }

    /**
     * 将企业稽查终端画面添加内容写入数据库
     * @param inspectDevice
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    @Token(remove=true)
    public String add(InspectDevice inspectDevice, HttpSession session) {
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		
        inspectDeviceService.add(inspectDevice, companyUser.getWorkMode());
        return "redirect:/company/inspectDevice/main";
    }

    /**
     * 进入企业稽查终端更新画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{deviceId}", method = RequestMethod.GET)
    public String update(@PathVariable("deviceId") String deviceId, Map<String, Object> map,HttpSession session) {
    	InspectDevice inspectDevice= new InspectDevice();
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		inspectDevice.setDeviceId(deviceId);
		inspectDevice = inspectDeviceService.findById(inspectDevice, companyUser.getWorkMode());
		map.put("inspectDevice", inspectDevice);
		
    	// 设备状态
		Map<Integer, String> statusMap = new HashMap<Integer, String>(); 
		statusMap.put(0, "挂 起"); 
		statusMap.put(1, "启 用");
		map.put("statusMap", statusMap);
		
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		
        return "company/inspectDevice/edit";
    }
   
    /**
     * 更新设备信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("inspectDevice") InspectDevice inspectDevice, HttpSession session) {
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
    	inspectDeviceService.update(inspectDevice, companyUser.getWorkMode());
        return "redirect:/company/inspectDevice/main";
    }
   
	@RequestMapping(value = "checkId")
	@ResponseBody
	public String checkId(@RequestParam("deviceId") String deviceId, HttpSession session) {
    	InspectDevice inspectDevice= new InspectDevice();
    	CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		inspectDevice.setCompanyId(companyUser.getCompanyId());
		inspectDevice.setDeviceId(deviceId);
		
		inspectDevice = inspectDeviceService.findById(inspectDevice, companyUser.getWorkMode());
		
		if (null == inspectDevice) {
			return "true";
		}else{
			return "false";
		}
	}
}
