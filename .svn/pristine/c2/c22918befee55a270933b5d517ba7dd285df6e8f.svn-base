package org.jymf.service;

import java.math.BigDecimal;

import org.jymf.entity.OutPutCount;
import org.jymf.utils.PageView;

/**
 * 企业标签息操作接口
 * @author lxg
 * @date   2014年8月1日
 */
public interface IOutPutCountService {
	/**
     * 统计一企业的窜货标签，以及地区销售多少标签
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */

	PageView queryOutPutCount(PageView pageView, OutPutCount outPutCount, BigDecimal workMode);
}
