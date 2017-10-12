package com.renjie120.common.dto;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

public class Entity implements Serializable,Dto{
	
	private static final long serialVersionUID = -6624519953583114792L;
	
	private Long id;
	private String uuid;
	private Date createTime=new Date();
	private Date updateTime=new Date();
	private PersistenceType ptype=PersistenceType.DEFAULT;
	
	
	@XmlTransient
	public PersistenceType getPtype() {
		return ptype;
	}

	public void setPtype(PersistenceType ptype) {
		this.ptype = ptype;
	}

	
	public String getSequence(){
		return null;
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
