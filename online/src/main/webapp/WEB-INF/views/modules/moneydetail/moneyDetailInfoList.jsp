<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>金额列表管理</title>
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
		function batchDelete(){
			var ids  =[];
			$('input[type=checkbox][name=ids]:checked').each(function(){
				ids.push(this.value);
			});
			console.log("ids000"+ids); 
			var requestUrl = "${ctx}/moneydetail/moneyDetailInfo/deleteAll";
			$.ajax({
		       type: "post",
		       async: false,
		       dataType: "json",
		       url: requestUrl,
		       data: {
		           moneySno:ids.join(",") },
		       success: function( ) {
					   alert("删除成功");
		       	   	location.reload(); 
		       },
		       error:function(data)
		       {					  alert("删除成功");
		           location.reload();
		       }
		   });

		}
		function checkAll(obj){
			if(obj.checked){
				$('input[type=checkbox][name=ids]').attr('checked',true);
			}else{
				$('input[type=checkbox][name=ids]').attr('checked',false);
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/moneydetail/moneyDetailInfo/">金额明细列表</a></li>
		<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><li><a href="${ctx}/moneydetail/moneyDetailInfo/form">金额列表添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><li><a href="${ctx}/moneydetail/moneyDetailInfo/import">金额列表导入</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="moneyDetailInfo" action="${ctx}/moneydetail/moneyDetailInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间：</label>
				<input name="beginMoneyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${moneyDetailInfo.beginMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endMoneyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${moneyDetailInfo.endMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>金额：</label>
				<form:input path="money" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>类型：</label>
			<sys:treeselect id="moneyType" name="moneyType"    value="${tallyType.typeCode}" labelName="TALLY_TYPE_DESC" labelValue="${tallyType.moneyTypeDesc}"
					title="类型" url="/money/tallytype/treeData?type=1"   checked="true" /> 
			</li>
			<li><label>描述：</label>
				<form:input path="moneyDesc" htmlEscape="false" maxlength="600" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit2" class="btn btn-primary" type="button" onclick='batchDelete()' value="批量删除"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkall" onclick='checkAll(this)' id="checkall">时间</th>
				<th>金额</th>
				<th>类型</th>
				<th style='width:400px'>描述</th>
				<!--th>信用卡</th>
				<th>是否拆分</th>
				<th>实际金额</th-->
				<th>账户名</th>
				<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="moneyDetailInfo">
			<tr>
				<td><a href="${ctx}/moneydetail/moneyDetailInfo/form?id=${moneyDetailInfo.moneySno}">
					<input type="checkbox" value="${moneyDetailInfo.moneySno}" name="ids"><fmt:formatDate value="${moneyDetailInfo.moneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${moneyDetailInfo.money}
				</td>
				<td>
					${fnc:getMoneyTypename(moneyDetailInfo.moneyType)}
				</td>
				<td style='width:400px'>
					${moneyDetailInfo.moneyDesc}
				</td>
				<!--td>
					${moneyDetailInfo.shopcard}
				</td>
				<td>
					${moneyDetailInfo.splitno}
				</td>
				<td>
					${moneyDetailInfo.realMoney}
				</td-->
				<td>
					${fns:getDictLabel(moneyDetailInfo.username, '', '')}
				</td>
				<shiro:hasPermission name="moneydetail:moneyDetailInfo:edit"><td>
    				<a href="${ctx}/moneydetail/moneyDetailInfo/form?id=${moneyDetailInfo.id}">修改</a>
					<a href="${ctx}/moneydetail/moneyDetailInfo/delete?id=${moneyDetailInfo.id}" onclick="return confirmx('确认要删除该金额列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>