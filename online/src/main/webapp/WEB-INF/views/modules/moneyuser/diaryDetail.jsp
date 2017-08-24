<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流水记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		<li class="active"><a href="${ctx}/moneyuser/diaryDetail/">流水</a></li>
		<shiro:hasPermission name="moneyuser:diaryDetail:edit"><li><a href="${ctx}/moneyuser/diaryDetail/form">添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="diaryDetail" action="${ctx}/moneyuser/diaryDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流水号：</label>
			<form:input path="id" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		
			<tr>
			    <th>流水号</th>
			    <th>日记内容</th>
			    <th>时间</th>
			    <th>类型</th>
				<shiro:hasPermission name="moneyuser:diaryDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="diaryDetail">
			<tr>
					<td>${diaryDetail.id}</td>
					<td>${diaryDetail.content}</td>
					<td >${diaryDetail.time}</td>
					<td>${diaryDetail.type}</td>
				<shiro:hasPermission name="moneyuser:diaryDetail:edit"><td>
    				<a href="${ctx}/moneyuser/diaryDetail/form?id=${diaryDetail.id}">修改</a>
					<a href="${ctx}/moneyuser/diaryDetail/delete?id=${diaryDetail.id}" onclick="return confirmx('确认要删除该理财系统用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>