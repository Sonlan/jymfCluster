package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.jymf.dao.CompanyMapper;
import org.jymf.dao.CompanyMonitorMapper;
import org.jymf.entity.Company;
import org.jymf.entity.CompanyMonitor;
import org.jymf.service.ICompanyMonitorService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 监管管理操作实现类
 * @author cqs
 * @date   2014年05月09日
 */
@Service("companyMonitorService")
public class CompanyMonitorServiceImpl implements ICompanyMonitorService{
    @Autowired
    private CompanyMonitorMapper companyMonitorDao;
    @Autowired
    private CompanyMapper companyDao;

    @Override
    public PageView query(PageView pageView, CompanyMonitor companyMonitor) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", companyMonitor);
        
        List<CompanyMonitor> list = companyMonitorDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    public PageView monitorquery(PageView pageView, CompanyMonitor companyMonitor) {
    	try {
    		//值引用，不改变原对象值
    		CompanyMonitor comMonitor = new CompanyMonitor();
    		comMonitor.setLevel(companyMonitor.getLevel());
    		comMonitor.setAreaCode(companyMonitor.getAreaCode());
    		String areaCode = companyMonitor.getAreaCode();
    		Map<Object, Object> map = new HashMap<Object, Object>();
    		if(null != areaCode && !"".equals(areaCode)){
    			if("00".equals(areaCode.substring(3, 5))){
    				comMonitor.setAreaCode(areaCode.substring(0,3));
    			}else if("00".equals(areaCode.substring(5, 7))){
    				comMonitor.setAreaCode(areaCode.substring(0,5));
    			}
    		}
            map.put("paging", pageView);
            map.put("t", comMonitor);
            List<CompanyMonitor> list = companyMonitorDao.regionquery(map);
            pageView.setRecords(list);
            return pageView;
		} catch (Exception e) {
			return pageView;
		}
    }
    public PageView companyListquery(PageView pageView, BigDecimal monitorId) {
    	try {
    		Map<Object, Object> map = new HashMap<Object, Object>();
    		map.put("monitorId", monitorId);
    		map.put("paging", pageView);
            List<Company> listCompany = companyDao.selectByMonitorIdquery(map);
            pageView.setRecords(listCompany);
            return pageView;
		} catch (Exception e) {
			e.printStackTrace();
			return pageView;
		}
    }
    @Override
    public CompanyMonitor findById(long id) {
    	try {
    		return companyMonitorDao.selectByPrimaryKey(new BigDecimal(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    /**
     * 更新监管关系
     */
    @Override
    public void update(CompanyMonitor companyMonitor) {
    	try {
            companyMonitorDao.updateByPrimaryKeySelective(companyMonitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    /**
     * 添加监管关系
     */
    @Override
    public void add(CompanyMonitor companyMonitor) {
        companyMonitorDao.insert(companyMonitor);
    }
    
    /**
     * 查询最大的ID
     */
    @Override
    public int findMaxId(){
    	return companyMonitorDao.findMaxId();
    }
    
    /**
     * 通过用户名查找用户
     */
    public CompanyMonitor selectCompanyMonitor(String account){
    	return companyMonitorDao.selectByCompanyMonitor(account);
    }
    
    /**
     * 通过用户名查询密码
     */
    @Override
	public Boolean findByAccountAndPassword(String account,String pwd){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("account", account);
		map.put("pwd", pwd);
		return this.companyMonitorDao.selectByAccountAndPassword(map)!=null;
	}
    

    /**
     * 修改当前用户的密码
     */
    @Override
    public int updateByPrimaryKey(CompanyMonitor companyMonitor){
    	return companyMonitorDao.updateByPrimaryKey(companyMonitor);
    }
    
}
