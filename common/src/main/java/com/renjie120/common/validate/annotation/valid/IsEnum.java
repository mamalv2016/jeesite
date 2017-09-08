package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface IsEnum
{
	String enumType();//要检测的enum的类型
	String message() default "选择日期必须大于当前时间";
}
