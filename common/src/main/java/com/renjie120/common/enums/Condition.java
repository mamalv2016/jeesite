package com.renjie120.common.enums;

/**
 * sql逻辑判断条件
 * 
 * @author Administrator
 *
 */
public enum Condition {
	GREATER("大于"), LESS("小于"), EQUAL("等于"), NOT_EQUAL("不等于"), GREATER_EQUAL(
			"大于等于"), LESS_EQUAL("小于等于"), LIKE("相似"), NOT_LIKE("不相似"), CONTAIN(
			"包含"), NOT_CONTAIN("不包含");

	private String name;

	private Condition(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
