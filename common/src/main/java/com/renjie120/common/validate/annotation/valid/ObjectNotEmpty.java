package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface ObjectNotEmpty {
	String message() default "对象不能为空";
}
