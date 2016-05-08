package org.jymf.service;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.Certificate;
import org.jymf.entity.CompanyUser;
import org.jymf.utils.PageView;
import org.springframework.web.multipart.MultipartFile;

public interface ICertificateService {
	
	PageView query(PageView pageView, Certificate cer,CompanyUser companyUser, BigDecimal workMode);

	void add(Certificate certificate, List<MultipartFile> imgFile1,
			List<MultipartFile> imgFile2, CompanyUser companyUser,
			BigDecimal workMode);

	Certificate findById(Integer id, CompanyUser companyUser, BigDecimal bigDecimal);

	void edit(Certificate certificate, List<MultipartFile> imgFile1, List<MultipartFile> imgFile2, 
			CompanyUser companyUser,BigDecimal workMode);

}
