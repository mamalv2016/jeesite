package com.renjie120.report.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME) 
public @interface Group {
	 String desc() default "";  //描述分类的汉字信息.
	 
	 String dbColumn() ;  //描述数据库中对应的字段
}
