package com.renjie120.common.validate.exception;


import com.lvmama.lvtraffic.common.validate.enums.LvtrafficCode;

import java.io.Serializable;

public class ValidateException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3420279558457367260L;

    /** 状态枚举 */
    private LvtrafficCode lvtrafficCode;

    /** 错误消息 */
    private String errMessage;

    public ValidateException() {
        super();
    }

    public ValidateException(LvtrafficCode lvtrafficCode, String errMessage) {
        this.lvtrafficCode = lvtrafficCode;
        this.errMessage = errMessage;
    }

    public LvtrafficCode getOtaCode() {
        return lvtrafficCode;
    }

    public void setOtaCode(LvtrafficCode lvtrafficCode) {
        this.lvtrafficCode = lvtrafficCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
