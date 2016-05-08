package org.jymf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class InspectDevice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deviceId;

    private BigDecimal status;

    private BigDecimal authority;

    /**
     * 公司ID
     */
    private BigDecimal companyId;
    
    /**
     * 责任人
     */
    private String operator;
    
    public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getAuthority() {
        return authority;
    }

    public void setAuthority(BigDecimal authority) {
        this.authority = authority;
    }
}