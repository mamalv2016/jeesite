package com.renjie120.common.validate.exception;



import com.renjie120.common.validate.enums.ValidateErrorCode;

import java.io.Serializable;

public class ValidateException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3420279558457367260L;

    /** 状态枚举 */
    private ValidateErrorCode validateErrorCode;

    /** 错误消息 */
    private String errMessage;

    public ValidateException() {
        super();
    }

    public ValidateException(ValidateErrorCode validateErrorCode, String errMessage) {
        this.validateErrorCode = validateErrorCode;
        this.errMessage = errMessage;
    }

    public ValidateErrorCode getOtaCode() {
        return validateErrorCode;
    }

    public void setOtaCode(ValidateErrorCode validateErrorCode) {
        this.validateErrorCode = validateErrorCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
