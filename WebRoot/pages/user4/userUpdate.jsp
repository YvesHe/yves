<%@page import="cn.com.yves.dao.impl.UserDaoImpl"%>
<%@page import="cn.com.yves.dao.UserDaoInf"%>
<%@page import="cn.com.yves.bean.UserBean"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="../errorPage/noLogin.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%
    //获取当前要修改的数据
    String userId = request.getParameter("userId");
    UserDaoInf iUserDao = new UserDaoImpl();
    UserBean ubBean = iUserDao.getUserBeanById(userId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userUpdate.jsp' starting page</title>

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
	<h1>修改账号信息</h1>
	<form method="post" action="userControl?service=userUpdate">
		userId:<input name="userId" type="text" readonly="readonly"
			value="<%=ubBean.getUserId()%>"><br> userCount:<input
			name="userCount" type="text" readonly="readonly"
			value="<%=ubBean.getUserCount()%>"><br> userName:<input
			name="userName" type="text" value="<%=ubBean.getUserName()%>"><br>
		userPhoneNumber:<input name="userPhoneNumber" type="text"
			value="<%=ubBean.getUserPhoneNumber()%>"><br> userPwd:<input
			name="userPwd" type="text" value="<%=ubBean.getUserPwd()%>"><br>
		userNickName:<input name="userNickName" type="text"
			value="<%=ubBean.getUserNickName()%>"><br> userDesc:<input
			name="userDesc" type="text" value="<%=ubBean.getUserDesc()%>"><br>
		userPowerId:<input name="userPowerId" type="text"
			value="<%=ubBean.getUserPowerId()%>"><br> <input
			type="submit" value="提交">
	</form>
</body>
</html>
