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

	public static final String CACHE_TALLYTYPE = "tallyTypes";

	public static List<TallyType> getTallyTypes() {
		@SuppressWarnings("unchecked")
		List<TallyType> tallyTypes = (List<TallyType>) CacheUtils
				.get(CACHE_TALLYTYPE);
		if (tallyTypes == null) {
			tallyTypes = tallyTypeDao.findAllList(new TallyType());
		 
			CacheUtils.put(CACHE_TALLYTYPE, tallyTypes);
		} 
		if (tallyTypes == null) {
			tallyTypes = Lists.newArrayList();
		}
		return tallyTypes;
	}

	/**
	 * 返回字典列表（JSON）
	 * 
	 * @param type
	 * @return
	 */
	public static String getTallyTypeListJson() {
		return JsonMapper.toJsonString(getTallyTypes());
	}

}
