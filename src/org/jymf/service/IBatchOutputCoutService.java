package org.jymf.service;

import java.math.BigDecimal;

public interface IBatchOutputCoutService {

	/**
	 * workMode:根据追溯模式不同，会访问不同的DB
	 */
	void insertBatchOptputRun(BigDecimal id, Boolean isFirst,BigDecimal workMode);
}
