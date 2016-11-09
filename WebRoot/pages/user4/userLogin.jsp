<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    //获取页面过来要显示的 操作消息
			String message = (String) request.getAttribute("message");
			if (message != null) {
				out.println(message);
			}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 成功登录后,跳转到userList.jsp  显示所的user -->
	<form method="post" id="myForm"
		action="/yves/userControl?service=login">
		<h1>用户登录</h1>
		用户名: <input type="text" name="userName"> <br> 密码: <input
			type="password" name="userPwd"> <br> 验证码: 待做<br> <input
			type="button" onclick="validate()" value="登录">
	</form>
</body>
<script type="text/javascript">
	//对验证码的验证   待做
	//思考 : 验证码是在前端验证,还是在后台验证的.
	function validate() {
		//验证码通过后,提交表单.
		document.getElementById("myForm").submit();
	}
</script>
</html>