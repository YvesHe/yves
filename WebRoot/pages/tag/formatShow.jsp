<!-- JSTL之format标签实例,设置启用EL表达式 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>


<!-- 引入核心标签和format标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>My JSP 'formatShow.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<c:set var="parameter" value="10000" scope="request" />

<!-- 设置 货币符号的访问为￥,但是现在的购物平台一般是采用直接写死货币符号:￥-->
<fmt:setLocale value="zh-CN" />
<fmt:formatNumber type="currency" value="${ parameter}" /><br>

<body>
   人命币货币符号:￥<br>
    美元货币符号:$<br>
</body>
</html>
