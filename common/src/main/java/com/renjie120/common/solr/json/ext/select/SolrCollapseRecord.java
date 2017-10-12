package com.renjie120.common.solr.json.ext.select;


import com.alibaba.fastjson.JSONArray;
import com.renjie120.common.solr.json.base.SolrJSONUtils;

/**
 *	[\"collapseCount\",2,\"fieldValue\",\"66666\"]
 */
public class SolrCollapseRecord {

	public SolrCollapseRecord() {
		super();
	}
	private static int COLLAPSECOUNT=1;
	private static int FIELDVALUE=3;
	
	private String recordId;
	private String collapseCount;
	private String fieldValue;
	
	public SolrCollapseRecord(String id, JSONArray jas) {
		
		this.setRecordId(id);
		this.setCollapseCount(SolrJSONUtils.getString(jas,COLLAPSECOUNT));
		this.setFieldValue(SolrJSONUtils.getString(jas,FIELDVALUE));
		
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getCollapseCount() {
		return collapseCount;
	}
	public void setCollapseCount(String collapseCount) {
		this.collapseCount = collapseCount;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
}
