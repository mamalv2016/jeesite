package com.renjie120.common.solr.json.ext.select;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.renjie120.common.solr.json.DynamicIndex;
import com.renjie120.common.solr.json.base.SolrJSONUtils;
import com.renjie120.common.utils.JsonUtils;

import java.lang.reflect.Array;
import java.util.*;

public class SolrResponse<T> {

    private static String RESPONSE = "response";
    private static String GROUPED = "grouped";
    
    private String dynamicBase;

    public SolrResponse() {
        super();
    }

    public SolrResponse(JSONObject obj, Class dto) {
        super();
        init(obj, dto);
    }
    
    public SolrResponse(JSONObject obj,String dynamicBase,Class dto) {
        super();
    	this.dynamicBase=dynamicBase;
        init(obj, dto);
    }

	private void init(JSONObject obj, Class dto) {
		try {
            if (obj.containsKey(GROUPED)) {
                setPropertyWhenHasGroupedQuery(obj, dto);
            } else {
            	if(DynamicIndex.class.isAssignableFrom(dto)){
            		setPropertyWhenSimpleQueryWithDynamic(obj, dto);
                    return;
            	}
                setPropertyWhenSimpleQuery(obj, dto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	List<T> docs;
	int numFound;
	int start;

	public List<T> getDocs() {
		return docs;
	}

	public void setDocs(List<T> docs) {
		this.docs = docs;
	}


	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}


	public int getStart() {
		return start;
	}

    public void setStart(int start) {
        this.start = start;
    }

    private void setPropertyWhenHasGroupedQuery(JSONObject jsonObj, Class dto) throws Exception {
        JSONObject  obj = jsonObj.getJSONObject(GROUPED);
        obj.values();
        Collection<Object> groupedValues =  obj.values();
        Iterator<Object> groupedIterator = groupedValues.iterator();
        while (groupedIterator.hasNext()) {
            JSONObject jsonValue = (JSONObject)groupedIterator.next();
            if (jsonValue.containsKey("ngroups")) {
                numFound = jsonValue.getInteger("ngroups");
            }
            if (jsonValue.containsKey("doclist")) {
                JSONObject docList = jsonValue.getJSONObject("doclist");
                start = docList.getInteger("start");
                ;
                docs = (List<T>) Arrays.asList(JSON.parseArray(docList.getJSONArray("docs").toString(), ((Object[]) Array.newInstance(dto, 1)).getClass()));
            }
        }
    }
    
    private void setPropertyWhenSimpleQueryWithDynamic(JSONObject jsonObj,Class<? extends DynamicIndex> dto) throws Exception{
        JSONObject  obj = jsonObj.getJSONObject(RESPONSE);
        numFound = SolrJSONUtils.getInt(obj, "numFound");
        if(obj != null &&  obj.size() > 0  && obj.getJSONArray("docs") != null){
            Map[] maps = JsonUtils.parseJsonStr(obj.getJSONArray("docs").toString(),((Map[])Array.newInstance(Map.class, 1)).getClass());
            //docs = (List<T>) Arrays.asList((Object[]) JSONMapper.getInstance().readValue(obj.getJSONArray("docs").toString(), ((Object[]) Array.newInstance(dto, 1)).getClass()));
            docs = (List<T>) Arrays.asList(JsonUtils.parseJsonStr(obj.getJSONArray("docs").toString(),((Object[]) Array.newInstance(dto, 1)).getClass()));
            for(int i=0;i<docs.size();i++){
            	DynamicIndex doc = (DynamicIndex)docs.get(i);
            	doc.initDynamicBase(this.dynamicBase);
            	doc.setFields(maps[i]);
            }
        }
    };

    private void setPropertyWhenSimpleQuery(JSONObject jsonObj, Class dto) throws Exception {
        JSONObject  obj = jsonObj.getJSONObject(RESPONSE);
        numFound = SolrJSONUtils.getInt(obj, "numFound");
        if(obj != null &&  obj.size() > 0  && obj.getJSONArray("docs") != null){
            docs = (List<T>) Arrays.asList(JsonUtils.parseJsonStr(obj.getJSONArray("docs").toString(),((Object[]) Array.newInstance(dto, 1)).getClass()));
//            docs = (List<T>) Arrays.asList((Object[]) JSONMapper.getInstance().readValue(obj.getJSONArray("docs").toString(), ((Object[]) Array.newInstancee(dto, 1)).getClass()));
        }
    }
    
    

}