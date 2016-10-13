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

<title>My JSP 'forwardShow.jsp' starting page</title>

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
	<%
		String att1 = (String)request.getAttribute("att1");
		System.out.println(att1);
		String text1 = new String(request.getParameter("text1").getBytes(
				"ISO-8859-1"), "UTF-8");
		System.out.println(text1);
	%>
	
	<div id="divShow"></div>
	<div id="divShow2"></div>
	<input type="button" onclick="showData();" value="显示数据">
</body>
<script type="text/javascript">
	var text1 = "<%=text1%>";
	var att1 = "<%=att1%>";
	
	function showData(){
		document.getElementById("divShow").innerHTML=text1;
		document.getElementById("divShow2").innerHTML=att1;
	}

</script>
</html>
