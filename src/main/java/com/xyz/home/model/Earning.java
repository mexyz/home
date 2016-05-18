package com.xyz.home.model;

import java.util.Date;

public class Earning {
    private Integer eId;
    private Integer caId;
    private Integer mId;
    private Float money;
    private Date createDate;
    private java.sql.Date earningDate;
    private String remark;
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public Integer getCaId() {
		return caId;
	}
	public void setCaId(Integer caId) {
		this.caId = caId;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public java.sql.Date getEarningDate() {
		return earningDate;
	}
	public void setEarningDate(java.sql.Date earningDate) {
		this.earningDate = earningDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}