package com.renjie120.modules.moneyuser.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renjie120.modules.moneyuser.entity.DiaryDetail;
import com.renjie120.modules.moneyuser.service.DiaryDetailService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
@Controller
@RequestMapping(value = "${adminPath}/moneyuser/diaryDetail")
public class DiaryDetailController extends BaseController{
	@Autowired
	private DiaryDetailService diaryDetailService;
	@ModelAttribute
	public DiaryDetail get(@RequestParam(required=false) String id) {
		DiaryDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diaryDetailService.get(id);
		}
		if (entity == null){
			entity = new DiaryDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("moneyuser:diaryDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DiaryDetail DiaryDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DiaryDetail> page = diaryDetailService.findPage(new Page<DiaryDetail>(request, response), DiaryDetail); 
		model.addAttribute("page", page);
		return "modules/moneyuser/diaryDetail";
	}

	@RequiresPermissions("moneyuser:diaryDetail:view")
	@RequestMapping(value = "form")
	public String form(DiaryDetail DiaryDetail, Model model) {
		model.addAttribute("DiaryDetail", DiaryDetail);
		return "modules/moneyuser/diaryDetailForm";
	}

	@RequiresPermissions("moneyuser:diaryDetail:edit")
	@RequestMapping(value = "save")
	public String save(DiaryDetail DiaryDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, DiaryDetail)){
			return form(DiaryDetail, model);
		}
		diaryDetailService.save(DiaryDetail);
		addMessage(redirectAttributes, "保存流水用户成功");
		return "redirect:"+Global.getAdminPath()+"/moneyuser/diaryDetail/?repage";
	}
	


	@RequiresPermissions("moneyuser:diaryDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DiaryDetail DiaryDetail, RedirectAttributes redirectAttributes) {
		diaryDetailService.delete(DiaryDetail);
		addMessage(redirectAttributes, "删除流水用户成功");
		return "redirect:"+Global.getAdminPath()+"/moneyuser/diaryDetail/?repage";
	}

}

