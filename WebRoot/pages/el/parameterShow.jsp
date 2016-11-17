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

<title>My JSP 'show.jsp' starting page</title>

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
	<!--   返回客户端的请求参数的字符串值  ,相当于 request.getParameter (name)。   -->
	姓名:${param.userName}<br> 
	年龄:${param.userAge}<br>
	
	<!-- 这个参数是没有什么都不显示 -->
	性别:${param.userSex }<br>
	
	<!-- 判断对象是否为空 ,为空返回true 也可以用  对象名  == null-->
	${empty param.userSex}<br>
	
    <!-- paramValues:  返回映射至客户端的请求参数的一组值   ,相当于  request.getParamterValues(name)。  -->
	参数列表:<br>
	${ paramValues.hobby[0]}<br>
	${ paramValues.hobby[1]}<br>
	${ paramValues.hobby[2]}<br>
	
	<!-- 运算符的比较 -->
	运算符的比较: ${4 >= 2 }
	${2+1 }
	${"2"+"1"}
	${"2" + 1 }
	


</body>
</html>
