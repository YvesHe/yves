<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Login</title>
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
	<form action="#">
		<span>用户名:</span> <input name="userName" type="text"> </br> <span>密码:</span>
		<input name="userPwd" type="password">
		<div id="divPwd"></div>
		</br> <input type="reset" value="reset"> <input type="button"
			value="login" onclick="ajaxLogin();">
	</form>

</body>

<script type="text/javascript">
	var request;
	var userName = document.getElementsByName("userName")[0];
	var userPwd = document.getElementsByName("userPwd")[0];
	var divPwd = document.getElementById("divPwd");

	//待做： 判断账号，密码不能为空,要对账号和密码进行去除空格

	function init() {
		//do  initialize
	}

	function ajaxLogin() {

		if (window.XMLHttpRequest) {
			//firefox
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			//IE
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		request.open("POST", "LoginServlet");
		//Post code
		request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		request.onreadystatechange = function() {
			if (request.readyState == 4 && request.status == 200) {
				var result = request.responseText;
				divPwd.innerHTML = result;
			} else {
				//data loading ...
				divPwd.innerHTM = "<img alt='wait loading...' src='image/wait.gif'>";
			}
		};
		request
				.send("userName=" + userName.value + "&userPwd="
						+ userPwd.value);
	}
</script>
</html>
