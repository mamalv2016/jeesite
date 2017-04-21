package com.thinkgem.jeesite.modules.gen.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenScheme;
import com.thinkgem.jeesite.modules.gen.service.GenSchemeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context-test.xml" })
public class GenCoderTest {
	@Autowired
	private GenSchemeService genSchemeService;
	
	String transferCity= "d23a24d4afb44709bb2a7448b272b1fe";//接送机城市
	
	String transferCityRel= "066951c312884d2ea7fe3841f7327e14";//关联城市
	
	String transferVendor= "aa715205e2924327be0f802a3745ce93";//服务供应商
	
	String transferVendorRel= "3a026dc1565c4ff99014ed9783b35a9c";//关联服务供应商
	@Test
	public void testGenCode() {
		Long l = 1492592445000L;
		Date d = new Date(l); 
		System.out.println(DateUtils.formatDateTime(d));
		GenScheme scheme   = genSchemeService.get(transferVendorRel);
		scheme.setFlag("1");
		scheme.setReplaceFile(true);
		System.out.println(scheme.getDbName());
		genSchemeService.save(scheme);
		System.out.println(1);
	}
	
	
}
