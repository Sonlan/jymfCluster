package org.jymf.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jymf.menu.Menu;

public class CompanyUser {
    private String id;

    private String password;

    private BigDecimal companyId;

    private String companyUrl;

	private Date createDate;

    private BigDecimal authority;

    private BigDecimal status;

    /**
     * 检索用ID
     */
    private String searchId;

	/**
     * 公司模式
     *  0: 产品追溯模式 
		1: 厂家追溯模式
		2: 商城追溯模式
		3: 汽配追溯模式
     */
    private BigDecimal workMode;
    
    /**
     * 公司名称
     */
    private String companyName;
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	private List<Menu> menus;
    
    public BigDecimal getWorkMode() {
		return workMode;
	}

	public void setWorkMode(BigDecimal workMode) {
		this.workMode = workMode;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getCompanyId() {
        return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
        this.companyId = companyId;
    }

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getAuthority() {
        return authority;
    }

    public void setAuthority(BigDecimal authority) {
        this.authority = authority;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }
    public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	    
    public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

}