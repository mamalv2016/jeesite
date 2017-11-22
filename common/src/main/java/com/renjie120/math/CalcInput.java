package com.renjie120.math;

public class CalcInput {
	// 本金
	private Double ben;
	// 年化收益(贷款收益)
	private Double fee;
	// 月份数
	private Integer month;
	// 年份数
	private Integer year;
	// 每月追加的金额
	private Double addon;
	/**
	 * 计算方式.
	 */
	private Integer calcMethod;

	public Double getBen() {
		return ben;
	}

	public void setBen(Double ben) {
		this.ben = ben;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getAddon() {
		return addon;
	}

	public void setAddon(Double addon) {
		this.addon = addon;
	}

	public Integer getCalcMethod() {
		return calcMethod;
	}

	public void setCalcMethod(Integer calcMethod) {
		this.calcMethod = calcMethod;
	}
}
