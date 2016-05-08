package org.jymf.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jymf.utils.Common;
import org.jymf.utils.ImgFile;
import org.springframework.format.annotation.DateTimeFormat;

public class Company {
	/**
	 * 公司标识
	 */
    private BigDecimal id;
	/**
	 * 公司名称
	 */
    private String name;
   
    /**
     * 公司图片的基本信息
     */
    private List<ImgFile> comImgs;
    
    /**
     * 
     * 公司描述
     */
    private Depict depict;

    /**
     * 投诉电话
     */
    private String tel;
    
    /**
     * 代理商名称
     */
    private String agentName;
    
	private String licenseNum;

    private String address;

    private String person;

    private BigDecimal capital;

    private String scope;

    private Date beginTime;

    private BigDecimal duration;

    private String tname;

    private String taxNum;

    private BigDecimal flag;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
        
	private BigDecimal auditValid;
    
    private String auditResult;

    private BigDecimal workMode;

    private BigDecimal status;

    private String url;
    
    private BigDecimal monitorId;
    
    private BigDecimal agentId;
    
	/**
	 * 激活数量
	 */
	private BigDecimal activeCnt;

	/**
     * 包标签数量
     */
	private BigDecimal packageCnt;
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
    * 维修质量评分
    */
   private double rate1;
   
   /**
    * 维修配件评分
    */
   private double rate2;
   
   /**
    * 评分人数
    */
   private long rateCnt;
   
   /**
    * 总评分
    */
   private double rate;
   
  /**
    * 许可证URL
    */
   private String xkzUrl;
   
   public String getXkzUrl() {
	   return xkzUrl;
   }
	
   public void setXkzUrl(String xkzUrl) {
		this.xkzUrl = xkzUrl;
   }

   public double getRate1() {
	   return rate1;
   }

	public void setRate1(double rate1) {
		this.rate1 = rate1;
	}
	
	public double getRate2() {
		return rate2;
	}
	
	public void setRate2(double rate2) {
		this.rate2 = rate2;
	}
	
    public double getRate() {
	   return rate;
    }

    public void setRate(double rate) {
	   this.rate = rate;
    }
	   
	public long getRateCnt() {
		return rateCnt;
	}
	
	public void setRateCnt(long rateCnt) {
		this.rateCnt = rateCnt;
	}

	public BigDecimal getAgentId() {
		return agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public BigDecimal getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(BigDecimal monitorId) {
		this.monitorId = monitorId;
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

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum == null ? null : licenseNum.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum == null ? null : taxNum.trim();
    }

    public BigDecimal getFlag() {
        return flag;
    }

    public void setFlag(BigDecimal flag) {
        this.flag = flag;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public BigDecimal getAuditValid() {
        return auditValid;
    }

    public void setAuditValid(BigDecimal auditValid) {
        this.auditValid = auditValid;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult == null ? null : auditResult.trim();
    }

    public BigDecimal getWorkMode() {
        return workMode;
    }

    public void setWorkMode(BigDecimal workMode) {
        this.workMode = workMode;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }
	public List<ImgFile> getComImgs() {
		return comImgs;
	}

	public void setComImgs(List<ImgFile> comImgs) {
		this.comImgs = comImgs;
	}
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url== null ? null : url.trim();
	}

	public Depict getDepict() {
		return depict;
	}

	public void setDepict(Depict depict) {
		this.depict = depict;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel== null ? null : tel.trim();
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName == null ? null : agentName.trim();
	}
	
	
    public BigDecimal getActiveCnt() {
		return activeCnt;
	}

	public void setActiveCnt(BigDecimal activeCnt) {
		this.activeCnt =  activeCnt;
	}

	public BigDecimal getPackageCnt() {
		return packageCnt;
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
	
	public void setPackageCnt(BigDecimal packageCnt) {
		this.packageCnt =  packageCnt;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
    /**
     * 对象比较，对DB中表象的元素和产品参数的信息做比较
     *  Ture: 相等
     * False: 不相等
     */
    public boolean equals(Company compay) {
    	return Common.isEquals(this.getName(), compay.getName()) &&
       		   Common.isEquals(this.getDepict(), compay.getDepict()) &&
       		   Common.isEquals(this.getLicenseNum(), compay.getLicenseNum()) &&
       		   Common.isEquals(this.getAddress(), compay.getAddress()) &&
       		   Common.isEquals(this.getPerson(), compay.getPerson()) &&
       		   Common.isEquals(this.getCapital(), compay.getCapital()) &&
       		   Common.isEquals(this.getScope(), compay.getScope()) &&
       		   Common.isEquals(this.getDuration(), compay.getDuration()) &&
       		   Common.isEquals(this.getTname(), compay.getTname()) &&
       		   Common.isEquals(this.getTaxNum(), compay.getTaxNum()) &&
       		   Common.isEquals(this.getWorkMode(), compay.getWorkMode()) &&
       		   Common.isEquals(this.getUrl(), compay.getUrl());
    } 
}