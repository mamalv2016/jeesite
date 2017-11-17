<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>金额列表导入</title>
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
		<li ><a href="${ctx}/moneydetail/moneyDetailInfo/">金额明细列表</a></li>
		<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><li><a href="${ctx}/moneydetail/moneyDetailInfo/form">金额列表添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><li class="active"><a href="${ctx}/moneydetail/moneyDetailInfo/import">金额列表导入</a></li></shiro:hasPermission>
	</ul>
	 <form:form id="inputForm" modelAttribute="tallyType"  enctype="multipart/form-data"  action="${ctx}/moneydetail/moneyDetailInfo/doImport" method="post" class="form-horizontal">  
		<div class="control-group">
			<label class="control-label">上传模板:</label>
			<div class="controls">
				<input type='file' name="imageFile"/>
			</div>
		</div> 
		<div class="form-actions">
			<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="导入"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<sys:message content="${message}"/> 
</body>
</html>