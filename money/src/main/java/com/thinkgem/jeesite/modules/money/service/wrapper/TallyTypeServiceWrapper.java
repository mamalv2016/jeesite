/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.service.wrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renjie120.common.annotation.log.LoggerPoint;
import com.renjie120.common.enums.PointerKey;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ICurdService;
import com.thinkgem.jeesite.modules.money.entity.TallyType;
import com.thinkgem.jeesite.modules.money.service.TallyTypeService;

/**
 * 封装对数据库的业务操作类
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class TallyTypeServiceWrapper implements ICurdService<TallyType> {
	@Autowired
	private TallyTypeService tallyTypeService;

	public TallyType get(String id) {
		return tallyTypeService.get(id);
	}

	public TallyType get(TallyType entity) {
		return tallyTypeService.get(entity);
	}

	public List<TallyType> findList(TallyType entity) { 
		return tallyTypeService.findList(entity);
	}


	public Page<TallyType> findPage(Page<TallyType> page, TallyType entity) { 
		return tallyTypeService.findPage(page, entity);
	}

	public void save(TallyType entity) {
		tallyTypeService.save(entity);
	}

	public void delete(TallyType entity) {
		tallyTypeService.delete(entity);
	}

}
