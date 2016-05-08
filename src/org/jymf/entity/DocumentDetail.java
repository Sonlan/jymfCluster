package org.jymf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 合同明细
 * @author cqs
 * @date   2014年11月18日
 */
public class DocumentDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 追溯码
     */
    private BigDecimal id;
    
    /**
     * 产品名称
     */
    private String productName;
    
    /** 
     * 适用车型
     */
    private String carType;
    
    /**
     * 生产商
     */
    private String producer;
    
    public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
}
