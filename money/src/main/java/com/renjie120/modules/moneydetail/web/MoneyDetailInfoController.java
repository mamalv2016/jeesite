/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.renjie120.modules.moneydetail.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renjie120.common.utils.JsonUtils;
import com.renjie120.math.CalcInput;
import com.renjie120.math.CalcStrategyManager;
import com.renjie120.math.tool.MathTool;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.renjie120.modules.moneydetail.entity.MoneyDetailInfo;
import com.renjie120.modules.moneydetail.service.wrapper.MoneyDetailInfoServiceWrapper;

import java.io.IOException;
import java.util.List;

/**
 * 金额列表Controller
 * @author renjie120
 * @version 2017-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/moneydetail/moneyDetailInfo")
public class MoneyDetailInfoController extends BaseController {

	@Autowired
	private MoneyDetailInfoServiceWrapper moneyDetailInfoService;
	
	@ModelAttribute
	public MoneyDetailInfo get(@RequestParam(required=false) String id) {
		MoneyDetailInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = moneyDetailInfoService.get(id);
		}
		if (entity == null){
			entity = new MoneyDetailInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("moneydetail:moneyDetailInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MoneyDetailInfo moneyDetailInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MoneyDetailInfo> page = moneyDetailInfoService.findPage(new Page<MoneyDetailInfo>(request, response), moneyDetailInfo); 
		model.addAttribute("page", page);
		return "modules/moneydetail/moneyDetailInfoList";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:view")
	@RequestMapping(value = "form")
	public String form(MoneyDetailInfo moneyDetailInfo, Model model) {
		model.addAttribute("moneyDetailInfo", moneyDetailInfo);
		return "modules/moneydetail/moneyDetailInfoForm";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:edit")
	@RequestMapping(value = "save")
	public String save(MoneyDetailInfo moneyDetailInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, moneyDetailInfo)){
			return form(moneyDetailInfo, model);
		}
		moneyDetailInfoService.save(moneyDetailInfo);
		addMessage(redirectAttributes, "保存金额列表成功");
		return "redirect:"+Global.getAdminPath()+"/moneydetail/moneyDetailInfo/?repage";
	}
	
	@RequiresPermissions("moneydetail:moneyDetailInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MoneyDetailInfo moneyDetailInfo, RedirectAttributes redirectAttributes) {
		moneyDetailInfoService.delete(moneyDetailInfo);
		addMessage(redirectAttributes, "删除金额列表成功");
		return "redirect:"+Global.getAdminPath()+"/moneydetail/moneyDetailInfo/?repage";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(MoneyDetailInfo moneyDetailInfo, RedirectAttributes redirectAttributes) {
		moneyDetailInfoService.deleteAll(moneyDetailInfo);
		addMessage(redirectAttributes, "批量删除金额列表成功");
		return "redirect:"+Global.getAdminPath()+"/moneydetail/moneyDetailInfo/?repage";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:edit")
	@RequestMapping(value = {"import"})
	public String importMoneys(HttpServletRequest request, HttpServletResponse response ) {
		return "modules/moneydetail/moneyDetailImport";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:view")
	@RequestMapping(value = {"calcInput"})
	public String calcInput( Model model,HttpServletRequest request, HttpServletResponse response ) {
		model.addAttribute("calcInput",new CalcInput());
		return "modules/moneydetail/moneyCalcForm";
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:view")
	@RequestMapping(value = {"calc"})
	public String calc(CalcInput calcInput, Model model,HttpServletRequest request, HttpServletResponse response ) {
			calcInput.setFee(MathTool.divide(calcInput.getFee(),100,4));
			calcInput.setYear ((int)MathTool.divide(calcInput.getMonth(),12,2));
			String ans = CalcStrategyManager.getHtml(calcInput,"test");
			try {
				logger.info("计算公式："+ JsonUtils.toJsonStr(calcInput));
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(ans);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			return null;
	}

	@RequiresPermissions("moneydetail:moneyDetailInfo:edit")
	@RequestMapping(value = {"doImport"})
	public String doImportMoneys(@RequestParam("imageFile") CommonsMultipartFile file , HttpServletRequest request, HttpServletResponse response ) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
		//用来检测程序运行时间
		long  startTime=System.currentTimeMillis();
		List<MoneyDetailInfo> data =  null;
		try {
			ImportExcel imp = new ImportExcel("test.xls", file.getInputStream(), 0, 0);

			data = imp.getDataList(MoneyDetailInfo.class, 1);

			moneyDetailInfoService.batchSave(data);
	}catch(Exception e){
		logger.error(e.getMessage(),e);
	}
		return "modules/moneydetail/moneyDetailImport";
	}
}