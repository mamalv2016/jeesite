package com.renjie120.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renjie120.common.annotation.log.LoggerPoint;
import com.renjie120.common.enums.PointerKey;
 
@Component
public class TestPointer {
	public static Logger logger = LoggerFactory.getLogger(TestPointer.class);

	@Autowired
	private TestPointer2 testPointer2;

	@LoggerPoint(pointKey=PointerKey.Test)
	public String testSth(String asa){
		String ans =  testPointer2.sayHello(asa);
		return ans;
	}
}
