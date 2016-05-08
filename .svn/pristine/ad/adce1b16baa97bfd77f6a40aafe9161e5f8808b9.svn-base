package org.jymf.service;


import java.math.BigDecimal;

import org.jymf.entity.InspectDevice;
import org.jymf.utils.PageView;

/**
 * 企业稽查终端操作接口
 * @author cqs
 * @date   2014年06月18日
 */
public interface IInspectDeviceService{
    /**
     * 分页处理
     * @param pageView
     * @param inspectDevice
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    PageView query(PageView pageView, InspectDevice inspectDevice, BigDecimal workMode);
    /**
     * 企业稽查终端信息
     * @param inspectDevice
     * @param workMode:根据追溯模式不同，会访问不同的DB
     */
    void update(InspectDevice inspectDevice, BigDecimal workMode);
    
    /**
     * 添加企业稽查终端
     * @param inspectDevice
     * @param workMode:根据追溯模式不同，会访问不同的DB
     */
    void add(InspectDevice inspectDevice, BigDecimal workMode);
    
    /**
     * 
     * @param inspectDevice
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
	InspectDevice findById(InspectDevice inspectDevice, BigDecimal workMode);
	
	
    /**
     * 判断公司中是否存在稽查设备
     * @param companyId
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    boolean isExistInCompany(BigDecimal companyId, BigDecimal workMode);
}
