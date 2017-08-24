/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneydetail.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 金额列表Entity
 * @author renjie120
 * @version 2017-04-22
 */
public class MoneyDetailInfo extends DataEntity<MoneyDetailInfo> {
	
	private static final long serialVersionUID = 1L;
	private String moneySno;		// 主键
	private Date moneyTime;		// 时间
	private String money;		// 金额
	private String moneyType;		// 类型
	private String moneyDesc;		// 描述
	private String shopcard;		// 信用卡
	private String useful;		// 有效
	private String booktype;		// 卡类型
	private String splitno;		// 是否拆分
	private String realMoney;		// 实际金额
	private String username;		// 账户名
	private Date beginMoneyTime;		// 开始 时间
	private Date endMoneyTime;		// 结束 时间
	private String beginMoney;		// 开始 金额
	private String endMoney;		// 结束 金额
	
	public MoneyDetailInfo() {
		super();
	}

	public MoneyDetailInfo(String id){
		super(id);
	}

	@Length(min=1, max=15, message="主键长度必须介于 1 和 15 之间")
	public String getMoneySno() {
		return moneySno;
	}

	public void setMoneySno(String moneySno) {
		this.moneySno = moneySno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getMoneyTime() {
		return moneyTime;
	}

	public void setMoneyTime(Date moneyTime) {
		this.moneyTime = moneyTime;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=1, max=10, message="类型长度必须介于 1 和 10 之间")
	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	
	@Length(min=0, max=600, message="描述长度必须介于 0 和 600 之间")
	public String getMoneyDesc() {
		return moneyDesc;
	}

	public void setMoneyDesc(String moneyDesc) {
		this.moneyDesc = moneyDesc;
	}
	
	public String getShopcard() {
		return shopcard;
	}

	public void setShopcard(String shopcard) {
		this.shopcard = shopcard;
	}
	
	@Length(min=1, max=1, message="有效长度必须介于 1 和 1 之间")
	public String getUseful() {
		return useful;
	}

	public void setUseful(String useful) {
		this.useful = useful;
	}
	
	@Length(min=0, max=2, message="卡类型长度必须介于 0 和 2 之间")
	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	
	public String getSplitno() {
		return splitno;
	}

	public void setSplitno(String splitno) {
		this.splitno = splitno;
	}
	
	public String getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(String realMoney) {
		this.realMoney = realMoney;
	}
	
	@Length(min=0, max=1000, message="账户名长度必须介于 0 和 1000 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getBeginMoneyTime() {
		return beginMoneyTime;
	}

	public void setBeginMoneyTime(Date beginMoneyTime) {
		this.beginMoneyTime = beginMoneyTime;
	}
	
	public Date getEndMoneyTime() {
		return endMoneyTime;
	}

	public void setEndMoneyTime(Date endMoneyTime) {
		this.endMoneyTime = endMoneyTime;
	}
		
	public String getBeginMoney() {
		return beginMoney;
	}

	public void setBeginMoney(String beginMoney) {
		this.beginMoney = beginMoney;
	}
	
	public String getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(String endMoney) {
		this.endMoney = endMoney;
	}
		
}