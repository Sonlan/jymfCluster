package org.jymf.service;

import org.jymf.entity.Admin;
import org.jymf.utils.PageView;

/**
 * 管理员用户操作接口
 * @author cqs
 * @date   2014年5月7日
 */
public interface IAdminService {
	/**
	 * 查找用户，通过用户名和密码
	 * @param account
	 * @param password
	 * @return
	 *        True 用户存在
	 *       False 用户不存在
	 */
	Boolean findByAccountAndPassword(String account,String password);
	
	/**
	 * 查询全部管理员用户
	 * update by wfj 
	 */
	PageView query(PageView pageView, Admin	admin);
	
	/**
	 * 根据account查询该用户的全部信息
	 * update by wfj 
	 */
	Admin findByAccount(Admin admin);
	

	/**
	 * 根据account查询是否有重复名称
	 * update by wfj 
	 */
	boolean checkAccount(String account);

	/**
	 * 新增管理员用户
	 * update by wfj 
	 */
	void addAdmin(Admin admin);

	/**
	 * 通过用户ID查找相关用户信息
	 * update by wfj 
	 */
	Admin findById(String id);

	/**
	 * 修改管理员用户信息
	 * update by wfj
	 */
	void editAdmin(Admin admin);
	
	void updatePwd(Admin admin);
}
