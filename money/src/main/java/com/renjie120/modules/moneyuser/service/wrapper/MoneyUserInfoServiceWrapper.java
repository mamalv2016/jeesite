/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneyuser.service.wrapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.renjie120.common.annotation.log.LoggerPoint;
import com.renjie120.common.enums.PointerKey;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ICurdService;
import com.renjie120.modules.moneyuser.entity.MoneyUserInfo;
import com.renjie120.modules.moneyuser.service.MoneyUserInfoService;

/**
 * 理财系统用户ServiceWrapper
 * @author renjie120
 * @version 2017-04-22
 */
@Service
public class MoneyUserInfoServiceWrapper implements ICurdService<MoneyUserInfo> {
	@Autowired
	private  MoneyUserInfoService moneyUserInfoService;
	
	public MoneyUserInfo get(String id) {
		return moneyUserInfoService.get(id);
	}
	
	public MoneyUserInfo get(MoneyUserInfo entity) {
		return moneyUserInfoService.get(entity);
	}
	
	public List<MoneyUserInfo> findList(MoneyUserInfo moneyUserInfo) {
		return moneyUserInfoService.findList(moneyUserInfo);
	}
	
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public Page<MoneyUserInfo> findPage(Page<MoneyUserInfo> page, MoneyUserInfo moneyUserInfo) {
		return moneyUserInfoService.findPage(page, moneyUserInfo);
	}
	 
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void save(MoneyUserInfo moneyUserInfo) {
		moneyUserInfoService.save(moneyUserInfo);
	}
	 
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void delete(MoneyUserInfo moneyUserInfo) {
		moneyUserInfoService.delete(moneyUserInfo);
	}
	
}