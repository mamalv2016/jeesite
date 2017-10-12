package com.renjie120.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 结果集泛型对象
 */
@XmlRootElement
public class BaseResultDto<T> extends BaseReponseHandle {

	private static final long serialVersionUID = 1911878262015746803L;

	private List<T> results = new ArrayList<T>();

	private Pagination pagination = new Pagination();

	private String queryKey;
	
	/** 返回类型:true 同步 false 异步 */
	private Boolean isSync;

	public BaseResultDto() {
		pagination = new Pagination();
	}
	
	public BaseResultDto(List<T> results) {
		pagination = new Pagination();
		this.results = results;
	}

	public BaseResultDto(Pagination pagination, List<T> results) {
		this.pagination = pagination;
		this.results = results;

	}
	
	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	@XmlElement
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	@XmlElement
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public int getPage() {
		return pagination.getPage();
	}
	
	public int getRows() {
		return pagination.getRows();
	}
	
	public int getRecords() {
		return pagination.getRecords();
	}
	
	public int getTotal() {
		return pagination.getTotal();
	}
	
	public String getSidx() {
		return pagination.getSidx();
	}
	
	public String getSord() {
		return pagination.getSord();
	}
	
	public Boolean isHasResults() {
		return getResults() != null ? true : false;
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
