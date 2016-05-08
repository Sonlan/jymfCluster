package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.ProductType;
/**
 * @author wfj 2015.4.28
 * 产品类型dao
 */
public interface ProductTypeMapper {

	/**
	 * 根据typePid查询该父元素所有子元素产品类型
	 */
	List<ProductType> query(Map<Object,Object> map);
	
	/**
	 * 查询指定公司下所有产品类型
	 */
	List<ProductType> findAll(BigDecimal companyId);
	
	/**
	 * 新增产品类型
	 */
	int insert(Map<Object,Object> map);
	 
	/**
	 * 按ID查询某公司下产品类型
	 */
	List<ProductType> findTypeById(Map<Object,Object> map);
	 
	/**
	 * 按产品名称查询当前公司下的所有产品类型
	 */
	List<ProductType> findTypeByName(Map<Object,Object> map);
	
	/**
	 * 更新产品类型信息
	 */
	int update(Map<Object,Object> map);
	
	
	/**
	 *查询某产品类型下的子类型个数
	 */
	int getChildCount(Map<Object,Object> map);
	
}
