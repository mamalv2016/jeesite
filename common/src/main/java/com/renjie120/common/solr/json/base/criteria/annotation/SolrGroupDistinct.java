package com.renjie120.common.solr.json.base.criteria.annotation;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface SolrGroupDistinct {
	
	String field() default "";
	int threshold() default 1;
	
}
