/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneyuser.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 理财系统用户Entity
 * @author renjie120
 * @version 2017-04-22
 */
public class MoneyUserInfo extends DataEntity<MoneyUserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 用户名
	private String pass;		// 密码
	private String loginid;		// 登录id
	private String orgid;		// 组织id
	private String email;		// 邮箱
	private String phone;		// 电话
	private String mobile;		// mobile
	private String usertype;		// 用户类型
	private String address;		// 地址
	private String orderid;		// 排序号
	
	public MoneyUserInfo() {
		super();
	}

	public MoneyUserInfo(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="用户名长度必须介于 0 和 1000 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=1000, message="密码长度必须介于 0 和 1000 之间")
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Length(min=0, max=1000, message="登录id长度必须介于 0 和 1000 之间")
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	@Length(min=0, max=10, message="组织id长度必须介于 0 和 10 之间")
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	@Length(min=0, max=1000, message="邮箱长度必须介于 0 和 1000 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=1000, message="电话长度必须介于 0 和 1000 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=1000, message="mobile长度必须介于 0 和 1000 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=1000, message="用户类型长度必须介于 0 和 1000 之间")
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Length(min=0, max=1000, message="地址长度必须介于 0 和 1000 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=10, message="排序号长度必须介于 0 和 10 之间")
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
}