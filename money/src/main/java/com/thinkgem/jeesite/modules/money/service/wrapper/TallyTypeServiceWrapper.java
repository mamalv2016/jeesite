/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.service.wrapper;

import java.util.*;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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

	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

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

	/**
	 * 同步关于金额类型到字典缓存中.
	 * @param entity
	 */
	public void synMoneyTypeDict(TallyType entity) {
		//查询数据库中的类型字典表
		List<TallyType> list =  tallyTypeService.findList(null);
		Map<String,TallyType> map = new HashMap<String,TallyType>();
		for(TallyType tp:list){
			map.put(tp.getMoneyTypeDesc(),tp);
		}
		//原来的moneyType列表转储到字典表
		List<Dict> oldDict = DictUtils.getDictList("moneyType");
		for(Dict dict:oldDict){
			if(map.get(dict.getLabel())!=null){
				map.remove(dict.getLabel());
			}
		}

		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			TallyType tp = map.get(it.next());
			Dict d = changeTypeToDict(tp);
			UUID uuid = UUID.randomUUID();
			//设置id
			d.setSort(10);
			d.setId(uuid+"");
			User user = UserUtils.getUser();
			d.setCreateBy(user);
			d.setUpdateBy(user);
			d.setCreateDate(new Date());
			d.setUpdateDate(new Date());
			dictDao.insert(d);
		}

		DictUtils.getDictList("moneyType");
	}

	private Dict changeTypeToDict(TallyType tp){
		Dict dict = new Dict();
		dict.setDescription("金额类型");
		dict.setLabel(tp.getMoneyTypeDesc());
		dict.setValue(tp.getTypeCode());
		dict.setType("moneyType");
		return dict;
	}

	public void delete(TallyType entity) {
		tallyTypeService.delete(entity);
	}

}
