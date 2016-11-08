<%@page import="cn.com.yves.bean.UserBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    //设置编码
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<%
    UserBean selfBean = (UserBean) session.getAttribute("userBean");//该登录用户有的信息

			if (selfBean == null) {
				response.sendRedirect(path + "/pages/user/userLogin.jsp");//当直接访问该页面的时候,打回登录页面不提示
				return;

				/*思考:有没有更好的方式处理*///由于jsp中有java代码要先编译java代码,所以当没登录直接访问该页面时候 uBean会为null 下面代码会报空指针,所以在这里先直接new一个空的对象,防止报错.
				//解决办法: return;
				//selfBean = new UserBean(); 
			}
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
	<form id="myForm" method="post" action="userAdd2">
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
