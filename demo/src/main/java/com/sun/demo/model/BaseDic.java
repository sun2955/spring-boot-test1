package com.sun.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseDic {
    private String dicId;

    private String dicCode;

    private String dicName;

    private Integer dicPid;

    private Date dicCreateTime;

    private Date dicLastUpdatetime;

    private Integer dicStatus;

    private String dicSortCode;
    
    private String parentName;
    
    private List<BaseDic> diclist = new ArrayList<BaseDic>();
    
    

    public List<BaseDic> getDiclist() {
		return diclist;
	}

	public void setDiclist(List<BaseDic> diclist) {
		this.diclist = diclist;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public Integer getDicPid() {
        return dicPid;
    }

    public void setDicPid(Integer dicPid) {
        this.dicPid = dicPid;
    }

    public Date getDicCreateTime() {
        return dicCreateTime;
    }

    public void setDicCreateTime(Date dicCreateTime) {
        this.dicCreateTime = dicCreateTime;
    }

    public Date getDicLastUpdatetime() {
        return dicLastUpdatetime;
    }

    public void setDicLastUpdatetime(Date dicLastUpdatetime) {
        this.dicLastUpdatetime = dicLastUpdatetime;
    }

    public Integer getDicStatus() {
        return dicStatus;
    }

    public void setDicStatus(Integer dicStatus) {
        this.dicStatus = dicStatus;
    }

    public String getDicSortCode() {
        return dicSortCode;
    }

    public void setDicSortCode(String dicSortCode) {
        this.dicSortCode = dicSortCode == null ? null : dicSortCode.trim();
    }
}