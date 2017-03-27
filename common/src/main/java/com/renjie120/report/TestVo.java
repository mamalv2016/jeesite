package com.renjie120.report;

@ReportTable(dbTable = "money_t")
public class TestVo {
	@ReportGroup(desc = "年份", dbColumn = "year")
	private String year;

	@ReportSum(desc = "金额", dbColumn = "money")
	private double money;

	@ReportCount(desc = "用户id", dbColumn = "user_id" )
	private int userId;

	public int getUserId() {
		return userId;
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
