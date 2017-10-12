package com.renjie120.common.solr.json.base.criteria;

/**
 * 
 * @author Administrator
 * 对应Solr facet
 */
public class SolrQFacet {
	
	String field;
	int limit=-1;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	

	public SolrQFacet(String field, int limit) {
		super();
		this.field = field;
		this.limit = limit;
	}
	public SolrQFacet() {
		super();
	}
	
	
	
}
