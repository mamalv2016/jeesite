package com.renjie120.common.solr.json.base.criteria;

/**
 * 
 * @author Administrator
 * 对应Solr Collapse
 */
public class SolrQCollapse {
	
	String field;
	int threshold=1;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public SolrQCollapse(String field, int threshold) {
		super();
		this.field = field;
		this.threshold = threshold;
	}
	public SolrQCollapse() {
		super();
	}
	
	
	
}
