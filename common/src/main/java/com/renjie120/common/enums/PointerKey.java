package com.renjie120.common.enums;

/**
 * 对于日志注入点的功能说明枚举.
 * @author Administrator
 *
 */
public enum PointerKey {
	ALL("全部"), SINGLE("单接口"), UNKNOW("未知接口"), Test("测试"), MONEY_TALLYTYPE(
			"金额类型");

	private String name;

	private PointerKey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
