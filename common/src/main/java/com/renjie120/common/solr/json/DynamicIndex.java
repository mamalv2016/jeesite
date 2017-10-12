package com.renjie120.common.solr.json;

import java.util.Map;

public abstract class DynamicIndex extends Index {
	
	public abstract Map getFields();
	public abstract void setFields(Map map);
	public abstract void initDynamicBase(String dynamicBase);

}
