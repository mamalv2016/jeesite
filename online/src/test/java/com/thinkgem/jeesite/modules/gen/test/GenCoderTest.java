package com.thinkgem.jeesite.modules.gen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.renjie120.common.utils.JsonUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenScheme;
import com.thinkgem.jeesite.modules.gen.service.GenSchemeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context-test.xml" })
public class GenCoderTest {
	@Autowired
	private GenSchemeService genSchemeService;
	
	@Test
	public void testGenCode() {
		GenScheme scheme   = genSchemeService.get("d23a24d4afb44709bb2a7448b272b1fe");
		scheme.setFlag("1");
		scheme.setReplaceFile(true);
		System.out.println(scheme.getDbName());
		genSchemeService.save(scheme);
		System.out.println(1);
	}
}
