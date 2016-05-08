package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.AcInfo;


/**
 * 窜货信息
 * @author cqs
 * @date   2014年9月26日
 */
public interface AcInfoMapper {  
    /**
     * 窜货信息详细查询
	 * @param map
	 *        查询区间
	 * @return
	 */
	List<AcInfo> queryAcInfo(Map<Object, Object> map);
	
    /**
     * 窜货统计，根据产品ID，销售区域，冲突区域进行统计
	 * @param map
	 *        查询区间
	 * @return
	 */
	List<AcInfo> queryAcCnt(Map<Object, Object> map);
}