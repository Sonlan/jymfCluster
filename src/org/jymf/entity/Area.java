package org.jymf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 区域
 * */
public class Area implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

    private String name;

    private int level;
    
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
}