package com.renjie120.common.solr.json.ext.select;


import com.renjie120.common.dto.BaseResultDto;
import com.renjie120.common.dto.Pagination;
import org.apache.commons.lang3.StringUtils;

public class SolrObject<T> extends SolrBaseObject<T> {

	public SolrObject(String solrResponse, Class dto) throws Exception {
		super(solrResponse,dto);
	}
	public SolrObject(String solrResponse, String dynamicBase, Class<T> dto)  throws Exception {
		super(solrResponse,dynamicBase,dto);
	}

	public Pagination getPagination() {
		
		Pagination pagination=new Pagination();
		
		pagination.setRecords(this.getResponse().getNumFound());
		
		if(0!=this.getResponseHeader().getParams().getRows()){
			pagination.setRows(this.getResponseHeader().getParams().getRows());
		}
		
		if(0!=this.getResponseHeader().getParams().getStart()
				&&0!=this.getResponseHeader().getParams().getRows()){
			pagination.setPage(1+(this.getResponseHeader().getParams().getStart())/this.getResponseHeader().getParams().getRows());
		}
		
		pagination.countRecords(this.getResponse().getNumFound());

		String sorts=this.getResponseHeader().getParams().getSort();
		
		if(StringUtils.isNotEmpty(sorts)){
			String[] strs = sorts.split(",");
			for(String sort:strs){
				String[] str=sort.split(" ");
				pagination.setSidx(str[0]);
				pagination.setSord(str[1]);
			}
		}
		
		return pagination;
    }
	
	 public BaseResultDto<T> getResultDto() {
		 return new BaseResultDto<T>(this.getPagination(),this.getResults());
	 }
	
}
