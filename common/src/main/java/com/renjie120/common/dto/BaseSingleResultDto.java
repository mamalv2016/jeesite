package com.renjie120.common.dto;


import javax.xml.bind.annotation.XmlRootElement;


/**
 * 结果集泛型对象
 */
@XmlRootElement
public class BaseSingleResultDto<T> extends BaseReponseHandle {

	private static final long serialVersionUID = 1911878262015746803L;

	private T result;

	private String queryKey;
	
	/** 返回类型:true 同步 false 异步 */
	private Boolean isSync;

	public BaseSingleResultDto() {
		
	}
	
	public BaseSingleResultDto(T result) {
		this.result = result;
	}

	
	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	
	@Override
	public Long getId() {
		return null;
	}

	public Boolean getIsSync() {
		return isSync;
	}

	public void setIsSync(Boolean isSync) {
		this.isSync = isSync;
	}
	
	
}
