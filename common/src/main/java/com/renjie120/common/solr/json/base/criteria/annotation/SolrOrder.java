package com.renjie120.common.solr.json.base.criteria.annotation;

import com.renjie120.common.solr.json.base.criteria.enums.SolrOrderTYPE;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface SolrOrder {
	
	String name() default "";
	/**
	 * to
	 * like
	 * eq
	 * >
	 * <
	 * noteq
	 * or
	 * @return
	 */
	SolrOrderTYPE sort() default SolrOrderTYPE.ASC;

	String sortfield() default "";
	
}
