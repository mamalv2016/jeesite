package com.renjie120.common.solr.json.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class SolrJSONUtils {

	public static Integer getInt(JSONObject jso, String field){

		return (jso == null || jso.size() == 0 ||null==jso.get(field)) ? 0:jso.getInteger(field);
	};

	public static String getString(JSONObject jso, String field){
		return (jso == null || jso.size() == 0 ||null==jso.get(field)) ?null:jso.getString(field);
	};
	
	public static Integer getInt(Map jso,String field){
		return null==jso.get(field)?0:new Integer(jso.get(field).toString());
	};

	public static String getString(Map jso,String field){
		return null==jso.get(field)?null:jso.get(field).toString();
	};
	
	
	public static Boolean getBoolean(JSONObject jso,String field){
		return null==jso.get(field)?true:jso.getBoolean(field);
	};

	public static Long getLong(JSONObject jso,String field){
		return null==jso.get(field)?0:jso.getLong(field);
	};

	public static Date getDate(JSONObject jso,String field){
		return null==jso.get(field)?null:new Date(jso.getLong(field));
	};
	
	public static Double getDouble(JSONObject jso,String field){
		return null==jso.get(field)?0.0:jso.getDouble(field);
	};

	public static BigDecimal getBigDecimal(JSONObject jso,String field){
		return null==jso.get(field)?new BigDecimal(0):new BigDecimal(jso.getString(field));
	}
	
	public static String getString(JSONArray ja, int index){
		return null==ja.get(index)?null:ja.getString(index);
	};
	
	public static int getInt(JSONArray ja, int index) {
		return null==ja.get(index)?null:ja.getInteger(index);
	};
	
	public static boolean assertEmpty(Object value){
		
		if(null==value){
			return true;
		}
		
		if(value instanceof String
				&& 0==((String)value).trim().length()){
			return true;
		}
		
		if(value instanceof Object[]
		        && 0==((Object[])value).length){
			return true;
		}

		if(value instanceof Collection
		        && 0==((Collection)value).size()){
			return true;
		}
		
		return false;
		
	}
}
