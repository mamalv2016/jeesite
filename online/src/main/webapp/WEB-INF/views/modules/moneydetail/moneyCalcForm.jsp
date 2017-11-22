<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>理财小工具</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript"> 
	/**
 * 用来查看一个对象的属性
 */
function debugObjectInfo(obj){
        traceObject(obj);
       
        function traceObject(obj){
                var str = '';
                if(obj.tagName&&obj.name&&obj.id)
                str="<table border='1' width='100%'><tr><td colspan='2' bgcolor='#ffff99'>traceObject 　　tag: &lt;"+obj.tagName+"&gt;　　 name = \""+obj.name+"\" 　　id = \""+obj.id+"\" </td></tr>";
                else{
                        str="<table border='1' width='100%'>";
                }
                var key=[];
                for(var i in obj){
                        key.push(i);
                }
                key.sort();
                for(var i=0;i<key.length;i++){
                        var v= new String(obj[key[i]]).replace(/</g,"&lt;").replace(/>/g,"&gt;");
                        str+="<tr><td valign='top'>"+key[i]+"</td><td>"+v+"</td></tr>";
                }
                str=str+"</table>";
                writeMsg(str);
        }
        function trace(v){
                var str="<table border='1' width='100%'><tr><td bgcolor='#ffff99'>";
                str+=String(v).replace(/</g,"&lt;").replace(/>/g,"&gt;");
                str+="</td></tr></table>";
                writeMsg(str);
        }
        function writeMsg(s){
                traceWin=window.open("","traceWindow","height=600, width=800,scrollbars=yes");
                traceWin.document.write(s);
        }
}

	function calc(){
			var form = [];
			$('input[name]').each(function(){
				form.push(this.name+'='+this.value);
			});
			form.push('calcMethod='+$('select[name=calcMethod]').val()); 
			$.ajax({
		       type: "post",
		       async: false,
		       dataType: "json",
				 data:form.join('&')  ,
		       url: "${ctx}/moneydetail/moneyDetailInfo/calc", 
		       success: function(d ) { 
			   },
		       error:function(data)
		       {	
				$('#result').html(data.responseText);
		       }
		   });
	}
	</script>
</head>
<body> 
	<form:form id="inputForm" modelAttribute="calcInput" action="#" method="post" class="form-horizontal"> 
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">理财方式：</label>
			<div class="controls">
				<select name="calcMethod" class="input-xlarge ">
					<option value="1"> 银行存款</> 
					<option value="2">复利投资</>  
					<option value="3">等本金贷款</> 
					<option value="4">等本息贷款</> 
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">本金：</label>
			<div class="controls">
				<input name="ben"  value="10000" class="input-xlarge required"/>元
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">年化收益：</label>
			<div class="controls">
				<input name=  "fee" value='8.4' class="input-xlarge required"/>%
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资月份数：</label>
			<div class="controls">
				<input name=  "month"  value=12  class="input-xlarge required"/>月
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">每月累加：</label>
			<div class="controls">
				<input name=  "addon" value="0" maxlength="600" class="input-xlarge "/>
			</div>
		</div>  
		<div class="form-actions">
			<shiro:hasPermission name="moneydetail:moneyDetailInfo:view"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="calc()" value="计算"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div id='result' style='height:200px;overflow:auto'>
	</div>
</body>
</html>