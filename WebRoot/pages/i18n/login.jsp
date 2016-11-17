<!-- 设置 isELIgnored=false  强制使用 EL表达式-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<!-- 导入核心标签库和format标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>My JSP 'zhLogin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<!-- 1.绑定资源文件:  i18n位properties文件的前缀,多个properties文件的前缀必须相同   作用域设置成session也就意味着从打开浏览器开始都是使用该地区语言的properties文件内容-->
<fmt:setBundle basename="i18n" scope="session" />

<!-- 一个登陆页面实现不同地区访客显示不同的语言 -->
<form method="post" id="myForm" action="*">
<!-- 用fmt:message标签来按键获取资源文件中的值 -->

	<h1><fmt:message key="user.login" /></h1>
	<fmt:message key="user.name" /> <input type="text" name="userName"><br> 
	<fmt:message key="user.pwd" /> <input type="password" name="userPwd"><br> 
	<fmt:message key="validate.code" /> <br>
	<input type="button" onclick="validate()" value="<fmt:message key="login" />">
	
</form>


</html>
