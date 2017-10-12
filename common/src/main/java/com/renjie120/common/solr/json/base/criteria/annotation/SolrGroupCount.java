package com.renjie120.common.solr.json.base.criteria.annotation;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface SolrGroupCount {
	
	String field() default "";
	
	int limit() default -1;

}
