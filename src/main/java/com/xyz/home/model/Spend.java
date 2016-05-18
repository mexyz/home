package com.xyz.home.model;

import java.util.Date;

public class Spend {
    private Integer sId;
    private Integer caId;
    private Integer mId;
    private Float money;
    private Date createDate;
    private java.sql.Date spendDate;
    private String remark;
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
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
	public java.sql.Date getSpendDate() {
		return spendDate;
	}
	public void setSpendDate(java.sql.Date spendDate) {
		this.spendDate = spendDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}