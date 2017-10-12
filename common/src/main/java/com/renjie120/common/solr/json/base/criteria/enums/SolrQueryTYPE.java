package com.renjie120.common.solr.json.base.criteria.enums;

import java.net.URLEncoder;
import java.util.*;

import com.renjie120.common.solr.json.base.criteria.SolrQueryField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 	"q=hotelName:*江* + hotelName:*江* + hotelName:*001* + doublePrice:[ 200 TO 900 ] +hotelId:( 1003 OR 1002 OR 1004)
 * @author Administrator
 *
 */
public enum SolrQueryTYPE {

        EQ("EQ"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
        		return field+":"+format(value,urlcode);
        	}
        },
        NOTEQ("NEQ"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
        		return "!"+field+":"+format(value,urlcode);
        	}
        },
        IN("IN"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){

        		if(value instanceof Object[]){
        			return field+":"+"("+formatCollection(Arrays.asList((Object[])value),"OR",urlcode)+")";
        		}else if(value instanceof Collection){
        			return field+":"+"("+formatCollection((Collection)value,"OR",urlcode)+")";
        		}else{
        			return field+":"+"("+format(value,urlcode)+")";
        		}
        	}
        },
        NOT_IN("NOT_IN"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){

        		if(value instanceof Object[]){
        			return "!"+field+":"+"("+formatCollection(Arrays.asList((Object[])value),"OR",urlcode)+")";
        		}else if(value instanceof Collection){
        			return "!"+field+":"+"("+formatCollection((Collection)value,"OR",urlcode)+")";
        		}else{
        			return "!"+field+":"+"("+format(value,urlcode)+")";
        		}
        	}
        },
        LIKE("LIKE"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
        			return field+":"+"*"+format(value,urlcode)+"*";
        	}
        },
        LIKE_IN("LIKE_IN"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){

        		if(value instanceof Object[]){
        			return field+":"+"("+formatCollection(Arrays.asList((Object[])value),"OR",urlcode,"*")+")";
        		}else if(value instanceof Collection){
        			return field+":"+"("+formatCollection((Collection)value,"OR",urlcode,"*")+")";
        		}else{
        			return field+":"+"("+"*"+format(value,urlcode)+"*"+")";
        		}
        	}
        },
        PREFIX("PREFIX"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
    			return field+":"+format(value,urlcode)+"*";
        	}
        },
        SUFFIX("SUFFIX"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
    			return field+":"+"*"+format(value,urlcode);
        	}
        },
        BETWEEN("BT"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){

        		if(value instanceof Object[]){
        		    Arrays.sort((Object[]) value);
                    String to = field + ":" + "[" + formatCollection(Arrays.asList((Object[]) value), "TO", urlcode) + "]";
//                    System.out.println("Between:-->"+to);
                    return to;
        		}else if(value instanceof Collection){
                    Collection value1 = (Collection) value;
                    Object[] objects = value1.toArray();
                    Arrays.sort(objects);
                    String to = field + ":" + "[" + formatCollection(Arrays.asList(objects), "TO", urlcode) + "]";
//                    System.out.println("Between:-->"+to);
                    return to;
        		}

        		return "";
        	}
        },
        GREAT_THAN("GT"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
    			return field+":"+"["+format(value,urlcode)+" TO *]";
        	}
        },
        LESS_THAN("LT"){
        	@Override
            public String getSolrString(String field,String urlcode,Object value){
    			return field+":"+"[* TO "+format(value,urlcode)+"]";
        	}
        };

	private static final String[] filterArr = new String[] { "AND", "OR", "+", "-", "&&", "||", "!", "(", ")", "{", "}",
			"[", "]", "^", "\"", "~", "*", "?", ":", "\\" };
		private static final String escapeStr = "\\";

		private String type;

		public String getType() {
			return type;
		}

		private SolrQueryTYPE(String type) {
			this.type = type;
		}

		public abstract String getSolrString(String field,String urlcode,Object value);
		
		public String getSolrString(String field,String urlcode,Object value,
				Map<String, Collection<String>> dbs) {
			
			String str = "";
			for(String key:dbs.keySet()){
				for(String val : dbs.get(key)){
					if(field.indexOf(key)>-1){
						str += getSolrString(field.replace(key,val),urlcode,value)+" AND ";
					}
				}
			}
			
			if ("".equals(str)) {
				return getSolrString(field,urlcode,value);
			}
			return str.substring(0,str.length()-5);
		}
		

		public String format(Object value,String urlcode,String fix) {


    		if(value instanceof Object[]){
    			value=((Object[])value)[0];
    		}else if(value instanceof Collection){
    			value=((Collection)value).iterator().next();
    		}

			if(value instanceof Date){
				return new Long(((Date)value).getTime()).toString();
			}
			String v;
			String valueStr = escapeValueString(value.toString());
			try{
				v=("".equals(urlcode)?valueStr:URLEncoder.encode(valueStr,urlcode));
			}catch(Exception e){
				v=valueStr;
			}
			if(fix==null||fix.isEmpty()){
			return v;
		}
			return fix+v+fix;
		}

		public static String escapeValueString(String valueStr){
			for (int i = 0; i < filterArr.length; i++) {
				String filterStr = filterArr[i];
				valueStr = escapeValueString(valueStr, filterStr);
			}
			return valueStr;
		}

		public static String escapeValueString(String valueStr, String filterStr){
			valueStr = StringUtils.replace(valueStr, " "+filterStr+" ", " "+escapeStr+filterStr+" ");
			if(StringUtils.startsWith(valueStr, filterStr+" ")){
				valueStr = StringUtils.replaceOnce(valueStr, filterStr+" ", escapeStr+filterStr+" ");
			}
			if(filterStr.equals(valueStr)){
				valueStr = escapeStr+filterStr;
			}
			return valueStr;
		}

		public String formatCollection(Collection collection,String split,String urlcode,String fix){

			if(1==collection.size()){
				return format(collection.iterator().next(),urlcode,fix);
			}

			StringBuilder builder=new StringBuilder();
			int i=0;
			int size=collection.size();
            // 如果传入单个*号则不作转义否则转义
            for(Object object:collection){
                i++;
                builder.append(((i==1)?" ":" "+split+" ")+("*".equals(object)?"*":format(object,urlcode,fix)));
            }

			return builder.toString();
		}
		
		public String formatCollection(Collection collection,String split,String urlcode){
			return formatCollection(collection,split,urlcode,null);
		}
		
		public String format(Object value,String urlcode) {
			return format(value,urlcode,null);
		}

		public String getSolrString(SolrQueryField solrQueryField) {
			return getSolrString(solrQueryField.getField().name()
					,solrQueryField.getField().urldecode()
					,solrQueryField.getValues());
		}
		
		public String getSolrString(SolrQueryField solrQueryField,Map<String,Collection<String>> dbs) {
			return getSolrString(solrQueryField.getField().name()
					,solrQueryField.getField().urldecode()
					,solrQueryField.getValues()
					,dbs);
		}
		
    }