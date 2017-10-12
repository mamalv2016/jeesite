package com.renjie120.common.solr.json.ext.select;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.Map.Entry;

/**
"facet_counts":{
	"facet_queries":{},
	"facet_fields":{
			"name":["66666",3,"66667",2],
			"title":["2",5,"3",5,"42",5,"sdd中文",5,"sds中文",5,"sfsd",5,"中文",5]
	},
	"facet_dates":{},
	"facet_ranges":{}
 }
 * 
 * @author Administrator
 *
 */

public class SolrFacet {
	
	public SolrFacet() {
		super();
	}

	private static String FACET_COUNTS="facet_counts";
	private static String FACET_FIELDS = "facet_fields";
	private static String FACET_QUERIES = "facet_queries";
	private List<SolrFacetFields> fields;

	public SolrFacet(JSONObject obj) {
		
		if(null==obj.get(FACET_COUNTS)){
			return;
		}
		
		JSONObject facet=obj.getJSONObject(FACET_COUNTS);
		
		if(null==facet.get(FACET_FIELDS)){
			return;
		}
		
		JSONObject jso=facet.getJSONObject(FACET_FIELDS);
		
		Set<Entry<String,Object>> es=jso.entrySet();
		fields=new ArrayList();
		
		for(Entry<String,Object> e:es){
			
			String key=e.getKey();
			
			JSONArray jas=(JSONArray)e.getValue();
			SolrFacetFields facetField=new SolrFacetFields(key,jas);
			
			fields.add(facetField);
			
		}
		
		jso = facet.getJSONObject(FACET_QUERIES);

		Set<Entry<String,Object>> ei = jso.entrySet();

		Map<String, SolrFacetFields> facetFieldMap = new HashMap<String, SolrFacetFields>();
		for (Entry<String, Object> e : ei) {

			String query = e.getKey();

			String[] split = StringUtils.split(query, ":");
			String key = split[0];
			SolrFacetFields solrFacetFields = facetFieldMap.get(key);
			if (solrFacetFields == null) {
				solrFacetFields = new SolrFacetFields();
				solrFacetFields.setFieldName(key);
				facetFieldMap.put(key, solrFacetFields);
			}

			solrFacetFields.getFields().add(new SolrFacetField(split[1], (Integer)e.getValue()));

		}
		fields.addAll(facetFieldMap.values());
	}

	public List<SolrFacetFields> getFields() {
		return fields;
	}

	public void setFields(List<SolrFacetFields> fields) {
		this.fields = fields;
	}
	
	
	
}
