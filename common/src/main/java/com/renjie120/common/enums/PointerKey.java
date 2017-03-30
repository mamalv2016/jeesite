package com.renjie120.common.enums;

public enum PointerKey {
	ALL("全部"), SINGLE("单接口"), UNKNOW("未知接口"), Test("测试");

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
