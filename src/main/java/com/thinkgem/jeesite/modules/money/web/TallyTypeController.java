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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.money.entity.TallyType;
import com.thinkgem.jeesite.modules.money.service.TallyTypeService;

/**
 * 字典Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/money/tallytype")
public class TallyTypeController extends BaseController {

	@Autowired
	private TallyTypeService dictService;
	
	@ModelAttribute
	public TallyType get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return dictService.get(id);
		}else{
			return new TallyType();
		}
	}
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = {"list", ""})
	public String list(TallyType dict, HttpServletRequest request, HttpServletResponse response, Model model) { 
        Page<TallyType> page = dictService.findPage(new Page<TallyType>(request, response), dict); 
        model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(TallyType dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(TallyType dict, Model model, RedirectAttributes redirectAttributes) { 
		dictService.save(dict); 
		return "redirect:" + adminPath + "/sys/dict/?repage&type=";
	}  
}
