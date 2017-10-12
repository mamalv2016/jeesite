/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import com.google.common.collect.Maps;
import com.renjie120.common.annotation.report.ReportUtils;
import com.renjie120.common.annotation.report.TestVo;
import com.renjie120.common.aspect.TestPointer;
import com.renjie120.common.utils.CustomizedPropertyPlaceholderConfigurer;
import com.renjie120.common.zkconfig.DynamicPropertiesHelper;
import com.renjie120.common.zkconfig.DynamicPropertiesHelperFactory;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.CookieUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.service.TestZkService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 关于zookeeper的操作的controller
 */
@Controller
public class ZookeeperController extends BaseController{
	@Autowired
	private ZkClient zkClient;
	@Autowired
	private DynamicPropertiesHelperFactory dynamicPropertiesHelperFactory;

	@Autowired
	private SessionDAO sessionDAO;

	@RequestMapping(value = "${adminPath}/changeZk", method = RequestMethod.GET)
	public String changeZk(HttpServletRequest request, HttpServletResponse response, Model model) {
		DynamicPropertiesHelper helper = this.dynamicPropertiesHelperFactory
				.getHelper("testPro2");
		String str = helper.getProperty("testname");
		System.out.println("testname="+str);
		helper.refresh("testPro2","testname="+System.currentTimeMillis());
		System.out.println("testname="+helper.getProperty("testname"));
		return null;
	}
}
