<%@page import="cn.com.yves.bean.UserBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	UserBean uBean = (UserBean) session.getAttribute("userBean");
	if (uBean == null) {
		response.sendRedirect(path + "/pages/user/userLogin.jsp");
		//由于jsp中有java代码要先编译java代码,所以当没登录直接访问该页面时候 uBean会为null 下面代码会报空指针,所以在这里先直接new
		//一个空的对象,防止报错.
		uBean = new UserBean();
	}
%>

<%
	List<UserBean> list = (List<UserBean>) session
			.getAttribute("queryUserList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userQueryShow.jsp' starting page</title>

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
	当前操作员:<%=uBean.getUserName()%>
	<a href="userExit">安全退出</a>

	<table border="1">
		<tbody>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Pwd</th>
				<th>update</th>
				<th>delete</th>
			</tr>
			<%
				for (UserBean ub : list) {
			%>
			<tr>
				<td><%=ub.getUserId()%></td>
				<td><%=ub.getUserName()%></td>
				<td><%=ub.getUserPwd()%></td>
				<td><a href="userUpdate?userId=<%=ub.getUserId()%>">修改</a>
				</td>
				<td><a href="userDelete?userId=<%=ub.getUserId()%>">删除</a>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>



</body>
</html>
