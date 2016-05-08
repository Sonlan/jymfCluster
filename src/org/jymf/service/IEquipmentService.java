package org.jymf.service;

import org.jymf.entity.Company;
import org.jymf.entity.Equipment;
import org.jymf.utils.PageView;

/**
 * 设备操作接口
 * @author cqs
 * @date   2014年05月09日
 */
public interface IEquipmentService{
    /**
     * 通过主键获取记录
     * @param id
     * @return
     */
    Equipment findById(String id);
    /**
     * 分页处理
     * @param pageView
     * @param equipment
     * @return
     */
    PageView query(PageView pageView, Equipment equipment);
    
    /**
     * 查询属于某个指定代理商的设备信息，分页处理
     * @param pageView
     * @param equipment
     * @param agentId 
     * @return
     */
    PageView query(PageView pageView, Equipment equipment,long agentId);
    
    /**
     * 设备信息
     * @param equipment
     */
    void update(Equipment equipment);
    
    /**
     * 添加设备
     * @param equipment
     */
    void add(Equipment equipment);
    
    /**
     * 通过公司ID，获取公司信息
     * @param id
     * @return
     */
    Company findCompanyById(long id);
    
    /**
     * 判断指定公司中是否存在手持设备
     * @param companyId
     * @return
     */
    boolean isExistInCompany(long companyId);
}
