<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//request.getContextPath();  获取的是上下文,也就是工程: /1yves
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 本页面是用来测试   Ajax 请求的,直接访问 -->
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
		<textarea id="showResult" rows="4" cols="30">结果显示区域:</textarea><br>
		 登录采用Ajax请求登录 </br>
		<span>UserName:</span>
		<input name="userName" type="text">
		</br> <span>Password:</span>
		<input name="userPwd" type="password">
		<div id="divPwd" style="dispaly:inline"></div>
		</br>
		<input type="reset" value="重置">
		<input type="button" value="登录" onclick="ajaxLogin();">
		</br></br>
		<input type="button" value="Ajax读取xml数据" onclick="readXMLData();">
		<button onclick="readJsonData();">Ajax请求json数据</button>
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
		request = ajaxRequest(); //ajaxRequest();方法定义在yves.js中

		//设置 请求方法 和  请求URl
		request.open("POST", "loginServlet?name=" + userName.value + "&pwd="
				+ userPwd.value + "&nowTime=" + new Date().getTime());

		//post 请求方式必须设置的代码
		request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		//回调方法
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
				//出现一个等待的动态图片,提示正在加载
				divPwd.innerHTML = "<img alt='正在加载...' src='images/wait.gif'>";
			}
		};
		request
				.send("userName=" + userName.value + "&userPwd="
						+ userPwd.value);
	}

	//读XML数据
	function readXMLData() {
		request = ajaxRequest();

		//get 请求方式
		request.open("GET", "readXML?nowTime=" + new Date().getTime());
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
				//加载数据 ...
			}
		};
		request.send(null);
	}

	//读Json数据
	function readJsonData() {
		request = ajaxRequest();

		//get 请求方式
		request.open("GET", "readJson?nowTime=" + new Date().getTime());
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					var result = request.responseText;
					console.log(result);
					//将json的字符串转换成json对象
					var jsonObj = eval("(" + result + ")");
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
				//加载数据 ...
			}
		};
		request.send(null);
	}
</script>
</html>
