package org.jymf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jymf.entity.Company;

/**
 * 企业信息DB操作接口
 * @author cqs
 * @date   2014年5月8日
 */
public interface CompanyMapper{
	/**
	 * 分页
	 * 检索表 TBL_COMPANY
	 * @param map
	 * @return
	 */
	List<Company> query(Map<Object, Object> map);
	/**
	 * 根据monitorId 联合查询得到被监管公司列表
	 * @param map
	 * @return
	 */
	List<Company> selectByMonitorIdquery(Map<Object, Object> map);
	/**
	 * 检索出所有没被指定监管部门（map.monitorId）下的公司
	 * 操作表 TBL_COMPANY、TBL_COMPANY_MONITOR_RELATION
	 * 
	 * @param map
	 * @return
	 */
	List<Company> queryCompanyMonitor(Map<Object, Object> map);
	
	/**
	 * 代理商关系,信息查询,分页
	 * 检索表TBL_COMPANY、TBL_AGENT_MONITOR_RELATION
	 * @param map
	 * @return
	 */
	List<Company> queryAgentUpdate(Map<Object, Object> map);
	

	/**
	 * 查找被代理商（agentId）代理的公司
	 * 操作表 TBL_COMPANY、 TBL_AGENT_MONITOR_RELATION
	 * @param map
	 * @return
	 */
	List<Company> queryAgentCompany(Map<Object, Object> map);
	
	
	/**
	 * 根据监管ID,查找企业信息
	 * @param map
	 * @return
	 */
	List<Company> queryCompany1(Map<Object, Object> map);
    
    
	/**
	 * 寻找最大可用的ID
	 * @return
	 */
	BigDecimal selectMaxId();
    
	/**
	 * 查询将要创建的动态表
	 * @param map 
	 * @return 
	 *        存在，将返回表的名字
	 */
	List<String> selectCreateTable(Map<String, Object> map);
	
	/** 以下为，动态建一些表名以公司ID为后缀的辅助table表 */
    /**
     * 创建企业标签信息表
     * TBL_LABEL_INFO_${companyId}
     * @param map
     */
    void createLabel(Map<String, Object> map);
    
	/**
	 * 创建串货信息表
	 * TBL_AC_INFO_${companyId}
	 * @param map
	 */
    void createAcInfo(Map<String, Object> map);
    
    /**
     * 创建稽查设备终端表
     * TBL_INSPECT_DEVICE_${companyId}
     */
    void createDevice(Map<String, Object> map);
       
    /**
     * 创建企业产品类型表
     * TABLE TBL_PRODUCT_${companyId}
     */
    void createProduct(Map<String, Object> map);
       
    /**
     * 创建操作类型表
     * TABLE TBL_OPT_ACTIONS_${companyId}
     * @param map
     */
    void createOptActions(Map<String, Object> map);

    /**
     * 创建以标签为基础的统计表 
     */
    void createCount(Map<String, Object> map);
    
    /**
     * 创建以出库为基础的统计表 
     */
    void createOutPutCount(Map<String, Object> map);
    
    /**
     * 创建虚拟托盘表
     */
    void createVpallet(Map<String, Object> map);
    
    /**
     * 创建虚拟托盘关系表 
     */
    void createVpalletRelation(Map<String, Object> map);
    
    /**
     * 创建产品类型表
     */
    void createProductType(Map<String, Object> map);
    
    /**
     * 创建货单正品表
     */
	void createGoodReal(Map<String, Object> map);
	
	/**
     * 创建入库表
     */
	void createRuKu(Map<String, Object> map);
	
	/**
     * 创建出库表
     */
	void createChuKu(Map<String, Object> map);
	
	/**
     * 创建返库表
     */
	void createFanKu(Map<String, Object> map);
	
	/**
	 * 取出指定模式下所有公司的ID
	 * @param workMode
	 */
	List<BigDecimal> selectAllIdByWorkMode(BigDecimal workMode);
	
	int deleteByPrimaryKey(BigDecimal id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> findAll();
}