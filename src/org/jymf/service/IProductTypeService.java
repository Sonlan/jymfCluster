package org.jymf.service;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.ProductType;
import org.jymf.utils.PageView;

/**
 * 
 * @author wfj
 * @date   2015年4月27日
 */
public interface IProductTypeService {

	 /**
     * 根据父ID查询该父ID下产品类型
     * @param companyId  公司ID
     * @param typeId 产品类型ID
     * @return 返回产品类型名称列表
     */
    PageView queryProductType(PageView pageView,BigDecimal companyId, BigDecimal typepId, BigDecimal workMode);
	
    /**
     * 查询产品类型名是否有重复
     * @param company 
     * @param productType 封装的产品类型包括TypeId和TypeName
     * @param type= “add”或“edit”
     */
    boolean isProductTypeRepeated(BigDecimal companyId, ProductType productType, String type, BigDecimal workMode);
	
    /**
     * 添加产品类型
     * @param productType 新的产品类型
     */
    boolean add(ProductType productType, BigDecimal companyId, BigDecimal workMode); 
    
    /**
     * 根据ID 查询产品类型
     */
    ProductType findProductTypeByTypeId(BigDecimal typeId, BigDecimal companyId, BigDecimal workMode);
    
    /**
     * 修改企业产品类型
     */
     boolean edit(ProductType productType, BigDecimal companyId,  BigDecimal workMode);
    
     /**
      * 获得产品类型json串
      */
     String getAllJsonType(BigDecimal companyId, BigDecimal workMode);
     
     /**
      * 查询全部产品类型
      */
     List<List<String>> getAllTypeList(BigDecimal companyId, BigDecimal workMode);
}
