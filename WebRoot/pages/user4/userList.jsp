<%@page import="cn.com.yves.bean.UserBean"%>
<%@page import="cn.com.yves.dao.UserDaoInf"%>
<%@page import="cn.com.yves.dao.impl.UserDaoImpl"%>
<!-- 设置 errorPage属性,当页面出错时的跳转页面为noLogin.jsp-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="../errorPage/noLogin.jsp"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
    //获取传递过来的所有数据
	List<UserBean> list = (List<UserBean>)request.getAttribute("userBeanList");
	UserBean selfBean = (UserBean) session.getAttribute("userBean");//该登录用户有的信息

//注意: 设置了页面出错后的跳转后,就不要担心用户刻意直接访问该页面会报错了,因为会直接将页面跳转到设置的出错页面
	//if(selfBean == null){
	//	response.sendRedirect(path+ "/pages/user2/userLogin.jsp");
	//	return;
	//}
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
		<a href="userControl?service=exit">安全退出</a>
	</h2>
	<form method="post" id="myForm" action="userControl?service=userQuery">
		<input type="text" name="queryWord"> <input type="button"
			value="查询" onclick="query();">
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
					<td><a
						href="pages/user4/userUpdate.jsp?userId=<%=ub.getUserId()%>">修改</a>
					</td>
					<td><a
						href="userControl?service=userDel&userId=<%=ub.getUserId()%>">删除</a>
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
		<a href="pages/user4/userAdd.jsp"> <input type="button" value="添加">
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
