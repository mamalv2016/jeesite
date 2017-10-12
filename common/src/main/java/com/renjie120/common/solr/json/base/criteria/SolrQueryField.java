package com.renjie120.common.solr.json.base.criteria;

import com.renjie120.common.solr.json.base.criteria.annotation.SolrField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
public class SolrQueryField {

	List<Object> values=new ArrayList();
	SolrField field;
	
	public SolrField getField() {
		return field;
	}
	public void setField(SolrField field) {
		this.field = field;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public SolrQueryField() {
		super();
	}
	
	public SolrQueryField(SolrField field,Object value) {
		super();
		this.field = field;
		this.addValue(value);
	}
	
	public String toSolrString(){
		return this.field.type().getSolrString(this);
	}

	public String toSolrString(Map<String,Collection<String>> dbs){
		if(null==dbs||0==dbs.size()){
			return toSolrString();
		}
		return this.field.type().getSolrString(this,dbs);
	}
	
	public void addValue(Object value){
		
		if(value instanceof Object[]){
			values.addAll(Arrays.asList((Object[])value));
		}else if( value instanceof Collection){
			values.addAll((Collection)value);
		}else{
			values.add(value);
		}
		
	}
	
}
