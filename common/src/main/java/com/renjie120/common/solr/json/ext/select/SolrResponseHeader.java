package com.renjie120.common.solr.json.ext.select;


import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <responseHeader class="object">
		<QTime type="number">78</QTime>
		<params class="object">
			<q type="string">*:*</q>
			<rows type="string">6</rows>
			<sort type="string">id desc</sort>
			<start type="string">1</start>
			<wt type="string">json</wt>
		</params>
		<status type="number">0</status>
	</responseHeader>
 * @author Administrator
 *
 */

public class SolrResponseHeader {

	public SolrResponseHeader() {
		super();
	}
	private static String QTIME="QTime";
	private static String STATUS="status";
	private static String PARAMS="params";
	
	private static String RESPONSE_HEADER="responseHeader";
	
	private Long QTime;
	private Long status;
	private SolrParams params;
	
	public SolrResponseHeader(JSONObject obj) {
		
		if(null==obj.get(RESPONSE_HEADER)){
			return;
		}
		
		JSONObject responseHeader=obj.getJSONObject(RESPONSE_HEADER);
		
		this.setQTime(responseHeader.getLong(QTIME));
		this.setStatus(responseHeader.getLong(STATUS));

		JSONObject params=responseHeader.getJSONObject(PARAMS);
		
		SolrParams sparams= new SolrParams(params);
		this.setParams(sparams);		
		
	}
	public Long getQTime() {
		return QTime;
	}
	public void setQTime(Long qTime) {
		QTime = qTime;
	}
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public SolrParams getParams() {
		return params;
	}
	public void setParams(SolrParams params) {
		this.params = params;
	}
	
	
	
	
}
