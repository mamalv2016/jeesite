package com.renjie120.common.solr.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class IndexConverter {

    public enum ActionType {
        ADD {
            @Override
            public String converToJsonString(List<? extends Index> indexs) throws Exception {
                StringBuilder builder=new StringBuilder("{");
                
                for(int i=0 ; i<indexs.size(); i++) {
                    Index journeyIndex = indexs.get(i);
                    if(i>0) {
                        builder.append(",");
                    }
                    builder.append("\"add\": {\"doc\":" + journeyIndex.toJsonString()+ "} ");
                }
                String options = "";
                builder.append( options + "}");
                return builder.toString();
            }
        },
        DELETE {
            @Override
            public String converToJsonString(List<? extends Index> indexs) throws Exception {
            	StringBuilder builder=new StringBuilder("{");
              
              for(int i=0 ; i<indexs.size(); i++) {
                  Index index = indexs.get(i);
                  if(i>0) {
                      builder.append(",");
                  }
                  builder.append("\"delete\": {\"id\":\"" + index.getIndexId()+ "\"} ");
              }
              String options = "";
              builder.append( options + "}");
              return builder.toString();
            }
        },
        DELETE_BY_QUERY {
			@Override
			public String deleteByQuery(Map<String, String> queryMap) throws Exception {
	              StringBuilder builder=new StringBuilder("{"+"\"delete\":{");
	              String queryStr = "\"query\":\"%s:%s\"";
	              
	              boolean flag = false;
	              for(Entry<String,String> entry:queryMap.entrySet()){
	            	  String query = String.format(queryStr,entry.getKey(),entry.getValue());
	            	  if(flag){
		                  builder.append(","+query);
	            	  }else{
		                  builder.append(query);
	            	  }
	                  flag=true;
	              }
	              String options = "}";
	              builder.append(options + "}");
	              return builder.toString();
	              
			}
        },
        DELETE_BY_MUTIQUERY {
			@Override
			public String deleteByMutiQuery(Map<String, String> queryMap) throws Exception {
	              StringBuilder builder=new StringBuilder("{"+"\"delete\":{"+"\"query\":\"");
	              String queryStr = "%s:%s";
	              
	              boolean flag = false;
	              for(Entry<String,String> entry:queryMap.entrySet()){
	            	  String query = String.format(queryStr,entry.getKey(),entry.getValue());
	            	  if(flag){
		                  builder.append(" AND "+query);
	            	  }else{
		                  builder.append(query);
	            	  }
	                  flag=true;
	              }
	              String options = "\"}";
	              builder.append(options + "}");
	              return builder.toString();
	              
			}
        };
        
	    public String converToJsonString(List<? extends Index> indexs) throws Exception{
	    	return "";
	    };
	    
	    public String deleteByMutiQuery(Map<String, String> queryMap)
				throws Exception {
			return "";
		}
	    
	    public String deleteByQuery(Map<String,String> queryMap) throws Exception{
	    	return "";
	    }
	    
    }
    
    public static void main(String[] args) throws Exception {
    	
    	Map<String, String> queryMap = new HashMap<String, String>();
    	queryMap.put("flightNo","MU2197");
    	queryMap.put("policyId","22828");
    	System.out.println(ActionType.DELETE_BY_QUERY.deleteByQuery(queryMap));
    	
    }

}
