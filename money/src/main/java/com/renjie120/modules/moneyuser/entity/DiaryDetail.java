package com.renjie120.modules.moneyuser.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class DiaryDetail extends DataEntity<DiaryDetail>{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String id;
  private String content;
  private String time;
  private String type;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
}
