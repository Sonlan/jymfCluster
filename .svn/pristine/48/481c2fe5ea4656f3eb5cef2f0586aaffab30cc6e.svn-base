package org.jymf.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jymf.utils.Common;
import org.jymf.utils.ImgFile;

public class Product {
    private BigDecimal id;

    private BigDecimal type;

    private String name;

    private String description;

    private String producer;

    private String origin;

    private String url;

    /**
     * 每箱瓶数
     */
    private BigDecimal boxCnt;

    /**
     * 每托盘箱数
     */
    private BigDecimal palletCnt;
    
    private BigDecimal maxCountQueyr;

    private BigDecimal maxCountConsquery;
    
    private String numberId;
    
    private String cartype;
    
    /**
     * 查询结果描述
     */
    private String queryDes;

	/**
     * 公司ID
     */
    private BigDecimal companyId;

    /**
     * 产品图片的基本信息
     */
    private List<ImgFile> productImgs;
	    
	/**
	 * 产品参数
	 */
    private ProductPara productPara;
    
    /**
     * 创建时间
     */
    private Date createDate;

	/**
     * 更新时间
     */
    private Date updateDate;
    
    /**
     * 产品激活标签数
     */
    private BigDecimal labelCnt;
    
    /**
     * 消费查询数量
     */
    private BigDecimal consNum;
    
    /**
     * 产品描述图片是否存在
     */
    private Boolean isInfoImg;
    
    /**
     * 审核人
     */
    private String auditUser;
    
    /**
     * 审核日期
     */
    private Date auditDate;
    
    /**
     * 状态
     */
    private BigDecimal status;
    
	/**
	 * 开始时间
	 */
    private String startDate;
	
	/**
	 * 结束时间
	 */
    private String endDate;
    
    /**
     * 排序条件
     */
    private String orderBy;
    
    /**
     * 供货商
     */
    private String supplier;
    
    /**
     * 消费模式
     */
    private BigDecimal consumeType;
    private String consumeTypeName;
    
	public String getConsumeTypeName() {
		return consumeTypeName;
	}

	public void setConsumeTypeName(String consumeTypeName) {
		this.consumeTypeName = consumeTypeName;
	}

	public BigDecimal getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(BigDecimal consumeType) {
		this.consumeType = consumeType;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getBoxCnt() {
		return boxCnt;
	}

	public void setBoxCnt(BigDecimal boxCnt) {
		this.boxCnt = boxCnt;
	}

	public BigDecimal getPalletCnt() {
		return palletCnt;
	}

	public void setPalletCnt(BigDecimal palletCnt) {
		this.palletCnt = palletCnt;
	}

	public String getQueryDes() {
		return queryDes;
	}

	public void setQueryDes(String queryDes) {
		this.queryDes = queryDes;
	}

	public Boolean getIsInfoImg() {
		return isInfoImg;
	}

	public void setIsInfoImg(Boolean isInfoImg) {
		this.isInfoImg = isInfoImg;
	}

	public BigDecimal getConsNum() {
		return consNum;
	}

	public void setConsNum(BigDecimal consNum) {
		this.consNum = consNum;
	}

	public BigDecimal getLabelCnt() {
		return labelCnt == null ? new BigDecimal(0) : labelCnt;
	}

	public void setLabelCnt(BigDecimal labelCnt) {
		this.labelCnt = labelCnt == null ? new BigDecimal(0) : labelCnt;
	}

	public ProductPara getProductPara() {
		return productPara;
	}

	public void setProductPara(ProductPara productPara) {
		this.productPara = productPara;
	}

	public List<ImgFile> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(List<ImgFile> productImgs) {
		this.productImgs = productImgs;
	}

	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public BigDecimal getMaxCountQueyr() {
        return maxCountQueyr;
    }

    public void setMaxCountQueyr(BigDecimal maxCountQueyr) {
        this.maxCountQueyr = maxCountQueyr == null ? new BigDecimal(0) : maxCountQueyr;
    }

    public BigDecimal getMaxCountConsquery() {
        return maxCountConsquery;
    }

    public void setMaxCountConsquery(BigDecimal maxCountConsquery) {
        this.maxCountConsquery = maxCountConsquery == null ? new BigDecimal(0) : maxCountConsquery;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
    public String getNumberId() {
		return numberId;
	}
    
    /**
     * 对象比较，对DB中表象的元素和产品参数的信息做比较
     *  Ture: 相等
     * False: 不相等
     */
    public boolean equals(Product p)  
    {  
    	return Common.isEquals(this.getType(), p.getType()) &&
    		   Common.isEquals(this.getName(), p.getName()) &&
    		   Common.isEquals(this.getNumberId(), p.getNumberId()) &&
    		   Common.isEquals(this.getCartype(), p.getCartype()) &&
    		   Common.isEquals(this.getDescription(), p.getDescription()) &&
    		   Common.isEquals(this.getProducer(), p.getProducer()) &&
    		   Common.isEquals(this.getOrigin(), p.getOrigin()) &&
    		   Common.isEquals(this.getUrl(), p.getUrl()) &&
    		   Common.isEquals(this.getBoxCnt(), p.getBoxCnt()) &&
    		   Common.isEquals(this.getPalletCnt(), p.getPalletCnt()) &&
    		   Common.isEquals(this.getMaxCountQueyr(), p.getMaxCountQueyr()) &&
    		   Common.isEquals(this.getMaxCountConsquery(), p.getMaxCountConsquery()) &&
    		   Common.isEquals(this.getQueryDes(), p.getQueryDes()) &&
    		   Common.isEquals(this.getProductPara(), p.getProductPara()) &&
    		   Common.isEquals(this.getAuditUser(), p.getAuditUser()) &&
    		   Common.isEquals(this.getStatus(), p.getStatus());
    }  
}
