package com.renjie120.common.solr.json.ext.select;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.List;

/**  {
            "responseHeader":
                {
                    "status":0,
                    "QTime":170,
                    "params":
                     {
                        "group.format":"simple",
                        "group.ngroups":"true",
                        "sort":"day asc",
                        "start":"0",
                        "q":"cityName:*上海* AND !rateCode:mustDelete AND day:[ 1345219200000 TO 1345305599999]",
                        "group.limit":"1",
                        "group":"true",
                        "rows":"10"
                     }
                },
            "grouped":
                {
                   "hotelId_str":
                    {
                        "matches":223,
                        "ngroups":13,
                        "doclist":
                            {
                                "numFound":223,
                                "start":0,
                                    "docs":[
                                        {"hotelId":13},
                                        {"hotelId":15},
                                        {"hotelId":26}
                                    ]
                            }
                    }
                }
            }
   **/

public class SolrBaseObject<T> {

	private static String DOCS="docs";

	private SolrResponseHeader responseHeader;
	private SolrResponse response;
	private SolrCollapse collapseCounts;
	private SolrFacet facetCounts;
	private String dynamicBase;
	
	public SolrBaseObject(String solrResponse, String dynamicBase, Class<T> dto) throws Exception {
		this.dynamicBase = dynamicBase;
		init(solrResponse, dto);
	}
	
	public SolrBaseObject(String solrResponse,Class dto) throws Exception {
		init(solrResponse, dto);
	}

	public static void main(String[] args){
	    String str = "{a:123,b:12344}";
		JSONObject obj = JSONObject.parseObject(str);
		System.out.println(obj.get("a"));
	}
	private void init(String solrResponse, Class dto) throws IOException,
			JsonParseException, JsonMappingException, Exception {
		JSONObject obj= JSONObject.parseObject(solrResponse);

		if(null==obj){
			throw new Exception("SolrResponse is NULL");
		}
        this.response=new SolrResponse(obj,dynamicBase,dto);

		this.responseHeader=new SolrResponseHeader(obj);

		this.collapseCounts=new SolrCollapse(obj);

		this.facetCounts=new SolrFacet(obj);
	}

    public List<T> getResults() {
        return response.getDocs();
    }

	public SolrResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(SolrResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public SolrResponse getResponse() {
		return response;
	}

	public void setResponse(SolrResponse response) {
		this.response = response;
	}

	public SolrCollapse getCollapseCounts() {
		return collapseCounts;
	}

	public void setCollapseCounts(SolrCollapse collapseCounts) {
		this.collapseCounts = collapseCounts;
	}

	public SolrFacet getFacetCounts() {
		return facetCounts;
	}

	public void setFacetCounts(SolrFacet facetCounts) {
		this.facetCounts = facetCounts;
	}

	public SolrBaseObject() {
		super();
	}

}