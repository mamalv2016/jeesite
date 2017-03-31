package com.renjie120.common.annotation.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME) 
public @interface Count {
	String desc() default "";  //描述数值的内容信息.
	
	String dbColumn();  //描述数据库中对应的字段
	
	String alias() default "";  //查询字段别名
	
	boolean distinct() default false;//是否去重.
}
