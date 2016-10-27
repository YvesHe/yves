<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 用ajax的方式来做这个自动刷新  这里是在前台进行自动刷新的 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'autoRefresh.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 引入js -->
<script type="text/javascript" src="js/yves.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	显示当前时间:
	<span id="currentTime"></span>
	<br>
	<input type="button" value="自动刷新时间" onclick="autoRefresh();">
	<input type="button" value="停止刷新" onclick="stopRefresh();">
</body>

<script type="text/javascript">
	var interval;

	function autoRefresh() {
		//设置刷新时间
		var waitTime = "1000";
		//setTimeout("test()",waitTime);  //2000毫秒后执行test()函数，只执行一次。
		interval = setInterval("readJsonData()", waitTime); //每隔2000毫秒执行一次test()函数，执行无数次。
	}

	//读Json数据
	function readJsonData() {
		request = ajaxRequest();
		//get 请求方式
		request.open("GET", "autoRefresh?nowTime=" + new Date().getTime());
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					var result = request.responseText;
					console.log(result);
					//将json的字符串转换成json对象
					var jsonObj = eval("(" + result + ")");
					refreshTime(jsonObj);
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

	//更新时间
	function refreshTime(jsonData) {
		var showSpan = document.getElementById("currentTime");
		showSpan.innerHTML = jsonData.currentTime;
	}

	//停止刷新
	function stopRefresh() {
		window.clearInterval(interval); //停止执行setInterval循环。
	}
</script>
</html>
