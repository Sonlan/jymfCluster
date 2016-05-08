package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.CompanyMapper;
import org.jymf.dao.EquipmentMapper;
import org.jymf.entity.Company;
import org.jymf.entity.Equipment;
import org.jymf.service.IEquipmentService;
import org.jymf.utils.Common;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 设备操作实现类
 * @author cqs
 * @date   2014年05月09日
 */
@Service("equipmentService")
public class EquipmentServiceImpl implements IEquipmentService{
    @Autowired
    private EquipmentMapper equipmentDao;

    @Autowired
    private CompanyMapper companyDao;
        
    @Override
    public PageView query(PageView pageView, Equipment equipment) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", equipment);
        
        List<Equipment> list = new ArrayList<Equipment>();
        if(Common.isNumeric(equipment.getId())){
        	list = equipmentDao.query(map);
        }        	
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Equipment findById(String id) {
    	Equipment equipment = equipmentDao.selectByPrimaryKey(id);
        return equipment;
    }

    @Override
    public void update(Equipment equipment) {
    	if(null != equipment.getWorkMode() && equipment.getWorkMode().compareTo(new BigDecimal(2))!=0){
    		equipment.setProductId(null);
    	}
        equipmentDao.updateByPrimaryKeySelective(equipment);
    }

    @Override
    public void add(Equipment equipment) {
    	if(equipment.getWorkMode().compareTo(new BigDecimal(2))!=0){
    		equipment.setProductId(null);
    	}
        equipmentDao.insert(equipment);
    }

	@Override
	public Company findCompanyById(long id) {
		return companyDao.selectByPrimaryKey(new BigDecimal(id));
	}

	@Override
	public PageView query(PageView pageView, Equipment equipment, long agentId) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", equipment);
        map.put("agentId", agentId);
        
        List<Equipment> list = equipmentDao.query(map);
        
        pageView.setRecords(list);
        return pageView;
	}

	@Override
	public boolean isExistInCompany(long companyId) {
		if(equipmentDao.selectCountBycompany(String.valueOf(companyId)).equals(new BigDecimal(0))){
			return false;
		}else{
			return true;
		}
	}
}
