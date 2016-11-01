<%@page import="cn.com.yves.bean.UserBean"%>
<%@page import="cn.com.yves.dao.UserDaoInf"%>
<%@page import="cn.com.yves.dao.impl.UserDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
	if(session.isNew()){
		//当打开浏览器访问该站点第一个网站开始的session才是新的   isNew与session是否是失效无关.
		System.out.println("session is New !");
	}
	UserDaoInf iUserDao = new UserDao();
	List<UserBean> list = iUserDao.listAllUserBean();
	UserBean uBean = (UserBean) session.getAttribute("userBean");
	
	if(uBean == null){
		response.sendRedirect(path+ "/pages/user/userLogin.jsp");
		//由于jsp中有java代码要先编译java代码,所以当没登录直接访问该页面时候 uBean会为null 下面代码会报空指针,所以在这里先直接new
		//一个空的对象,防止报错.
		uBean = new UserBean();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userList.jsp' starting page</title>

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
	<h1>User信息总览表</h1>
	<h2>
		当前操作员:<%=uBean.getUserName()%>
		<a href="userExit">安全退出</a>
	</h2>
	<form method="post" id="myForm" action="userQuery">
		<input type="text" name="queryWord">
		<input type="button" value="查询" onclick="query();">
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
					<td><a href="userUpdate?userId=<%=ub.getUserId()%>">修改</a></td>
					<td><a href="userDelete?userId=<%=ub.getUserId()%>">删除</a></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<a href="pages/user/userAdd.jsp">
			<input type="button" value="添加">
		</a>
	</form>
</body>

<script type="text/javascript">
	function query() {
		//查询结果显示,POST提交
		document.getElementById("myForm").submit();
	}
</script>
</html>
