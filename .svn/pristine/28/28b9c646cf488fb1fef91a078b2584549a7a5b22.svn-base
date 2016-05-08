package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.CompanyUser;

public interface CompanyUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompanyUser record);

    int insertSelective(CompanyUser record);

    CompanyUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyUser record);

    int updateByPrimaryKey(CompanyUser record);

	List<CompanyUser> query(Map<Object, Object> map);
	
	CompanyUser findUserByIdAndPwd (CompanyUser record);
}