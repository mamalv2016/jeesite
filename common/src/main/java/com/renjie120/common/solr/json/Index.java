package com.renjie120.common.solr.json;

import com.renjie120.common.utils.JsonUtils;

public abstract class Index {
	
	public String toJsonString() throws Exception {
		return JsonUtils.toJsonStr(this);
	}
	
	public abstract String getIndexId();

}
