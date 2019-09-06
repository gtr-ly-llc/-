<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>  
  <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台登录</title>
	<style type="text/css">
	table{
		text-align: center;
	}
	.textSize{
		width: 120px;
		height: 25px;
	}
	* {
		margin: 0px;
		padding: 0px;
	}
	body {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		margin: 10px 10px auto;
		background-image: url(<%=basePath %>images/admin/bb.jpg);
	}
	</style>
	<script type="text/javascript">
	//确定按钮
	function gogo(){
		document.forms[0].submit();
	}
	//取消按钮
	function cancel(){
		document.forms[0].action = "";
	}
	</script>
  </head>
  <body>
  <form action="main.jsp">
	<table>
		<tr>
			<td colspan="2"><img src="<%=basePath %>images/admin/login.gif"></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td>
				<input type="text" name="aname" class="textSize"/>
			</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td>
				<input type="password" name="apwd" class="textSize" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="image" src="<%=basePath %>images/admin/ok.gif" onclick="gogo()" >
				<input type="image" src="<%=basePath %>images/admin/cancel.gif" onclick="cancel()" >
			</td>
		</tr>
	</table>
	</form>
  </body>
</html>
