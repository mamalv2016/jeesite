/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneydetail.service.wrapper;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.renjie120.common.annotation.log.LoggerPoint;
import com.renjie120.common.enums.PointerKey;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ICurdService;
import com.renjie120.modules.moneydetail.entity.MoneyDetailInfo;
import com.renjie120.modules.moneydetail.service.MoneyDetailInfoService;

/**
 * 金额列表ServiceWrapper
 * @author renjie120
 * @version 2017-04-22
 */
@Service
public class MoneyDetailInfoServiceWrapper implements ICurdService<MoneyDetailInfo> {
	@Autowired
	private  MoneyDetailInfoService moneyDetailInfoService;
	
	public MoneyDetailInfo get(String id) {
		return moneyDetailInfoService.get(id);
	}
	
	public MoneyDetailInfo get(MoneyDetailInfo entity) {
		return moneyDetailInfoService.get(entity);
	}
	
	public List<MoneyDetailInfo> findList(MoneyDetailInfo moneyDetailInfo) {
		return moneyDetailInfoService.findList(moneyDetailInfo);
	}
	
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public Page<MoneyDetailInfo> findPage(Page<MoneyDetailInfo> page, MoneyDetailInfo moneyDetailInfo) {
		return moneyDetailInfoService.findPage(page, moneyDetailInfo);
	}
	 
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void save(MoneyDetailInfo moneyDetailInfo) {
		moneyDetailInfoService.save(moneyDetailInfo);
	}

	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void batchSave(List<MoneyDetailInfo> moneyDetailInfos) {
		if(!CollectionUtils.isEmpty(moneyDetailInfos)){
			for(MoneyDetailInfo moneyDetailInfo :moneyDetailInfos )
			{
				moneyDetailInfo.setUseful("1");
				moneyDetailInfoService.save(moneyDetailInfo);
			}
		}

	}
	 
	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void delete(MoneyDetailInfo moneyDetailInfo) {
		moneyDetailInfoService.delete(moneyDetailInfo);
	}

	@LoggerPoint(pointKey=PointerKey.UNKNOW)
	public void deleteAll(MoneyDetailInfo moneyDetailInfo) {
		moneyDetailInfoService.deleteAll(moneyDetailInfo);
	}
}