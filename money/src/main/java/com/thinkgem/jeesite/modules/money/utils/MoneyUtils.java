/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.utils;

import java.util.List;

import math.utils.EqualUtils;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.renjie120.modules.moneyuser.dao.MoneyUserInfoDao;
import com.renjie120.modules.moneyuser.entity.MoneyUserInfo;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.money.dao.TallyTypeDao;
import com.thinkgem.jeesite.modules.money.entity.TallyType;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class MoneyUtils {

	private static TallyTypeDao tallyTypeDao = SpringContextHolder
			.getBean(TallyTypeDao.class);

	private static MoneyUserInfoDao moneyUserInfoDao = SpringContextHolder
			.getBean(MoneyUserInfoDao.class);

	public static final String CACHE_TALLYTYPE = "tallyTypes";
	public static final String CACHE_MONEY_USER = "moneyUsers";

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
	
	public static List<MoneyUserInfo> getMoneyUsers() {
		@SuppressWarnings("unchecked")
		List<MoneyUserInfo> lists = (List<MoneyUserInfo>) CacheUtils
				.get(CACHE_MONEY_USER);
		if (lists == null) {
			lists = moneyUserInfoDao.findAllList(new MoneyUserInfo());
		 
			CacheUtils.put(CACHE_MONEY_USER, lists);
		} 
		if (lists == null) {
			lists = Lists.newArrayList();
		}
		return lists;
	}
	
    public static String getMoneyTypename(String str){
    	String str1="";
    	if(StringUtils.isNotBlank(str)){
    		 str1=moneyUserInfoDao.finTypename(str);
    	}
    	return str1;
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
