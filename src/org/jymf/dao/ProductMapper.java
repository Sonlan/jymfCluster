package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.Product;

public interface ProductMapper {

	int deleteByPrimaryKey(Map<Object, Object> map);

    int insert(Product product);

    int insertSelective(Product product);

    Product selectByPrimaryKey(Map<Object, Object> map);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);

    /**
     * 根据追溯模式不同，会访问不同的DB
     * @param map
     * @return
     */
	List<Product> query(Map<Object, Object> map);
	/*
	 * 查询商户单选画面
	 */
	List<Product> queryProduct(Map<Object, Object> map);
	
	BigDecimal selectMaxId(Map<String, Object> map);
	
	List<BigDecimal> selectAllId(Map<String, Object> map);
	
	/**
	 * 查询全部产品信息
	 * update by wfj 2015.5.6
	 */
	List<Product> findAllProduct(BigDecimal companyId);
	
	/**
	 * 汽配模式销售统计
	 * @param map
	 * @return
	 */
	List<Product> querySales(Map<Object, Object> map);
}
