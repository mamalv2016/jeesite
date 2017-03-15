package com.thinkgem.jeesite.modules.money.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.money.entity.TallyType;

@MyBatisDao
public interface TallyTypeDao extends CrudDao<TallyType> { 
	
}
