<!-- 这里主要讲解的是 核心标签 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 引入核心标签库  :  prefix是指用该标签 时候的前缀, uri是指该标签库的路径  tagdir属性是指在使用本地标签的时候配置的属性?  -->
<!-- prefix的名字可以随便取,但是核心标签库大家都习惯取名字为: c  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>My JSP 'core.jsp' starting page</title>

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
	<c:out value="<string>" default="<string>" escapeXml="true" />
</body>
</html>
