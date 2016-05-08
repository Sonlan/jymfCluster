package org.jymf.menu;

import java.math.BigDecimal;
import java.util.List;

public class Menu {

	private String id;
	private String title;
	private String link;
	private String css;
	/**
	 * 需要的最低权限
	 */
	private BigDecimal authority;

	/**
	 * 需要本菜单显示的公司ID集合
	 * null不限制
	 */
	private List<String> companyIds;
	
	private List<Menu> submenu;
	
	public List<String> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}
	
	public List<Menu> getSubmenu() {
		return submenu;
	}
	public void setSubmenu(List<Menu> submenu) {
		this.submenu = submenu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	
	public BigDecimal getAuthority() {
		return authority;
	}
	public void setAuthority(BigDecimal authority) {
		this.authority = authority;
	}
}
