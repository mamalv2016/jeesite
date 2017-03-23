<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>金额类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/money/tallytype/">金额类型列表</a></li>
		<shiro:hasPermission name="money:tallytype:edit"><li><a href="${ctx}/money/tallytype/form">类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tallyType" action="${ctx}/money/tallytype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> 
		&nbsp;&nbsp;<label>描述 ：</label><form:input path="moneyTypeDesc" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>主键</th><th>类型描述</th><th>收支类型</th><th>上级Code</th><th>code</th>
		<shiro:hasPermission name="money:tallytype:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="dict">
			<tr>
				<td>${dict.id}</td>
				<td>${dict.moneyTypeDesc}</td>
				<td><a href="javascript:" onclick="$('#type').val('${dict.moneyType}');$('#searchForm').submit();return false;">${dict.moneyType}</a></td>
				<td>${dict.parentCode}</td>
				<td>${dict.typeCode}</td> 
				<shiro:hasPermission name="money:tallytype:edit"><td>
    				<a href="${ctx}/money/tallytype/form?id=${dict.id}">修改</a>
					<a href="${ctx}/money/tallytype/delete?id=${dict.id}" onclick="return confirmx('确认要删除该类型吗？', this.href)">删除</a>
    		 	</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>