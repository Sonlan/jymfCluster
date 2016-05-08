package org.jymf.dao;

import java.util.List;
import java.util.Map;

import org.jymf.entity.Document;
import org.jymf.entity.DocumentDetail;


/**
 * 合同管理_汽配模式
 * @author cqs
 * @date   2014年11月18日
 */
public interface DocumentMapper {
	/**
	 * 分页,查询信息
	 * @param map
	 * @return
	 */
	List<Document> query(Map<Object, Object> map);
	
	Document selectById(Map<Object, Object> map);
	/**
	 * 查询详细
	 * @param map
	 * @return
	 */
	List<DocumentDetail> selectDetail(Map<Object, Object> map);
}