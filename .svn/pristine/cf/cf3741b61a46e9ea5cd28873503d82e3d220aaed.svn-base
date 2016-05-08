package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 企业内统计信息类
 * @author cqs
 * @date   2014年7月16日
 */
public class CompanyCount {
	
	/**
	 * 公司ID
	 */
	private BigDecimal companyId;

	/**
	 * 产品ID
	 */
	private BigDecimal productId;
	
	/**
	 * 产品名称
	 */
	private String productName;
      
	/**
	 * 激活数量
	 */
	private BigDecimal activeCnt;
	
    /**
     * 包标签数量
     */
	private BigDecimal packageCnt;
	
	/**
	 * 出库数量
	 */
	private BigDecimal outCnt;
	
	/**
	 * 入库数量
	 */
    private BigDecimal inCnt;
    
    /**
     * 销售数量
     */
    private BigDecimal salesCnt;
    
	/**
	 * 统计开始时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
	
	/**
	 * 统计结束时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;
    
    /**
	 * 今天
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String taday;
    
	public String getTaday() {
		return taday;
	}

	public void setTaday(String taday) {
		this.taday = taday;
	}

	public BigDecimal getActiveCnt() {
		return activeCnt;
	}

	public void setActiveCnt(BigDecimal activeCnt) {
		this.activeCnt = activeCnt;
	}

	public BigDecimal getOutCnt() {
		return outCnt;
	}

	public void setOutCnt(BigDecimal outCnt) {
		this.outCnt = outCnt;
	}

	public BigDecimal getInCnt() {
		return inCnt;
	}

	public void setInCnt(BigDecimal inCnt) {
		this.inCnt = inCnt;
	}

	public BigDecimal getSalesCnt() {
		return salesCnt;
	}

	public void setSalesCnt(BigDecimal salesCnt) {
		this.salesCnt = salesCnt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPackageCnt() {
		return packageCnt;
	}

	public void setPackageCnt(BigDecimal packageCnt) {
		this.packageCnt = packageCnt;
	}

	public BigDecimal getProductId() {
		return productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}
}
