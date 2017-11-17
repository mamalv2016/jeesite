<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>金额列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		<li><a href="${ctx}/moneydetail/moneyDetailInfo/">金额列表列表</a></li>
		<li class="active"><a href="${ctx}/moneydetail/moneyDetailInfo/form?id=${moneyDetailInfo.id}">金额列表<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit">${not empty moneyDetailInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="moneydetail:moneyDetailInfo:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><li><a href="${ctx}/moneydetail/moneyDetailInfo/import">金额列表导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="moneyDetailInfo" action="${ctx}/moneydetail/moneyDetailInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input name="moneyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${moneyDetailInfo.moneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="moneyType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getTallyTypeList()}" itemLabel="moneyTypeDesc" itemValue="typeCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="moneyDesc" htmlEscape="false" maxlength="600" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用卡：</label>
			<div class="controls">
				<form:input path="shopcard" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效：</label>
			<div class="controls">
				<form:input path="useful" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卡类型：</label>
			<div class="controls">
				<form:input path="booktype" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否拆分：</label>
			<div class="controls">
				<form:input path="splitno" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际金额：</label>
			<div class="controls">
				<form:input path="realMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户名：</label>
			<div class="controls">
				<form:select path="username" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getMoneyUserList()}" itemLabel="username" itemValue="loginid" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>