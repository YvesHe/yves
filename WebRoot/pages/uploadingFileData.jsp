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

<title>My JSP 'uploadingFileData.jsp' starting page</title>

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
	<!--  文件上传时 method必须为post enctype必须为 multipart/form-data-->
	<form method="POST" id="myForm" action="uploadingFile" enctype="multipart/form-data">
		普通表单数据
		<input type="text" name="text1">
		<input type="text" name="text2">
		<br> 单个的上传文件:
		<input type="file" name="file">
		<br> 这两个上传文件的name属性一样:
		<input type="file" name="file1">
		<input type="file" name="file1">
		<br>
		<input type="submit" value="提交">
	</form>
</body>

</html>
