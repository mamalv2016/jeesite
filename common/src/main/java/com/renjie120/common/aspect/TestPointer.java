package com.renjie120.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.renjie120.common.utils.JsonUtils;
 
@Component
public class TestPointer {
	public static Logger logger = LoggerFactory.getLogger(TestPointer.class);

	public String testSth(Object asa){
		if(asa!=null)
			return "";
		else
			return JsonUtils.toJsonStr(asa);
	}
}
