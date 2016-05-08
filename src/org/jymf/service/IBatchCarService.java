package org.jymf.service;

import java.math.BigDecimal;

public interface IBatchCarService {
	
	/**
	 * 针对汽配模式，对包子关系无法绑定的业务
	 * 夜间统一再绑定一次
	 */
	void  updateBatchCarRun(BigDecimal companyId,BigDecimal workMode);
}
