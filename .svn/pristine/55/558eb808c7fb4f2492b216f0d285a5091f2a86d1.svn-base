package org.jymf.service;


import org.jymf.entity.CompanyUser;
import org.jymf.utils.PageView;

/**
 * 企业用户操作接口
 * @author cqs
 * @date   2014年05月09日
 */
public interface ICompanyUserService{
    /**
     * 通过主键获取记录
     * @param id
     * @return
     */
    CompanyUser findById(String id);
    /**
     * 分页处理
     * @param pageView
     * @param companyUser
     * @return
     */
    PageView query(PageView pageView, CompanyUser companyUser);
    /**
     * 企业用户信息,只能更新权限和状态，初始化密码
     * @param companyUser
     * @param pwd “” 不修改密码，不为空时需要修改密码
     */
    void update(CompanyUser companyUser,String pwd);
    
    /**
     * 添加企业用户
     * @param companyUser
     */
    void add(CompanyUser companyUser);
    
    /**
     * 查找用户，通过用户名&密码
     * @param companyUser
     * @return
     */
    CompanyUser findUser(CompanyUser companyUser);
}
