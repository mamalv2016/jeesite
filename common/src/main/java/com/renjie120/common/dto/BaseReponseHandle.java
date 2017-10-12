package com.renjie120.common.dto;


import java.io.Serializable;

/**
 * 响应返回对象，包括返回的状态和提示信息
 *
 */
@Deprecated
public class BaseReponseHandle implements Serializable,Dto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5758100152130213831L;

	/** 结果状态枚举 */
	private ResultStatus status = ResultStatus.SUCCESS;
	
	/** 错误信息*/
	private String errCode;
	 
	/** 返回信息 */
	private String message;

	public void setIsSuccess(boolean isSuccess) {}
	
	
	public boolean getIsSuccess() {
		if(status.name().equals(ResultStatus.SUCCESS.name())){
			return true;
		}
		return false;
	}
	

	public boolean getIsFail(){
		return !getIsSuccess();
	}
	
	public void setIsFail(boolean isFail){}
	
	
	@Override
	public Long getId() {
		//不做操作
		return null;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setErrorMessage(String message){
		status = ResultStatus.FAIL;
		this.message = message;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}
	
}
