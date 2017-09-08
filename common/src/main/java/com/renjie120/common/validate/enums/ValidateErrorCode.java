package com.renjie120.common.validate.enums;

import com.thinkgem.jeesite.common.utils.StringUtils;

	public enum ValidateErrorCode
	{
		OK("1", "成功"),
		ERROR("-1", "失败"),
		SIGN_ERROR("-2", "签名错误"),
		PARAMS_ERROR("-5", "请求参数错误"),

		PASSENGER_NAME_FORBIDDEN("1020", "您输入的%s名字不合民航规定，请检查是否填写正确！");


		private String code;
		private String message;

		ValidateErrorCode(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

	public String getMessage() {
		return message;
	}

	public static boolean containsExceptionCode(ValidateErrorCode exceptionCode) {
		if (null == exceptionCode) {
			return false;
		}
		String code = exceptionCode.getCode();
		if (StringUtils.isEmpty(code)) {
			return false;
		}
		for (ValidateErrorCode otaCode : ValidateErrorCode.values()) {
			if (code.equals(otaCode.getCode())) {
				return true;
			}
		}
		return false;
	}
}
