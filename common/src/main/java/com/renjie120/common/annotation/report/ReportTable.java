package com.renjie120.common.annotation.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportTable {
	String dbTable(); // 描述数据库中对应的表名

	String dbWhere() default "";// 报表查询数据的时候，添加一些条件sql
}
