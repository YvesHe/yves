<!-- jsp九大隐式对象的讲解 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!-- 隐式对象1: request  跟servlet中 HttpServletRequest一样 -->

<!-- 隐式对象2: response  跟servl中HttpServletResponse一样 -->

<!-- 隐式对象3: page -->
<%
    //方法一:如果是想调用当前jsp转义后的java类中的方法和属性 ,可以直接用this.  当前对象也就是object9_jsp对象
    System.out.println(this.getClass().getName());
    //方法二:用隐式对象page,因为page的引用时object,所以要转型

    HttpJspPage httpJspPage = (HttpJspPage) page;
    System.out.println(httpJspPage.getClass().getName());
%>

<!-- 隐式对象4: config -->
<%
    ServletContext sContext = config.getServletContext();//获取servlet的上下文,也就是当前web工程.  
    sContext.setAttribute("attribute", "attribute value"); //在上下文中这是attribute,也就是在Application中setAttribute,
                                         //因为Application是上下文的容器

    //思考: 如果我向servletContext中steAttribute在Application中会取到数据吗?可以,数据是伴随着整个web工程的生命周期,Application也是一样
    String value = (String) application.getAttribute("attribute");
    System.out.println(value);//显示出之前设置在ServletContext中的结果

    config.getServletName();// 不管是什么jsp调用这个方法获得的值都是:JSP
                    //主要用来是判断url访问的是JSP还是servlet,因为在java代码servlet中也有这个方法
    config.getInitParameter("");//这个是配置在web.xml 中该servlet下<init-param>中的参数,这里是设置参数
    String  st1 = config.getServletContext().getInitParameter("initPrarameter");
      System.out.println(st1);
%>

<!-- 隐式对象5: Application     Application是上下文中的容器不是上下文ServletContext -->

<!-- 隐式对象6: exception       引用:java.lang.Throwable -->
<%
    //隐式对象 exception只有设置了page的属性 isErrorPage="true" 才能调用
    //exception.printStackTrace();
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'object9.jsp' starting page</title>

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
	This is my JSP page.
	<br>
</body>
</html>
