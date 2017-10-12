package com.renjie120.common.solr.json.ext.dynamic.criteria;

import com.renjie120.common.solr.json.base.SolrJSONUtils;
import com.renjie120.common.solr.json.base.criteria.SolrBaseCriteria;
import com.renjie120.common.solr.json.base.criteria.SolrOrderField;
import com.renjie120.common.solr.json.base.criteria.SolrQueryField;
import com.renjie120.common.solr.json.ext.dynamic.criteria.annotation.SolrDynamicBase;

import java.lang.reflect.Method;
import java.util.*;


public class SolrDynamicCriteria extends SolrBaseCriteria
{

	private Map<String,Collection<String>> dynamicBases = new LinkedHashMap<String,Collection<String>>();
	
	public Map<String, Collection<String>> getDynamicBases() {
		return dynamicBases;
	}

	public void setDynamicBases(Map<String, Collection<String>> dynamicBases) {
		this.dynamicBases = dynamicBases;
	}
	
	@Override
	protected void addFields(Object object, Method method) throws Exception {
		super.addFields(object, method);
		add2Dynamic(method,object);	
	}

	private void add2Dynamic(Method method,Object object) throws Exception {	
		SolrDynamicBase field=method.getAnnotation(SolrDynamicBase.class);
		if(null==field){
			return;
		}
		Object value=getMethodFieldValue(method,object);
		putValue2DBS(field,value);
	}
	
	private void putValue2DBS(SolrDynamicBase field, Object value){
		
		if(SolrJSONUtils.assertEmpty(value)){
			return;
		}
		
		Collection colls = new ArrayList<String>();
		if(value instanceof Collection){
			for(Object v:(Collection)value){
				if(v instanceof String){
					colls.add((String)v);
				}
			}
		}else if(value instanceof Map){
			for(Object v:((Map)value).values()){
				if(v instanceof String){
					colls.add((String)v);
				}
			}
		}else if(value instanceof String){
			colls.add((String)value);
		}
		
		if(0==colls.size()){
			return;
		}
		
		String name=field.name();
		Collection collVs=null;
		if(!dynamicBases.containsKey(name)){
			dynamicBases.put(name,colls);
		}else{
			collVs=dynamicBases.get(name);
			collVs.addAll(colls);
		}
			
	}

	protected void initSort(){
		
		StringBuilder builder = new StringBuilder("");
		Set<Map.Entry<String,SolrOrderField>> sortset=sorts.entrySet();
		int i=0;
		for(Map.Entry<String,SolrOrderField> entry:sortset){
			i++;
			String solrfield=entry.getKey();
			SolrOrderField  qField=entry.getValue();
			builder.append((i==1?"sort=":",")+qField.toSolrString(dynamicBases));
		}
		
		this.setSort(builder.toString());
		
	}
	
	protected void initQ(){
		
		StringBuilder builder = new StringBuilder("q");
		
		Set<Map.Entry<String,SolrQueryField>> qset=qs.entrySet();
		
		int i=0;
		for(Map.Entry<String,SolrQueryField> entry:qset){
			i++;
			String solrfield=entry.getKey();
			SolrQueryField  qField=entry.getValue();
			builder.append((i==1?"=":" AND ")+qField.toSolrString(dynamicBases));
		}
		if("q".equals(builder.toString())){
			builder.append("=*:*");
		}
		
		this.setQ(builder.toString());
		
	}
}
