package com.renjie120.common.solr.json.ext.select;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renjie120.common.solr.json.base.SolrJSONUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * "collapse_counts\":[\"field\",\"name\",
	\"results\",[
			\"11\",[\"collapseCount\",2,\"fieldValue\",\"66666\"],
			\"15\",[\"collapseCount\",1,\"fieldValue\",\"66667\"]
	]	 ]
	
	["field","name","results",["11",["collapseCount",2,"fieldValue","66666"],"15",["collapseCount",1,"fieldValue","66667"]]]

 * @author Administrator
 *
 */
public class SolrCollapse {
	
	private static String COLLAPSE_COUNTS="collapse_counts";
	
	private static int FIELD=1;
	private static int COLLAPSE_RESULTS=3;
	
	private String field;
	private List<SolrCollapseRecord> results;
	
	public SolrCollapse(JSONObject obj) {
		
		if(null==obj.get(COLLAPSE_COUNTS)){
			return;
		}
		
		JSONArray collapses=obj.getJSONArray(COLLAPSE_COUNTS);
		
		this.setField(SolrJSONUtils.getString(collapses,FIELD));
		
		JSONArray results=collapses.getJSONArray(COLLAPSE_RESULTS);
		List<SolrCollapseRecord> records=new ArrayList();

		for(int i=0;i<results.size();i+=2){
			
			String id= SolrJSONUtils.getString(results,i);
			JSONArray jas=(JSONArray)results.get(i+1);
			SolrCollapseRecord record = new SolrCollapseRecord(id,jas);
			records.add(record);
		}
		this.setResults(records);
		
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public List<SolrCollapseRecord> getResults() {
		return results;
	}
	public void setResults(List<SolrCollapseRecord> results) {
		this.results = results;
	}
	public SolrCollapse() {
		super();
	}
	
	
}
