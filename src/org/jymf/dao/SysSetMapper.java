package org.jymf.dao;

import java.util.Map;

public interface SysSetMapper{

	String selectByName(String name);
    
	void updateValue(Map<String, Object> map);
	
	void insert(Map<String, Object> map);
}