package com.renjie120.common.solr.json.base.criteria;

import com.renjie120.common.solr.json.base.SolrJSONUtils;
import com.renjie120.common.solr.json.base.criteria.annotation.*;
import com.renjie120.common.solr.json.base.criteria.enums.SolrOrderTYPE;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * "http://127.0.0.1:6061/solr/select/?" +
	"wt=json&" +
	"start=0&" +
	"rows=2&" +
	"q=hotelName:*江* + hotelName:*江* + hotelName:*001* + doublePrice:[ 200 TO 900 ] +hotelId:( 1003 OR 1002 OR 1004)	
	"facet=true&facet.field=name&facet.field=title&"+
	"&qt=collapse&collapse=true&collapse.field=name&collapse.threshold=1"
 *
 */
public class SolrBaseCriteria {
	
	protected int start=-1;
	protected int rows=-1;
	
	protected Map<Method,Object> fieldvalues=new HashMap<Method,Object>();
	protected Map<String,String> fieldnames=new HashMap<String,String>();
	
	protected Map<String,SolrOrderField> sorts=new LinkedHashMap<String,SolrOrderField>();
	protected Map<String,SolrQueryField> qs=new LinkedHashMap<String,SolrQueryField>();
	protected Map<String,SolrQFacet> facets=new LinkedHashMap<String,SolrQFacet>();
	protected Map<String,SolrQCollapse> collapses=new LinkedHashMap<String,SolrQCollapse>();
	
	protected String wt="wt=json";
	protected String q="";
	protected String sort="";
	protected String page="";
	protected String facet="";
	protected String collapes="";
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public String getWt() {
		return wt;
	}
	public void setWt(String wt) {
		this.wt = wt;
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
	public String getFacet() {
		return facet;
	}
	public void setFacet(String facet) {
		this.facet = facet;
	}
	public String getCollapes() {
		return collapes;
	}
	public void setCollapes(String collapes) {
		this.collapes = collapes;
	}	
	
	public Map<Method, Object> getFieldvalues() {
		return fieldvalues;
	}

	public void setFieldvalues(Map<Method, Object> fieldvalues) {
		this.fieldvalues = fieldvalues;
	}

	public Map<String, String> getFieldnames() {
		return fieldnames;
	}

	public void setFieldnames(Map<String, String> fieldnames) {
		this.fieldnames = fieldnames;
	}

	public Map<String, SolrOrderField> getSorts() {
		return sorts;
	}

	public void setSorts(Map<String, SolrOrderField> sorts) {
		this.sorts = sorts;
	}

	public Map<String, SolrQueryField> getQs() {
		return qs;
	}

	public void setQs(Map<String, SolrQueryField> qs) {
		this.qs = qs;
	}

	public Map<String, SolrQFacet> getFacets() {
		return facets;
	}

	public void setFacets(Map<String, SolrQFacet> facets) {
		this.facets = facets;
	}

	public Map<String, SolrQCollapse> getCollapses() {
		return collapses;
	}

	public void setCollapses(Map<String, SolrQCollapse> collapses) {
		this.collapses = collapses;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public SolrBaseCriteria() {
		super();
	}
	
	public SolrBaseCriteria(Object object) throws Exception {
		super();
		init(object);
	}
	
	public void init(Object object) throws Exception {
		
		if(null==object){
			throw new NullPointerException();
		}
		
		try{
		
			Method[] methods=object.getClass().getMethods();
			for(Method method:methods){
				SolrIgnore ignore=method.getAnnotation(SolrIgnore.class);
				if(isGetMethod(method)
						||null!=ignore){
					continue;
				}
				
				addFields(object, method);
				
			}
		}catch(Exception e){
			throw e;
		}		
	}
	protected void addFields(Object object, Method method) throws Exception {
		add2Q(method,object);
		add2Sort(method,object);
		add2Collapse(method,object);
		add2Facet(method,object);
	}
	
	public void create(){
		
		initPage();
		initSort();
		initQ();
		initFacet();
		initCollapes();
		
	}
	
	protected void add2Q(Method method,Object object) throws Exception {
		
		SolrField field=method.getAnnotation(SolrField.class);
		
		if(null==field){
			return;
		}
		
		Object value=getMethodFieldValue(method,object);
		putValue2QS(field,value);
		
	}

	protected void putValue2QS(SolrField field, Object value){
		
		if(SolrJSONUtils.assertEmpty(value)){
			return;
		}
		
		String name=field.name();
		
		SolrQueryField solr=null;
		if(!qs.containsKey(name)){
			qs.put(name,new SolrQueryField(field,value));
		}else{
			solr=qs.get(name);
			solr.addValue(value);
		}
			
	}
	
	protected void add2Sort(Method method,Object object)  throws Exception{
		
		SolrOrder field=method.getAnnotation(SolrOrder.class);
		if(null==field){
			return;
		}
		
		String name=field.name();
		if(0==name.length()){
			name=getFieldName(method);
		}
		SolrOrderTYPE type=field.sort();
		String sortfield=field.sortfield();
		Object value=getMethodFieldValue(method,object);
		
		if(0!=sortfield.trim().length()){
			Object sortvalue=getFieldValue(sortfield,object);
    		if(SolrJSONUtils.assertEmpty(sortvalue)){
    			return;
    		}
			type=SolrOrderTYPE.valueOf(sortvalue.toString().toUpperCase());
		}
		sorts.put(name,new SolrOrderField(name,type));
	}
	
	protected void add2Collapse(Method method,Object object)  throws Exception{
		SolrGroupDistinct field=method.getAnnotation(SolrGroupDistinct.class);
		if(null==field){
			return;
		}
		String name=field.field();
		if(0==name.trim().length()){
			name=getFieldName(method);
		}
		int threshold=field.threshold();
		collapses.put(name,new SolrQCollapse(name,threshold));
	}
	
	protected void add2Facet(Method method,Object object)  throws Exception{
		
		SolrGroupCount field=method.getAnnotation(SolrGroupCount.class);
		if(null==field){
			return;
		}
		String name=field.field();
		if(0==name.trim().length()){
			name=getFieldName(method);
		}
		int limit=field.limit();
		facets.put(name,new SolrQFacet(name,limit));
	}

	protected String getFieldName(Method method) {
		
		if(fieldnames.containsKey(method)){
			return fieldnames.get(method);
		}
		
		String methodName=method.getName();
		String firstChar=methodName.substring(3,4);
		String fieldName=methodName.substring(4,methodName.length());
		String field=firstChar.toLowerCase()+fieldName;
		fieldnames.put(method.getName(),field);
		
		return field;
	}

	protected boolean isGetMethod(Method method){
		String methodName=method.getName();
		return !"get".equals(methodName.substring(0,3));
	}

	protected String getMethodName(String field){
		String firstChar=field.substring(0,1);
		String fieldName=field.substring(1,field.length());
		return "get"+firstChar.toUpperCase()+fieldName;
	}	
	
	protected Object getFieldValue(String field, Object object) throws Exception {
		String methodName=getMethodName(field);
		Method method=object.getClass().getMethod(methodName);
		return getMethodFieldValue(method,object);
	}

	
	protected Object getMethodFieldValue(Method method, Object object) throws Exception {
		
		if(fieldvalues.containsKey(method)){
			return fieldvalues.get(method);
		}
		
		Object value=method.invoke(object);
		fieldvalues.put(method,value);
		return value;
		
	}
	
	protected void initPage(){
		
		if(-1!=start&&-1!=rows){
			this.setPage("&start="+start+"&"+"rows="+rows);
		}	
		
	}
	
	protected void initSort(){
		
		StringBuilder builder = new StringBuilder("");
		Set<Entry<String,SolrOrderField>> sortset=sorts.entrySet();
		int i=0;
		for(Entry<String,SolrOrderField> entry:sortset){
			i++;
			String solrfield=entry.getKey();
			SolrOrderField  qField=entry.getValue();
			builder.append((i==1?"sort=":",")+qField.toSolrString());
		}
		
		this.setSort(builder.toString());
	}
	
	protected void initQ(){
		
		StringBuilder builder = new StringBuilder("q");
		
		Set<Entry<String,SolrQueryField>> qset=qs.entrySet();
		
		int i=0;
		for(Entry<String,SolrQueryField> entry:qset){
			i++;
			String solrfield=entry.getKey();
			SolrQueryField  qField=entry.getValue();
			builder.append((i==1?"=":" AND ")+qField.toSolrString());
		}
		if("q".equals(builder.toString())){
			builder.append("=*:*");
		}
		
		this.setQ(builder.toString());
	}
	
	/**
	 * 	"facet=true&facet.field=name&facet.field=title&"+
	 * @param
	 */
	protected void initFacet(){
			
		StringBuilder builder = new StringBuilder("facet=true");
		Set<Entry<String,SolrQFacet>> facetset=facets.entrySet();
		
		int i=0;
		for(Entry<String,SolrQFacet> entry:facetset){
			i++;
			String solrfield=entry.getKey();
			builder.append("&facet.field="+solrfield);
		}
		
		this.setFacet(builder.toString());
	}

	/**
	 * "qt=collapse&collapse=true&collapse.threshold=1&collapse.field=name";
	 * @param
	 */
	protected void initCollapes(){
		
		StringBuilder builder = new StringBuilder("group=true&group.limit=1&group.ngroups=true&group.format=simple");
		Set<Entry<String,SolrQCollapse>> collset=collapses.entrySet();
		
		int i=0;
		for(Entry<String,SolrQCollapse> entry:collset){
			i++;
			String solrfield=entry.getKey();
			builder.append("&group.field="+solrfield);
		}
		
		this.setCollapes(builder.toString());
	}
	
	public String getSolrSelect(){
		if(StringUtils.isNotBlank(this.sort)){
			return replace2And(this.q+"&"+this.sort);
		}
		return replace2And(this.q);
	}
	
	public String getSolrSelectWithSort() {
		return replace2And(this.q+"&"+this.sort);
	}
	
	public String getSolrSelectWithFacet(){
		
		return replace2And(this.getSolrSelectWithPage()+
		"&"+this.facet);
		
	}
	public String getSolrSelectWithCollapse(){
		
		return replace2And(this.getSolrSelectWithPage()+
		"&"+this.collapes);
		
	}
	
	public String getSolrSelectWithPage() {
		return replace2And(this.q+"&"+this.sort+
		this.page);
	}
	
	protected String replace2And(String in){
		return in.replaceAll("&&","&");
	}
	
}
