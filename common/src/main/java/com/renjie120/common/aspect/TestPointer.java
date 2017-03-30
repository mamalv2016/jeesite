package com.renjie120.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class TestPointer {
	public static Logger logger = LoggerFactory.getLogger(TestPointer.class);

	@Autowired
	private TestPointer2 testPointer2;

	
	public String testSth(Object asa){
		String ans =  testPointer2.testSth(asa);
		return ans;
	}
}
