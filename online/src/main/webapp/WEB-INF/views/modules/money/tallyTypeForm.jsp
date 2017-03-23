<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/money/tallytype/">金额类型列表</a></li>
		<li class="active"><a href="${ctx}/money/tallytype/form?id=${tallyType.id}">字典<shiro:hasPermission name="money:tallytype:edit">${not empty tallyType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="money:tallytype:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tallyType" action="${ctx}/money/tallytype/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/> 
		<div class="control-group">
			<label class="control-label">类型描述:</label>
			<div class="controls">
				<form:input path="moneyTypeDesc" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收支类型:</label>
			<div class="controls">
				<form:input path="moneyType" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级编码:</label>
			<div class="controls">
				<form:select path="parentCode" class="input-medium">
					<form:option     value="" htmlEscape="false">不选择</form:option>
					<form:options items="${fns:getTallyTypeList()}" itemLabel="moneyTypeDesc" itemValue="typeCode" htmlEscape="false"/>
				</form:select> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">code:</label>
			<div class="controls">
				<form:input path="typeCode" htmlEscape="false" maxlength="11" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序id:</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11"  class="required abc"/>				
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="money:tallytype:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>