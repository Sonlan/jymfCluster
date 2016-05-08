package org.jymf.web.company;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jymf.entity.Certificate;
import org.jymf.entity.CompanyUser;
import org.jymf.interceptor.Token;
import org.jymf.service.ICertificateService;
import org.jymf.utils.Common;
import org.jymf.utils.Constants;
import org.jymf.utils.FileManager;
import org.jymf.utils.PageView;
import org.jymf.utils.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/company/certificate")
public class CertificateController {
    @Autowired
    private ICertificateService certificateService;
    
    @Autowired
    private FileManager fileManager;
    
    @Autowired
    private SysConfig sysConfig;
    
    @RequestMapping(value = "main")
    public String init(Model model, Certificate certificate,HttpSession session) {
    	PageView pageView = new PageView(1);
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
        pageView = certificateService.query(pageView, certificate,companyUser, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("certificate", certificate);
        
        return "/company/certificate/main";
    }

    @RequestMapping("query")
    public String query(Model model, Certificate certificate, String pageNow ,HttpSession session) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        
		CompanyUser companyUser = (CompanyUser)session.getAttribute(Constants.SESSION_COMPANY_USER);
		
		pageView = certificateService.query(pageView, certificate,companyUser, companyUser.getWorkMode());
        model.addAttribute("pageView", pageView);
        model.addAttribute("certificate", certificate);
        
        return "/company/certificate/main";
    }
    
    @RequestMapping(value="add",method=RequestMethod.GET)
    @Token(save=true)
    public String add(){
    	return "/company/certificate/add";
    }
    
    
    @RequestMapping(value="add",method=RequestMethod.POST)
    @Token(remove=true)
    public String add(Certificate certificate,HttpServletRequest request,HttpSession session){
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFile1 = multipartRequest.getFiles("imgFile1");
		List<MultipartFile> imgFile2 = multipartRequest.getFiles("imgFile2"); 
		
		CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
		certificateService.add(certificate,imgFile1,imgFile2,companyUser,companyUser.getWorkMode());
		
    	return "redirect:/company/certificate/main";
    }
    
    @RequestMapping(value="edit/{id}",method=RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id,Model model,HttpSession session){
    	CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
    	model.addAttribute("certificate", certificateService.findById(id,companyUser,companyUser.getWorkMode()));
    	return "/company/certificate/edit";
    }

    @RequestMapping(value="edit",method=RequestMethod.POST)
    public String edit(Certificate certificate,HttpSession session,HttpServletRequest request){
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> imgFile1 = multipartRequest.getFiles("imgFile1");
		List<MultipartFile> imgFile2 = multipartRequest.getFiles("imgFile2"); 
    	
    	CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
    	certificateService.edit(certificate,imgFile1,imgFile2,companyUser,companyUser.getWorkMode());
    	
    	return "redirect:/company/certificate/main";
    }
    
    @RequestMapping(value="view/{id}",method=RequestMethod.GET)
    public String view(@PathVariable("id")Integer id,Model model,HttpSession session){
    	CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
    	model.addAttribute("certificate", certificateService.findById(id,companyUser,companyUser.getWorkMode()));
    	return "/company/certificate/view";
    }
    
    
    @RequestMapping(value="showPic/{certificateId}/{picName}")
    public void showPic(@PathVariable("picName")String picName,
    					@PathVariable("certificateId") BigDecimal certificateId,
    					HttpSession session,HttpServletResponse response){
    	CompanyUser companyUser = (CompanyUser) session.getAttribute(Constants.SESSION_COMPANY_USER);
    	// TODO
    	/*String path = sysConfig.getCertificatePhotoPath(companyUser.getCompanyId(), certificateId);
    	String suffix=FileUtils.getSuffix(path, picName);
    	String picPath = String.format("%s%s%s", path,picName,suffix);
    	fileManager.getPic(picPath, response);*/
    }
    
}
