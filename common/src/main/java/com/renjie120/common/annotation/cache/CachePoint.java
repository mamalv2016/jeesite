package com.renjie120.common.annotation.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD })
public @interface CachePoint {
	CacheBoxConvert value();

	String expireTimeKey();

	//int sizeLimit() default 1;

	boolean enable() default true;
}
