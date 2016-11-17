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

<title>My JSP 'request.jsp' starting page</title>

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

	<form method="get" action="pages/el/parameterShow.jsp">
		姓名:<input type="text" name="userName"> <br> 
		年龄:<input type="text" name="userAge"> <br>
		
		兴趣爱好:
		打球<input type="checkbox" name="hobby" value="play ball">  &nbsp;
		看电视<input type="checkbox" name="hobby" value="watch TV">  &nbsp;
		打游戏<input type="checkbox" name="hobby" value="play game">  &nbsp;
		
		<input type="submit" value="提交">


	</form>
</body>
</html>
