package com.renjie120.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.renjie120.common.annotation.log.LoggerPoint;
import com.renjie120.common.enums.PointerKey;
import com.renjie120.common.utils.JsonUtils;
 
@Component
public class TestPointer2  {
	public static Logger logger = LoggerFactory.getLogger(TestPointer2.class); 
  
	@LoggerPoint(pointKey=PointerKey.Test)
	public String sayHello(String name) {
		System.out.println("执行pointer2");
		if(name==null)
			return "";
		else
			return JsonUtils.toJsonStr(name);
	}
}
