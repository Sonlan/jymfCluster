package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.CompanyMonitor;
import org.jymf.utils.PageView;

/**
 * 监管管理操作接口
 * @author cqs
 * @date   2014年05月09日
 */
public interface ICompanyMonitorService{
    /**
     * 通过主键获取记录
     * @param id
     * @return
     */
    CompanyMonitor findById(long id);
    /**
     * 分页处理
     * @param pageView
     * @param companyMonitor
     * @return
     */
    PageView query(PageView pageView, CompanyMonitor companyMonitor);
    /**
     * 监管管理信息
     * @param companyMonitor
     */
    void update(CompanyMonitor companyMonitor);
    
    /**
     * 添加监管管理
     * @param companyMonitor
     */
    void add(CompanyMonitor companyMonitor);
    
    /**
     * 获取最大的ID
     * @return
     */
    int findMaxId();
    
    /**
	 * 通过用户名查找信息
	 */
	CompanyMonitor selectCompanyMonitor(String account);
	
    /**
	 * 查找用户，通过用户名和密码
	 * @param account
	 * @param password
	 * @return
	 *        True 用户存在
	 *       False 用户不存在
	 */
	Boolean findByAccountAndPassword(String account,String pwd);
	
	/**
	 * 修改当前用户的密码
	 */
	int updateByPrimaryKey(CompanyMonitor companyMonitor);
	/**
	 * 查找下一级监管单位
	 * @param pageView
	 * @param companyMonitor
	 * @return
	 */
    public PageView monitorquery(PageView pageView, CompanyMonitor companyMonitor);
    /**
     * 根据monitor_id 联合查询得到该监管单位下所有的公司
     * @param pageView
     * @param monitorId
     * @return
     */
    public PageView companyListquery(PageView pageView, BigDecimal monitorId);
}
