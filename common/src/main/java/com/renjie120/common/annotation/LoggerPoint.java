package com.renjie120.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.renjie120.common.enums.PointerKey;
@Retention(RetentionPolicy.RUNTIME) 
@Target({ ElementType.METHOD })
public @interface LoggerPoint {
	public PointerKey pointKey();
}
