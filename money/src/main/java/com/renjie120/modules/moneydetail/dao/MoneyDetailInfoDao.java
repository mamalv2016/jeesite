/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneydetail.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.renjie120.modules.moneydetail.entity.MoneyDetailInfo;

/**
 * 金额列表DAO接口
 * @author renjie120
 * @version 2017-04-22
 */
@MyBatisDao
public interface MoneyDetailInfoDao extends CrudDao<MoneyDetailInfo> {
	
}