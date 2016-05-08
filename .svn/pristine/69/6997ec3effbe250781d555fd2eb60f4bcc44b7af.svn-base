package org.jymf.web.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jymf.entity.AcInfo;
import org.jymf.entity.CompanyUser;
import org.jymf.service.IAcInfoService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.PageView;

/**
 * 窜货信息Controller
 * @author cqs
 * @date   2014年06月18日
 */
@Controller
@RequestMapping(value = "/company/acInfo")
public class AcInfoController {
    @Autowired
    private IAcInfoService acInfoService;
    
    @RequestMapping(value = "main")
    public String init(Model model, AcInfo acInfo,HttpSession session) {
    	PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		acInfo.setCompanyId(companyUser.getCompanyId());
		
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, 1);   
		//获得当前月第一天  
		Date sdate = calendar.getTime();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		acInfo.setStartDate(sdf.format(sdate));
		acInfo.setEndDate(sdf.format(new Date()));
		
        pageView = acInfoService.query(pageView, acInfo, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("acInfo", acInfo);
        
        return "/company/acInfo/main";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, AcInfo acInfo, String pageNow ,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		acInfo.setCompanyId(companyUser.getCompanyId());
		
        pageView = acInfoService.query(pageView, acInfo, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("acInfo", acInfo);
        
        return "/company/acInfo/main";
    }
}
