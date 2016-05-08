package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.AcInfo;
import org.jymf.utils.PageView;

public interface IAcInfoService {
	
	/**
     * 窜货信息详细查询
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
	PageView query(PageView pageView, AcInfo acInfo, BigDecimal workMode);

	/**
	 * 窜货统计信息查询
	 * @param pageView
	 * @param acInfo
	 * @param workMode:根据追溯模式不同，会访问不同的DB
	 * @return
	 */
	PageView queryCnt(PageView pageView, AcInfo acInfo,BigDecimal workMode);
}
