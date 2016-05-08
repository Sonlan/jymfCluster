package org.jymf.entity;

import org.jymf.utils.Common;

public class Admin {
	
    private String id;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;
   
    private String name;

    private String telephone;

    private Integer role;

    
    /**
     * update by wfj at 2015.5.25 
     */
    private String mail;
    
    private Integer status;
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail  == null ? null : mail.trim();;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
   
    public boolean equals(Admin admin) {
	    	return Common.isEquals(this.getAccount(), admin.getAccount()) &&
	       		   Common.isEquals(this.getRole(),admin.getRole()) &&
	       		   Common.isEquals(this.getPassword(), admin.getPassword()) &&
	       		   Common.isEquals(this.getName(),admin.getName()) &&
	       		   Common.isEquals(this.getMail(),admin.getMail()) &&
	       		   Common.isEquals(this.getRole(),admin.getRole()) &&
	       		   Common.isEquals(this.getTelephone(),admin.getTelephone()) &&
	       		   Common.isEquals(this.getStatus(),admin.getStatus());
	} 
}