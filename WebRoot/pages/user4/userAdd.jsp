<%@page import="cn.com.yves.bean.UserBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="../errorPage/noLogin.jsp"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<%
    UserBean selfBean = (UserBean) session.getAttribute("userBean");//该登录用户有的信息
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h1>新增User</h1>
	<h2>
		当前操作员:<%=selfBean.getUserName()%></h2>
	<form id="myForm" method="post" action="userControl?service=userAdd">
		UserName: <input type="text" name="userName"> <br>
		UserPowerId: <input type="text" name="userPowerId"> 这里要做权限控制
		只能创建比自己权限低的账号 <br> UserPwd: <input type="password" name="userPwd">
		<br> 确认密码: <input type="password" name="userPwdConfirm">
		<br> <input type="button" value="提交" onclick="validatePwd();">
	</form>
</body>
<script type="text/javascript">
	//前台验证两次密码是否一致
	function validatePwd() {
		var pwd = document.getElementsByName("userPwd")[0].value.trim();
		var pwdConfirm = document.getElementsByName("userPwdConfirm")[0].value
				.trim();
		if (pwd != pwdConfirm) {
			alert("两次输入的密码不一致!");
		} else {
			document.getElementById("myForm").submit();
		}
	}
</script>

</html>
