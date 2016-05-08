package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.Certificate;

public interface CertificateMapper {
	
	List<Certificate> query(Map<String,Object> map);

	int add(Certificate certificate);

	Certificate findByCondition(Certificate certificate);

	void edit(Certificate certificate);

}