/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.money.dao.TallyTypeDao;
import com.thinkgem.jeesite.modules.money.entity.TallyType;

/**
 * 字典工具类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
public class TallyTypeUtils {

	private static TallyTypeDao tallyTypeDao = SpringContextHolder
			.getBean(TallyTypeDao.class);

	public static final String CACHE_TALLYTYPE_MAP = "tallyTypeMap";

	public static List<TallyType> getTallyTypeList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<TallyType>> dictMap = (Map<String, List<TallyType>>)CacheUtils.get(CACHE_TALLYTYPE_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (TallyType dict : tallyTypeDao.findAllList(new TallyType())){
				List<TallyType> dictList = dictMap.get(dict.getTypeCode());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getTypeCode(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_TALLYTYPE_MAP, dictMap);
		}
		List<TallyType> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * 
	 * @param type
	 * @return
	 */
	public static String getTallyTypeListJson(String type) {
		return JsonMapper.toJsonString(getTallyTypeList(type));
	}

}
