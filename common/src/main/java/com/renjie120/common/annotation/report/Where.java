package com.renjie120.common.annotation.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.renjie120.common.enums.Condition;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME) 
public @interface Where {  
	String dbColumn();  //描述数据库中对应的字段
	
	Condition condition()  ;  //判断条件 
}
