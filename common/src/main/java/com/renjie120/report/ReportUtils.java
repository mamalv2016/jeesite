package com.renjie120.report;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class ReportUtils {
	private List<TestVo> datas;

	public List<TestVo> getDatas() {
		return datas;
	}

	public void setDatas(List<TestVo> datas) {
		this.datas = datas;
	} 

	List<Object[]> annotationList = Lists.newArrayList();

	Table table = new Table();

	private List<Sum> sums = Lists.newArrayList();
	
	private List<Count> counts = Lists.newArrayList();

	private List<Group> groups = Lists.newArrayList();

	public ReportUtils(String title, Class<?> cls) {
		ReportTable tb = cls.getAnnotation(ReportTable.class); 
		Preconditions.checkArgument(tb != null, cls.getName()
				+ "未实现注解ReportTable!"); 
		table.dbTable = tb.dbTable();
		table.dbWhere = tb.dbWhere();

		// 循环全部的属性,找到里面的group列，sum列
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			ReportGroup reportGroup = f.getAnnotation(ReportGroup.class);
			if (reportGroup != null) {
				annotationList.add(new Object[] { reportGroup, f });
				Group g = new Group();
				g.dbCoulmn = reportGroup.dbColumn();
				g.desc = StringUtils.isEmpty(reportGroup.desc())?g.dbCoulmn:reportGroup.desc();
				groups.add(g);
			}
			
			ReportCount reportCount = f.getAnnotation(ReportCount.class);
			if (reportCount != null) {
				annotationList.add(new Object[] { reportCount, f });
				Count c = new Count();
				c.dbCoulmn = reportCount.dbColumn();
				c.alias = StringUtils.isEmpty(reportCount.alias())?c.dbCoulmn:reportCount.alias();
				c.desc = StringUtils.isEmpty(reportCount.desc())?c.dbCoulmn:reportCount.desc();
				counts.add(c);
			}

			ReportSum reportSum = f.getAnnotation(ReportSum.class);
			if (reportSum != null) {
				annotationList.add(new Object[] { reportSum, f });
				Sum s = new Sum();
				s.dbCoulmn = reportSum.dbColumn();
				s.alias = StringUtils.isEmpty(reportSum.alias())?s.dbCoulmn:reportSum.alias();
				s.desc = StringUtils.isEmpty(reportSum.desc())?s.dbCoulmn:reportSum.desc();
				sums.add(s);
			}
		}
		
		Preconditions.checkArgument(groups.size()>0, cls.getName()+ "必须含有@ReportGroup注解的属性!");
		Preconditions.checkArgument((counts.size()+sums.size())>0, cls.getName()+ "必须至少含有@ReportSum/@ReportCount之一注解的属性!");
	}

	public String getReportSql() {
		StringBuilder sql = new StringBuilder(128);
		sql.append("select ");
		for (Sum s : sums) {
			sql.append(" sum(").append(s.dbCoulmn).append(") AS ").append(s.alias).append(" ,");
		}
		
		for (Count c : counts) {
			sql.append(" count(").append(c.dbCoulmn).append(") AS ").append(c.alias).append(" ,");
		}
		
		sql = sql.deleteCharAt(sql.lastIndexOf(","));
		
		sql.append(" from ").append(table.dbTable);

		if (!StringUtils.isEmpty(table.dbWhere)) {
			sql.append(" where ").append(table.dbWhere);
		}
		if (!CollectionUtils.isEmpty(groups)) {
			sql.append(" group by ");
			for (Group g : groups) {
				sql.append(g.dbCoulmn).append(" ,");
			}
			sql = sql.deleteCharAt(sql.lastIndexOf(","));
		}
		return sql.toString();
	} 

	public static void main(String[] as) {
		List<TestVo> datas = Lists.newArrayList();
		TestVo v1 = new TestVo();
		v1.setMoney(123);
		v1.setYear("2016");
		TestVo v2 = new TestVo();
		v2.setMoney(1333);
		v2.setYear("2018");
		datas.add(v2);
		datas.add(v1);

		ReportUtils report = new ReportUtils("test", TestVo.class);
		System.out.println(report.getReportSql());
	}
	
	class Sum {
		String dbCoulmn;
		String desc;
		String alias;
	}

	class Count {
		String dbCoulmn;
		String desc;
		String alias;
	}
	
	class Group {
		String dbCoulmn;
		String desc;
	}

	class Table {
		String dbTable;
		String dbWhere;
	}
}
