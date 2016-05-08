package org.jymf.dao;

import java.util.List;
import java.util.Map;
import org.jymf.entity.OutPutCount;


/**
 * 窜货统计DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface OutPutCountMapper {  
    /**
     * 以出库信息为基础的统计信息
     * 根据产品ID，查询区间，按照省级销售区域分组，统计查询结果
	 * @param map
	 *        产品ID，查询区间
	 * @return
	 */
	List<OutPutCount> queryOutPutCount(Map<Object, Object> map);
}