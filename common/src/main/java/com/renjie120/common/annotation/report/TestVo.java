package com.renjie120.common.annotation.report;

import java.util.List;

import com.renjie120.common.enums.Condition;

@ReportTable(dbTable = "money_t")
public class TestVo {
	@Group(desc = "年份", dbColumn = "year", order = 3)
	private String year;

	@Group(desc = "金额类型", dbColumn = "type", order = 2)
	private String moneyType;

	@Sum(desc = "金额", dbColumn = "money")
	private double money;

	@Where(dbColumn = "money", condition = Condition.GREATER_EQUAL)
	private double smallMoney;

	@Where(dbColumn = "year", condition = Condition.CONTAIN)
	private List<String> years;

	@Where(dbColumn = "money", condition = Condition.LESS_EQUAL)
	private double bigMoney;

	@Where(dbColumn = "user_name", condition = Condition.LIKE)
	private String likeName;

	@Count(desc = "用户id", dbColumn = "user_id")
	private int userId;

	@Where(condition = Condition.NOT_CONTAIN, dbColumn = "user_id")
	private int[] users;

	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public int[] getUsers() {
		return users;
	}

	public void setUsers(int[] users) {
		this.users = users;
	}

	public int getUserId() {
		return userId;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public double getSmallMoney() {
		return smallMoney;
	}

	public void setSmallMoney(double smallMoney) {
		this.smallMoney = smallMoney;
	}

	public double getBigMoney() {
		return bigMoney;
	}

	public void setBigMoney(double bigMoney) {
		this.bigMoney = bigMoney;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
