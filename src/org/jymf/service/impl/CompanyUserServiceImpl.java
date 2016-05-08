package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.CompanyUserMapper;
import org.jymf.entity.CompanyUser;
import org.jymf.service.ICompanyUserService;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 企业用户操作实现类
 * @author cqs
 * @date   2014年05月09日
 */
@Service("companyUserService")
public class CompanyUserServiceImpl implements ICompanyUserService{
    @Autowired
    private CompanyUserMapper companyUserDao;

    @Override
    public PageView query(PageView pageView, CompanyUser companyUser) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", companyUser);
        
        List<CompanyUser> list = companyUserDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public CompanyUser findById(String id) {
        return companyUserDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(CompanyUser companyUser,String pwd) {
    	if(!pwd.equals("")){
    		try {
    		companyUser.setPassword(MD5.getInstance().encrypt(pwd));
    		}catch(Exception e){
    			return;
    		}
    	}
        companyUserDao.updateByPrimaryKeySelective(companyUser);
    }

    @Override
    public void add(CompanyUser companyUser) {
    	companyUser.setId(String.format("%s_%s", companyUser.getId(),companyUser.getCompanyId()));
    	try {
			companyUser.setPassword(MD5.getInstance().encrypt("123456"));
		} catch (Exception e) {
			return;
		}
        companyUser.setStatus(new BigDecimal(0));
        companyUserDao.insert(companyUser);
    }

	@Override
	public CompanyUser findUser(CompanyUser companyUser) {
		companyUser.setId(String.format("%s_%s", companyUser.getId(),companyUser.getCompanyId()));
		try {
			companyUser.setPassword(MD5.getInstance().encrypt(companyUser.getPassword()));
		} catch (Exception e) {
			return null;
		}  		
		return companyUserDao.findUserByIdAndPwd(companyUser);
	}

}
