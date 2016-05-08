package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AdminMapper;
import org.jymf.entity.Admin;
import org.jymf.service.IAdminService;
import org.jymf.utils.MD5;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员操作实现类
 * @author lxg
 * @date   2014年07月16日
 */
@Service("adminService")
public class AdminServiceImpl implements IAdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	
	@Override
	public Boolean findByAccountAndPassword(String account,String password){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("account", account);
		try {
			map.put("password",MD5.getInstance().encrypt(password) );
		} catch (Exception e) {
			return false;
		}
		
		return this.adminMapper.selectByAccountAndPassword(map)!=null;
	}

	@Override
	public PageView query(PageView pageView, Admin admin) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("pageView", pageView);
		map.put("t", admin);
		
		List<Admin> list = adminMapper.query(map);
		pageView.setRecords(list);
		
		return pageView;
	}

	@Override
	public boolean checkAccount(String account) {
		boolean flag = false;
		Admin admin = adminMapper.findByAccount(account);
		if(null==admin){
			flag=true;
		}
		return	flag;
	}

	@Override
	public Admin findByAccount(Admin admin) {
		return adminMapper.findByAccount(admin.getAccount());
	}

	@Override
	public void addAdmin(Admin admin) {
		try {
			admin.setPassword(MD5.getInstance().encrypt("123456"));
		} catch (Exception e) {
			return;
		}
		adminMapper.insert(admin);
	}

	@Override
	public Admin findById(String id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	public void editAdmin(Admin admin) {
		Admin oldAdmin=adminMapper.selectByPrimaryKey(admin.getId());
		if(!admin.equals(oldAdmin)){
			admin.setPassword(oldAdmin.getPassword());
			adminMapper.updateByPrimaryKeySelective(admin);
		}
	}

	@Override
	public void updatePwd(Admin admin){
		try {
			admin.setPassword(MD5.getInstance().encrypt(admin.getPassword()));
		} catch (Exception e) {
			return;
		}
		adminMapper.updateByPrimaryKeySelective(admin);
	}
   
	
	
}
