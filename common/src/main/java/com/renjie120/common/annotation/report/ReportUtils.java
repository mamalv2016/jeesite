package com.renjie120.common.annotation.report;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.renjie120.common.enums.Condition;
import com.renjie120.common.utils.JsonUtils;

public class ReportUtils<T> {
	// @Autowired
	// private DataSource dataSource;

	private List<TestVo> datas;

	public List<TestVo> getDatas() {
		return datas;
	}

	public void setDatas(List<TestVo> datas) {
		this.datas = datas;
	}

	List<Object[]> annotationList = Lists.newArrayList();

	Tb table = new Tb();

	private List<S> sums = Lists.newArrayList();

	private List<C> counts = Lists.newArrayList();

	private List<G> groups = Lists.newArrayList();

	private List<W> wheres = Lists.newArrayList();

	private Map<String, Field> fieldsMap = Maps.newHashMap();

	// public ReportUtils(String title, Class<?> vo) {
	//
	// }

	private String genMapKey(Object obj) {
		StringBuilder sb = new StringBuilder(64);
		if (obj instanceof ReportUtils.G) {
			ReportUtils.G g = (ReportUtils.G) obj;
			sb.append("G$" + g.dbCoulmn);
		} else if (obj instanceof ReportUtils.S) {
			ReportUtils.S s = (ReportUtils.S) obj;
			sb.append("S$" + s.dbCoulmn);
		} else if (obj instanceof ReportUtils.C) {
			ReportUtils.C c = (ReportUtils.C) obj;
			sb.append("C$" + c.dbCoulmn);
		} else if (obj instanceof ReportUtils.W) {
			ReportUtils.W w = (ReportUtils.W) obj;
			sb.append("W$" + w.dbCoulmn + "$" + w.condition.name());
		}
		return sb.toString();
	}

	private boolean haveWhere = false;

	private String getWhereSql(ReportUtils.W w) {
		StringBuilder sb = new StringBuilder(64);
		if (haveWhere) {
			sb.append(" and ");
		}
		sb.append(w.dbCoulmn);
		Condition c = w.condition;
		Field f = fieldsMap.get(genMapKey(w));
		Object valueObj = getFieldValue(vo, f.getName());

		if (c == Condition.CONTAIN) {
			String json = JsonUtils.toJsonStr(valueObj);
			JSONArray array = JSON.parseArray(json);
			sb.append(" in (");
			for (Object obj : array) {
				sb.append(obj instanceof Number ? obj : "'" + obj + "'");
				sb.append(",");
			}
			sb = sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append(")");
		} else if (c == Condition.EQUAL) {
			sb.append(" = ").append(valueObj);
		} else if (c == Condition.GREATER) {
			sb.append(" > ").append(valueObj);
		} else if (c == Condition.GREATER_EQUAL) {
			sb.append(" >= ").append(valueObj);
		} else if (c == Condition.LESS) {
			sb.append(" < ").append(valueObj);
		} else if (c == Condition.LESS_EQUAL) {
			sb.append(" <= ").append(valueObj);
		} else if (c == Condition.LIKE) {
			sb.append(" like '%").append(valueObj).append("%'");
		} else if (c == Condition.NOT_CONTAIN) {
			String json = JsonUtils.toJsonStr(valueObj);
			JSONArray array = JSON.parseArray(json);
			sb.append(" not in (");
			for (Object obj : array) {
				sb.append(obj instanceof Number ? obj : "'" + obj + "'");
				sb.append(",");
			}
			sb = sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append(")");
		} else if (c == Condition.NOT_EQUAL) {
			sb.append(" != ").append(valueObj);
		} else if (c == Condition.NOT_LIKE) {
			sb.append(" not like '%").append(valueObj).append("%'");
		}
		haveWhere = true;
		return sb.toString();
	}

	private Object getFieldValue(Object thisClass, String fieldName) {
		Object value = new Object();
		Method method = null;
		try {
			String methodName = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			method = thisClass.getClass().getMethod("get" + methodName);
			value = method.invoke(thisClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	private T vo;

	public ReportUtils(String title, T vo) {
		this.vo = vo;
		Class<?> cls = vo.getClass();
		System.out.println(vo.getClass());
		ReportTable tb = cls.getAnnotation(ReportTable.class);
		Preconditions.checkArgument(tb != null, cls.getName()
				+ "未实现注解ReportTable!");
		table.dbTable = tb.dbTable();
		// table.dbWhere = tb.dbWhere();

		// 循环全部的属性,找到里面的group列，sum列
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			Group reportGroup = f.getAnnotation(Group.class);
			if (reportGroup != null) {
				annotationList.add(new Object[] { reportGroup, f });
				G g = new G();
				g.dbCoulmn = reportGroup.dbColumn();

				g.order = reportGroup.order();

				g.desc = StringUtils.isEmpty(reportGroup.desc()) ? g.dbCoulmn
						: reportGroup.desc();
				groups.add(g);

				fieldsMap.put(genMapKey(g), f);
			}

			Count reportCount = f.getAnnotation(Count.class);
			if (reportCount != null) {
				annotationList.add(new Object[] { reportCount, f });
				C c = new C();
				c.dbCoulmn = reportCount.dbColumn();
				c.alias = StringUtils.isEmpty(reportCount.alias()) ? c.dbCoulmn
						: reportCount.alias();
				c.desc = StringUtils.isEmpty(reportCount.desc()) ? c.dbCoulmn
						: reportCount.desc();
				c.distinct = reportCount.distinct();
				counts.add(c);

				fieldsMap.put(genMapKey(c), f);
			}

			Sum reportSum = f.getAnnotation(Sum.class);
			if (reportSum != null) {
				annotationList.add(new Object[] { reportSum, f });
				S s = new S();
				s.dbCoulmn = reportSum.dbColumn();
				s.alias = StringUtils.isEmpty(reportSum.alias()) ? s.dbCoulmn
						: reportSum.alias();
				s.desc = StringUtils.isEmpty(reportSum.desc()) ? s.dbCoulmn
						: reportSum.desc();
				sums.add(s);

				fieldsMap.put(genMapKey(s), f);
			}

			Where whereColumn = f.getAnnotation(Where.class);
			if (whereColumn != null) {
				annotationList.add(new Object[] { whereColumn, f });
				W w = new W();
				w.dbCoulmn = whereColumn.dbColumn();
				w.condition = whereColumn.condition();
				wheres.add(w);
				fieldsMap.put(genMapKey(w), f);
			}
		}

		System.out.println(fieldsMap.keySet());
		Preconditions.checkArgument(groups.size() > 0, cls.getName()
				+ "必须含有@ReportGroup注解的属性!");
		Preconditions.checkArgument((counts.size() + sums.size()) > 0,
				cls.getName() + "必须至少含有@ReportSum/@ReportCount之一注解的属性!");
	}

	public String getReportSql() {
		// JdbcTemplate template = new JdbcTemplate(dataSource);

		StringBuilder sql = new StringBuilder(128);
		sql.append("select ");
		for (S s : sums) {
			sql.append(" sum(").append(s.dbCoulmn).append(") AS ")
					.append(s.alias).append(" ,");
		}

		for (C c : counts) {
			sql.append(" count(").append(c.distinct ? " distinct " : "")
					.append(c.dbCoulmn).append(") AS ").append(c.alias)
					.append(" ,");
		}

		sql = sql.deleteCharAt(sql.lastIndexOf(","));

		sql.append(" from ").append(table.dbTable);

		if (!CollectionUtils.isEmpty(wheres)) {
			sql.append(" where ");
			for (W w : wheres) {
				sql.append(getWhereSql(w));
			}
		} 

		Collections.sort(groups, new Comparator<ReportUtils.G>() {
			@Override
			public int compare(ReportUtils.G o1, ReportUtils.G o2) {
				if (o1.order > o2.order) {
					return 1;
				} else if (o1.order < o2.order) {
					return -1;
				} else
					return 0;
			} 
		});

		if (!CollectionUtils.isEmpty(groups)) {
			sql.append(" group by ");
			for (G g : groups) {
				sql.append(g.dbCoulmn).append(" ,");
			}
			sql = sql.deleteCharAt(sql.lastIndexOf(","));
		}
		return sql.toString();
	}

	public static void main(String[] as) {
		TestVo vo = new TestVo();
		vo.setMoney(123);
		vo.setBigMoney(123);
		vo.setLikeName("lishuiqing");
		List<String> ans = Lists.newArrayList();
		ans.add("2016");
		ans.add("2017");
		vo.setYears(ans);
		vo.setSmallMoney(44);

		int[] users = new int[] { 1, 2, 3, 4 };
		vo.setUsers(users);
		ReportUtils report = new ReportUtils("test", vo);
		System.out.println(report.getReportSql());
		System.out.println(vo.getMoney());
	}

	class S {
		String dbCoulmn;
		String desc;
		String alias;
	}

	class W {
		String dbCoulmn;
		Condition condition;
	}

	class C {
		String dbCoulmn;
		String desc;
		String alias;
		boolean distinct;
	}

	class G {
		String dbCoulmn;
		String desc;
		int order;
	}

	class Tb {
		String dbTable;
		// String dbWhere;
	}
}
