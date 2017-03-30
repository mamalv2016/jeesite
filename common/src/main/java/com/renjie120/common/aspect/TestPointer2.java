package com.renjie120.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.renjie120.common.enums.PointerKey;
import com.renjie120.common.utils.JsonUtils;
 
@Component
public class TestPointer2 {
	public static Logger logger = LoggerFactory.getLogger(TestPointer2.class);

	@LoggerPoint(pointKey=PointerKey.Test)
	public String testSth(Object asa){
		if(asa==null)
			return "";
		else
			return JsonUtils.toJsonStr(asa);
	}
}
