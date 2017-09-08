package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface NotNull {
	String message() default "对象不能为空";
}
