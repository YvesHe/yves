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
	//获取页面过来要显示的 操作消息
	String message = (String)request.getAttribute("message");
	if (message !=  null){
		out.println(message);
	}


	//获取传递过来的所有数据
	List<UserBean> list = (List<UserBean>)request.getAttribute("userBeanList");
	UserBean selfBean = (UserBean) session.getAttribute("userBean");//该登录用户有的信息

	if(selfBean == null){
		response.sendRedirect(path+ "/pages/user/userLogin.jsp");//当直接访问该页面的时候,打回登录页面不提示
		return;
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
		当前操作员:<%=selfBean.getUserName()%>
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
					<th>UserPowerId</th>
					<th>UserNickName</th>
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
					<td><%=ub.getUserPowerId()%></td>
					<td><%=ub.getUserNickName()%></td>

					<%
						if(selfBean.getUserPowerId() < ub.getUserPowerId()) {
					%>
					<td><a href="pages/user/userUpdate.jsp?userId=<%=ub.getUserId()%>">修改</a></td>
					<td><a href="userDelete?userId=<%=ub.getUserId()%>">删除</a>
					</td>
					<%
						}else{
					%>
					<td>修改</td>
					<td>删除</td>
					<%
						}
					%>

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
