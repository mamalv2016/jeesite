package com.renjie120.common.solr.json.ext.select;


import com.alibaba.fastjson.JSONObject;
import com.renjie120.common.solr.json.base.SolrJSONUtils;

/**
 * 		<params class="object">
			<q type="string">*:*</q>
			<rows type="string">6</rows>
			<sort type="string">id desc</sort>
			<start type="string">1</start>
			<wt type="string">json</wt>
		</params>

 * @author Administrator
 *
 */
public class SolrParams {
	
	public SolrParams() {
		super();
	}
	private static String Q="q";   	
	private static String WT="wt";   	
	private static String ROWS="rows";   	
	private static String SORT="sort";   	
	private static String START="start";
	
	private String q;
	private int rows;
	private String sort;
	private int start;
	private String wt;
	private SolrCollapse collapse;
	
	public SolrParams(JSONObject params) {
		this.setQ(SolrJSONUtils.getString(params,Q));
		this.setWt(SolrJSONUtils.getString(params,WT));
		this.setRows(SolrJSONUtils.getInt(params,ROWS));
		this.setSort(SolrJSONUtils.getString(params,SORT));
		this.setStart(SolrJSONUtils.getInt(params,START));
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
	public String getWt() {
		return wt;
	}
	public void setWt(String wt) {
		this.wt = wt;
	}
	public SolrCollapse getCollapse() {
		return collapse;
	}
	public void setCollapse(SolrCollapse collapse) {
		this.collapse = collapse;
	}
	
}
