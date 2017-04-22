/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.renjie120.modules.moneyuser.entity.MoneyUserInfo;
import com.renjie120.modules.moneyuser.service.wrapper.MoneyUserInfoServiceWrapper;

/**
 * 理财系统用户Controller
 * @author renjie120
 * @version 2017-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/moneyuser/moneyUserInfo")
public class MoneyUserInfoController extends BaseController {

	@Autowired
	private MoneyUserInfoServiceWrapper moneyUserInfoService;
	
	@ModelAttribute
	public MoneyUserInfo get(@RequestParam(required=false) String id) {
		MoneyUserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = moneyUserInfoService.get(id);
		}
		if (entity == null){
			entity = new MoneyUserInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("moneyuser:moneyUserInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MoneyUserInfo moneyUserInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MoneyUserInfo> page = moneyUserInfoService.findPage(new Page<MoneyUserInfo>(request, response), moneyUserInfo); 
		model.addAttribute("page", page);
		return "modules/moneyuser/moneyUserInfoList";
	}

	@RequiresPermissions("moneyuser:moneyUserInfo:view")
	@RequestMapping(value = "form")
	public String form(MoneyUserInfo moneyUserInfo, Model model) {
		model.addAttribute("moneyUserInfo", moneyUserInfo);
		return "modules/moneyuser/moneyUserInfoForm";
	}

	@RequiresPermissions("moneyuser:moneyUserInfo:edit")
	@RequestMapping(value = "save")
	public String save(MoneyUserInfo moneyUserInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, moneyUserInfo)){
			return form(moneyUserInfo, model);
		}
		moneyUserInfoService.save(moneyUserInfo);
		addMessage(redirectAttributes, "保存理财系统用户成功");
		return "redirect:"+Global.getAdminPath()+"/moneyuser/moneyUserInfo/?repage";
	}
	
	@RequiresPermissions("moneyuser:moneyUserInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MoneyUserInfo moneyUserInfo, RedirectAttributes redirectAttributes) {
		moneyUserInfoService.delete(moneyUserInfo);
		addMessage(redirectAttributes, "删除理财系统用户成功");
		return "redirect:"+Global.getAdminPath()+"/moneyuser/moneyUserInfo/?repage";
	}

}