package com.thinkgem.jeesite.modules.money.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @author Administrator
 *
 */
public class TallyType extends DataEntity<TallyType> {
	private static final long serialVersionUID = 1L;
	
	private String moneyTypeDesc;
	private String moneyType;
	private String parentCode;
	private String typeCode;
	private int orderId;

	public TallyType() {
		super();
	}
	
	public TallyType(String id){
		super(id);
	}
	
	public String getMoneyTypeDesc() {
		return moneyTypeDesc;
	}

	public void setMoneyTypeDesc(String moneyTypeDesc) {
		this.moneyTypeDesc = moneyTypeDesc;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
