package com.renjie120.common.annotation.cache;

import java.io.Serializable;

public class CacheBox implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7368753007637917612L;
	private String key;
	private String json;
	private Long expireTime;
	
	public Boolean valid(){
		return null!=this.getKey()&&null!=this.getExpireTime()
				&&null!=this.getJson();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
}
