/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.money.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.money.entity.TallyType;
import com.thinkgem.jeesite.modules.money.service.wrapper.TallyTypeServiceWrapper;
import com.thinkgem.jeesite.modules.money.utils.MoneyUtils;
/**
 * 字典Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/money/tallytype")
public class TallyTypeController extends BaseController {

	@Autowired
	private TallyTypeServiceWrapper tallyTypeService;
	
	@ModelAttribute
	public TallyType get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return tallyTypeService.get(id);
		}else{
			return new TallyType();
		}
	}
	
	@RequiresPermissions("money:tallytype:view")
	@RequestMapping(value = {"list", ""})
	public String list(TallyType tallyType, HttpServletRequest request, HttpServletResponse response, Model model) { 
        Page<TallyType> page = tallyTypeService.findPage(new Page<TallyType>(request, response), tallyType); 
        model.addAttribute("page", page); 
		return "modules/money/tallyTypeList";
	}

	@RequiresPermissions("money:tallytype:edit")
	@RequestMapping(value = "form")
	public String form(TallyType tallyType, Model model) {
		model.addAttribute("tallyType", tallyType);
		model.addAttribute("types",MoneyUtils.getTallyTypes());
		return "modules/money/tallyTypeForm";
	}

	@RequiresPermissions("money:tallytype:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(TallyType tallyType, Model model, RedirectAttributes redirectAttributes) { 
		tallyTypeService.save(tallyType);  
		return "redirect:" + adminPath + "/money/tallytype";
	}  
	
	@RequiresPermissions("money:tallytype:edit")
	@RequestMapping(value = "delete")
	public String delete(TallyType tallyType, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:modules/money/tallyTypeList";
		}
		tallyTypeService.delete(tallyType);
		addMessage(redirectAttributes, "删除类型成功");
		return "redirect:modules/money/tallyTypeList";
	}
}
