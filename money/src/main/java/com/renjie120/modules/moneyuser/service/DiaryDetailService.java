package com.renjie120.modules.moneyuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renjie120.modules.moneyuser.dao.DiaryDetailDao;
import com.renjie120.modules.moneyuser.entity.DiaryDetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
@Service
@Transactional(readOnly = true)
public class DiaryDetailService extends CrudService<DiaryDetailDao, DiaryDetail>{

	public DiaryDetail get(String id) {
		return super.get(id);
	}
	
	public List<DiaryDetail> findList(DiaryDetail moneyUserInfo) {
		return super.findList(moneyUserInfo);
	}
	
	public Page<DiaryDetail> findPage(Page<DiaryDetail> page, DiaryDetail moneyUserInfo) {
		return super.findPage(page, moneyUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(DiaryDetail moneyUserInfo) {
		super.save(moneyUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiaryDetail moneyUserInfo) {
		super.delete(moneyUserInfo);
	}

}
