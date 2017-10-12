package com.renjie120.common.dto;

public enum ResultStatus
{
	
	DEFAULT("初始状态"),
	INVALID("无效"),
	APPLY("发起"),
	SUCCESS("成功"),
	TIMEOUT("超时"),
	FAIL("失败");
	

	private String cnName;

	public String getCnName() {
		return cnName;
	}

	private ResultStatus(String cnName) {
		this.cnName = cnName;
	}
	
}
