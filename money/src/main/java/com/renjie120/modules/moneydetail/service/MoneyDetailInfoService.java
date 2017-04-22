/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneydetail.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.renjie120.modules.moneydetail.entity.MoneyDetailInfo;
import com.renjie120.modules.moneydetail.dao.MoneyDetailInfoDao;

/**
 * 金额列表Service
 * @author renjie120
 * @version 2017-04-22
 */
@Service
@Transactional(readOnly = true)
public class MoneyDetailInfoService extends CrudService<MoneyDetailInfoDao, MoneyDetailInfo> {

	public MoneyDetailInfo get(String id) {
		return super.get(id);
	}
	
	public List<MoneyDetailInfo> findList(MoneyDetailInfo moneyDetailInfo) {
		return super.findList(moneyDetailInfo);
	}
	
	public Page<MoneyDetailInfo> findPage(Page<MoneyDetailInfo> page, MoneyDetailInfo moneyDetailInfo) {
		return super.findPage(page, moneyDetailInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MoneyDetailInfo moneyDetailInfo) {
		super.save(moneyDetailInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MoneyDetailInfo moneyDetailInfo) {
		super.delete(moneyDetailInfo);
	}
	
}