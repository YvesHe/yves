<!-- jsp中的两种注释 -->
<!-- 网页注释,可以发送到浏览器端 -->
<%-- jsp注释,不发送到浏览器端 --%>

<!-- jsp本质就是servlet，所以数据可以直接从一个jsp传递到另外一个jsp -->
<!-- errorPage这是该页面出错后跳转到error.jsp页面 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>

<!-- 这段代码用来让改页面报错后,进入error.jsp -->
<%--
    int num = 10 / 0;
--%>

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
	<!-- jps本质也是servlet,所以也可以进行数据的传递 -->
	<form method="get" action="pages/jsp/result.jsp">
		要传递的数据:
		<input type="text" name="parameter">
		<br>
		<input type="submit" value="提交">
	</form>
</body>
</html>
