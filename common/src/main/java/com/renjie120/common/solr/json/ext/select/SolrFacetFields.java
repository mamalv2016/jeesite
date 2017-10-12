package com.renjie120.common.solr.json.ext.select;

import com.alibaba.fastjson.JSONArray;
import com.renjie120.common.solr.json.base.SolrJSONUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 	["66666",3,"66667",2],
 * @author Administrator
 *
 */

public class SolrFacetFields {
	
	public SolrFacetFields() {
		super();
		this.fields=new ArrayList<SolrFacetField>();
	}

	private String fieldName;
	List<SolrFacetField> fields;
	
	public SolrFacetFields(String key, JSONArray jas) {
		
		this.setFieldName(key);
		this.fields=new ArrayList<SolrFacetField>();
		
		for(int i=0;i<jas.size();i+=2){
			SolrFacetField field=new SolrFacetField(SolrJSONUtils.getString(jas,i)
					,SolrJSONUtils.getInt(jas,i+1));
			fields.add(field);
		}
		
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<SolrFacetField> getFields() {
		return fields;
	}

	public void setFields(List<SolrFacetField> fields) {
		this.fields = fields;
	}
	
}
