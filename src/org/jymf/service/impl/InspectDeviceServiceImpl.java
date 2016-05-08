package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.InspectDeviceMapper;
import org.jymf.entity.InspectDevice;
import org.jymf.service.IInspectDeviceService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 企业稽查终端操作实现类
 * @author cqs
 * @date   2014年06月18日
 */
@Service("inspectDeviceService")
public class InspectDeviceServiceImpl implements IInspectDeviceService{
    @Autowired
    private InspectDeviceMapper inspectDeviceDao;

    @Override
    public PageView query(PageView pageView, InspectDevice inspectDevice, BigDecimal workMode) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", inspectDevice);
        
        List<InspectDevice> list = inspectDeviceDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void update(InspectDevice inspectDevice, BigDecimal workMode) {
        inspectDeviceDao.updateByPrimaryKeySelective(inspectDevice);
    }

    @Override
    public void add(InspectDevice inspectDevice, BigDecimal workMode) {
    	inspectDevice.setStatus(new BigDecimal(1));
        inspectDeviceDao.insert(inspectDevice);
    }

	@Override
	public InspectDevice findById(InspectDevice inspectDevice, BigDecimal workMode) {
		return inspectDeviceDao.selectByPrimaryKey(inspectDevice);
	}

	@Override
	public boolean isExistInCompany(BigDecimal companyId, BigDecimal workMode) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("companyId", companyId);
        
        if(inspectDeviceDao.selectCount(map).equals(new BigDecimal(0))){
        	return false;
        }else{
		    return true;
		}
	}

}
