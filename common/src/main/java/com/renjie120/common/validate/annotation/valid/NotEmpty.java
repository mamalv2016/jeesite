package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface NotEmpty {
	String message() default "字符串不能为空";
}
