/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneyuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.renjie120.modules.moneyuser.entity.MoneyUserInfo;
import com.renjie120.modules.moneyuser.dao.MoneyUserInfoDao;

/**
 * 理财系统用户Service
 * @author renjie120
 * @version 2017-04-22
 */
@Service
@Transactional(readOnly = true)
public class MoneyUserInfoService extends CrudService<MoneyUserInfoDao, MoneyUserInfo> {

	public MoneyUserInfo get(String id) {
		return super.get(id);
	}
	
	public List<MoneyUserInfo> findList(MoneyUserInfo moneyUserInfo) {
		return super.findList(moneyUserInfo);
	}
	
	public Page<MoneyUserInfo> findPage(Page<MoneyUserInfo> page, MoneyUserInfo moneyUserInfo) {
		return super.findPage(page, moneyUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MoneyUserInfo moneyUserInfo) {
		super.save(moneyUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MoneyUserInfo moneyUserInfo) {
		super.delete(moneyUserInfo);
	}
	
}