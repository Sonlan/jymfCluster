package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.dao.AreaMapper;
import org.jymf.entity.Area;
import org.jymf.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AreaServiceImpl implements IAreaService {
	@Autowired
	private AreaMapper areaDao;
	@Override
	public List<String> areaNameQuery() {
		try {
			return areaDao.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Area areaQueryByName(String name) {
		try {
			return areaDao.queryAreaByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Area areaQueryById(BigDecimal id,BigDecimal workMode) {
		try {
			return areaDao.queryAreaById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
