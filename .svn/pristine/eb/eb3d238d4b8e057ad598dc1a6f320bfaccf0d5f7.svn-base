package org.jymf.service;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.Company;
import org.jymf.entity.CompanyCount;
import org.jymf.utils.PageView;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业信息操作接口
 * @author cqs
 * @date   2014年5月7日
 */
public interface ICompanyService{
	/**
	 * 通过主键获取记录
	 * @param id
	 * @param isImg 是否需要图片
	 * @return
	 */
    Company findById(BigDecimal id,Boolean isImg);
    /**
     * 获取所有公司信息（每次获取一页）
     * @param pageView
     * @param company
     * @return
     */
    PageView query(PageView pageView, Company company);
    
    /**
     * 检索出所有没被指定监管部门（monitorId）下的公司
     * @param pageView
     * @param company
     * @param monitorId
     * @return
     */
    PageView queryCompanyMonitor(PageView pageView, Company company,long monitorId);
    
    /**
     * 检索出所有没被指定代理商（agentId）代理的公司
     * @param pageView
     * @param company
     * @return
     */
    PageView queryAgentCompany(PageView pageView, Company company,long monitorId);
    
    
    /**
     * 分页处理,监管ID查找企业信息
     * @param pageView
     * @param company
     * @return
     */
    PageView queryMonitorCompany(PageView pageView, Company company,long monitorId);
    
    
    /**
     * 分页处理,代理商关系
     * @param pageView
     * @param company
     * @return
     */
    PageView queryAgentUpdate(PageView pageView, Company company,long agentId);
    /**
     * 更新企业信息
     * @param company
     * @param cacheFolder 服务器上传图片的暂存绝对路径
     * @param imgFile4 红酒城模式上传的许可证
     */
    boolean update(Company company,List<MultipartFile> imgFile4,String cacheFolder);
	
	/**
	 * 添加企业信息
	 * @param company
	 * @param cacheFolder 服务器上传图片的暂存绝对路径
	 * @param imgFile4  红酒城模式下的卫生许可证图片
	 */
	void add(Company company,List<MultipartFile> imgFile4, String cacheFolder);
	
	/**
	 * 获取审核有效的企业
	 * @param id
	 * @return
	 */
	Company getAuditble(long id);
    
    /**
     * 统计一段时间内企业的包凭证使用信息，按照产品进行分类统计
     * @param workMode 根据追溯模式不同，会访问不同的DB
     * @return
     */
    PageView queryCountByCompany(PageView pageView, CompanyCount companyCount,BigDecimal workMode);
    
    /**
     * 创建企业用表
     * @param company
     * @param workMode 根据追溯模式不同，会访问不同的DB
     */
    void createTable(Company company,BigDecimal workMode);
    
    /**
     * 获取所有公司
     * @return
     */
    List<Company> findAllCompany();
	
    /**
     * 统计一个公司指定时间范围内标签的总的使用量
     * 代理商和监管部门使用
     * @param companyCount
     * @param workMode
     * @return
     */
    CompanyCount labelCountByCompany(CompanyCount companyCount,BigDecimal workMode);
    
	/**
	 * 取出指定模式下所有公司的ID
	 * @param workMode
	 */
	List<BigDecimal> selectAllIdByWorkMode(int workMode);
}
