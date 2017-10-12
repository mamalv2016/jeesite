package com.renjie120.common.solr.json.ext.select;



/**
 * 	"name":["66666",3,"66667",2],
	"title":["2",5,"3",5,"42",5,"sdd中文",5,"sds中文",5,"sfsd",5,"中文",5]},
 * @author Administrator
 *
 */

public class SolrFacetField {
	
	public SolrFacetField() {
		super();
	}

	private String fieldValue;
	private String fieldDesc;
	private int count;
	
	public SolrFacetField(String fieldValue, int count) {
		this.setFieldValue(fieldValue);
		this.setCount(count);
	}
	
	public SolrFacetField(String fieldValue, String fieldDesc, int count) {
		super();
		this.fieldValue = fieldValue;
		this.fieldDesc = fieldDesc;
		this.count = count;
	}



	public String getFieldValue() {
		return fieldValue;
	}
	
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	
	
	
}
