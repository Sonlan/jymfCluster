package org.jymf.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Logs {
    private BigDecimal id;

    private String userId;

    private String event;

	private String description;

    private Date createTime;
    
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
     * 公司ID
     */
    private BigDecimal companyId;
    
    /**
     * 公司名称
     */
    private String companyName;
    
	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public BigDecimal getId() {
		return id;
	}
    
    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}