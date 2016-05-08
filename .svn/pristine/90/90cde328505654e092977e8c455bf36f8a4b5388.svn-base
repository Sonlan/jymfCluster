package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 以出库信息为基础的统计信息
 */
public class OutPutCount {
	/**
	 * 公司ID
	 */
	private BigDecimal companyId;

	/**
	 * 被选择的产品ID
	 */
	private BigDecimal productId;
	
	/**
	 * 产品名称
	 */
	private String productName;
	   
	/**
     * 查询总次数（包括：普通查询和消费查询）
     */
    private BigDecimal querySum;
    /**
     * 消费验证查询产品总数
     */
    private BigDecimal consNum;
    /**
     * 窜货总量
     */
    private BigDecimal acNum;
    
	/**
     * 出货总量
     */
    private BigDecimal outPutSum;
    /**
     * 销售区域（省级）
     */
    private String salesName;
    
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
    
    
	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getProductId() {
		return productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getQuerySum() {
		return querySum;
	}

	public void setQuerySum(BigDecimal querySum) {
		this.querySum = querySum;
	}

	public BigDecimal getConsNum() {
		return consNum;
	}

	public void setConsNum(BigDecimal consNum) {
		this.consNum = consNum;
	}

	public BigDecimal getAcNum() {
		return acNum;
	}

	public void setAcNum(BigDecimal acNum) {
		this.acNum = acNum;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
    public BigDecimal getOutPutSum() {
		return outPutSum;
	}

	public void setOutPutSum(BigDecimal outPutSum) {
		this.outPutSum = outPutSum;
	}
}