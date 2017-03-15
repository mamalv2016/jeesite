/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.money.dao.TallyTypeDao;
import com.thinkgem.jeesite.modules.money.entity.TallyType;
import com.thinkgem.jeesite.modules.money.utils.TallyTypeUtils;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class TallyTypeService extends CrudService<TallyTypeDao, TallyType> { 

	@Transactional(readOnly = false)
	public void save(TallyType dict) {
		super.save(dict);
		CacheUtils.remove(TallyTypeUtils.CACHE_TALLYTYPE_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(TallyType dict) {
		super.delete(dict);
		CacheUtils.remove(TallyTypeUtils.CACHE_TALLYTYPE_MAP);
	}

}
