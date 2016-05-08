package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.Admin;

public interface AdminMapper{
    
	/**
	 * 查询用户，通过用户名/密码
	 * @param map
	 * @return
	 */
    Admin selectByAccountAndPassword(Map<String, Object> map);
    
    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    /**
     * 查询全部管理用户
     * update by wfj at 2015.5.25
     */
    List<Admin> query(Map<String, Object> map);

    /**
     * 根据account查询用户信息
     * update by wfj 2015.5.26
     */
	Admin findByAccount(String account);
	
	/**
	 * 根据条件查询管理员用户
	 */
    List<Admin> findAdmins(Map<String, Object> map);

}