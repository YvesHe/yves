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

<title>Index</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/yves.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body>
	<form action="#">
		<textarea id="showResult" rows="4" cols="8"></textarea>
		</br> <span>UserName:</span> <input name="userName" type="text"> </br> <span>Password:</span><input
			name="userPwd" type="password">
		<div id="divPwd" style="dispaly:inline"></div>
		</br> <input type="reset" value="reset"> <input type="button"
			value="login" onclick="ajaxLogin();"></br> <input type="button"
			value="ajaxGet方式中文乱码" onclick=""></br> <input type="button"
			value="ajax读取xml数据" onclick="readXMLData();"></br>
			<button onclick="readJsonData();">ajax请求json数据</button>

	</form>
</body>

<script type="text/javascript">
	var request;
	var userName = document.getElementsByName("userName")[0];
	var userPwd = document.getElementsByName("userPwd")[0];
	var divPwd = document.getElementById("divPwd");
	var showResult = document.getElementById("showResult");

	function init() {
		//do  initialize
	}

	function ajaxLogin() {
		request = ajaxRequest();
		request.open("POST", "LoginServlet?name=" + userName.value + "&pwd="
				+ userPwd.value + "&nowTime=" + new Date().getTime());
		// post option
		request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					var result = request.responseText;
					divPwd.innerHTML = result;
				} else if (request.status == 404) {
					alert("找不到该页面!");
				} else if (request.status == 500) {
					alert("服务器异常!");
				} else {
					alert("其他异常!");
				}
			} else {
				//data loading ...
				divPwd.innerHTML = "<img alt='正在加载...' src='images/wait.gif'>";
			}
		};
		request
				.send("userName=" + userName.value + "&userPwd="
						+ userPwd.value);
	}

	function readXMLData() {
		request = ajaxRequest();
		request.open("GET", "ReadXML?nowTime=" + new Date().getTime());
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					var result = request.responseXML;
					String
					message = "";
					console.log(result.getElementsByTagName("name")[0]);
					message += "姓名:"
							+ result.getElementsByTagName("name")[0].textContent;
					+"/n"
					message += "地址:"
							+ result.getElementsByTagName("name")[0]
									.getAttribute("address");
					showResult.value = message;
				} else if (request.status == 404) {
					alert("找不到该页面!");
				} else if (request.status == 500) {
					alert("服务器异常!");
				} else {
					alert("其他异常!");
				}
			} else {
				//data loading ...
			}
		};
		request.send(null);
	}

	function readJsonData() {
		request = ajaxRequest();
		request.open("GET", "ReadJson?nowTime=" + new Date().getTime());
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					var result = request.responseText;
					console.log(result);
					//将json的字符串转换成json对象
					var jsonObj = eval("(" +result+")");
					console.log(jsonObj);
					var message = jsonObj[0].name;
					showResult.value = message;
				} else if (request.status == 404) {
					alert("找不到该页面!");
				} else if (request.status == 500) {
					alert("服务器异常!");
				} else {
					alert("其他异常!");
				}
			} else {
				//data loading ...
			}
		};
		request.send(null);
	}
</script>
</html>
