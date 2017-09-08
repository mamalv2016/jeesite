package com.renjie120.common.validate.annotation.valid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface NotZero {
	String message() default "属性值不为零";
}
