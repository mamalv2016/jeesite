package com.renjie120.common.solr.json.base.criteria.enums;

import java.util.Collection;
import java.util.Map;


/**
 * 	"q=hotelName:*江* + hotelName:*江* + hotelName:*001* + doublePrice:[ 200 TO 900 ] +hotelId:( 1003 OR 1002 OR 1004)	
 * @author Administrator
 *
 */
public enum SolrOrderTYPE {
        
        ASC("ASC"){
        	@Override
            public String getSolrString(String field){
        		return field+" "+"asc";
        	}
			
        },
        DESC("DESC"){
        	@Override
            public String getSolrString(String field){
        		return field+" "+"desc";
        	}

        };
        
        private String type;

		public String getType() {
			return type;
		}

		private SolrOrderTYPE(String type) {
			this.type = type;
		}
        
		public abstract String getSolrString(String field);
		
		public String getSolrString(String field,
				Map<String, Collection<String>> dbs) {
			
			String str = "";
			for(String key:dbs.keySet()){
				for(String value : dbs.get(key)){
					if(field.indexOf(key)>-1){
						str += getSolrString(field.replace(key,value))+",";
					}
				}
			}
			
			if ("".equals(str)) {
				return getSolrString(field);
			}
			return str.substring(0,str.length()-1);
		}	
    }