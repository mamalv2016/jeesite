package com.renjie120.common.solr.json.base.criteria;

import com.renjie120.common.solr.json.base.criteria.enums.SolrOrderTYPE;

import java.util.Collection;
import java.util.Map;


public class SolrOrderField {

	String field;
	SolrOrderTYPE type;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public SolrOrderTYPE getType() {
		return type;
	}
	public void setType(SolrOrderTYPE type) {
		this.type = type;
	}
	public SolrOrderField(String field, SolrOrderTYPE type) {
		super();
		this.field = field;
		this.type = type;
	}
	public String toSolrString() {
		return type.getSolrString(field);
	}
	public String toSolrString(Map<String,Collection<String>> dbs) {
		if(null==dbs||0==dbs.size()){
			return toSolrString() ;
		}
		return type.getSolrString(field,dbs);
	}
	public SolrOrderField() {
		super();
	}
	
	
	
}
