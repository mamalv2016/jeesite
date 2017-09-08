package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD })
public @interface Length {
	int min();
	int max();
	String message() default "超出输入字符串长度";
}
