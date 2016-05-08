package org.jymf.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class CompanyMonitor {
    private BigDecimal id;

    private String name;

    private String pwd;

    private String tel;

    private BigDecimal status;

    private String account;

    /**
     * 监管者类型
     */
    private BigDecimal monitorMode;
    
	/**
     * 被监管的企业列表
     */
    private List<Cmr> crms;
    
    /**
     * 省的类别
     */
    private int proType;
    
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="YYYY-MM-DD hh:mm:ss")
    private String createTime;
    /**
     * 最近更新时间
     */
    @DateTimeFormat(pattern="YYYY-MM-DD hh:mm:ss")
    private String updateTime;
    //地区编码
    private String areaCode;
    //地区分级
    private int level;
    
    
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getProType() {
		return proType;
	}

	public void setProType(int proType) {
		this.proType = proType;
	}

	public List<Cmr> getCrms() {
		return crms;
	}

	public void setCrms(List<Cmr> crms) {
		this.crms = crms;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getMonitorMode() {
		return monitorMode;
	}

	public void setMonitorMode(BigDecimal monitorMode) {
		this.monitorMode = monitorMode;
	}

	public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
    
}