package org.jymf.dao;

import java.util.List;
import java.util.Map;

import java.math.BigDecimal;
import org.jymf.entity.LabelIndex;

/**
 * 标签DB操作接口
 * @author lxg
 * @date   2014年7月14日
 */
public interface LabelIndexMapper {

	int insertIndex(LabelIndex record);

    LabelIndex selectByPrimaryKey(BigDecimal id);

    int updateIndex(LabelIndex record);

	List<LabelIndex> query(Map<Object, Object> map);
	
	LabelIndex findByLabelId(String labelId);
	
	int insertIndexS(LabelIndex record);
	
	int updateIndexS(LabelIndex record);
}