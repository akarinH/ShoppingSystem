<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color: red" align="center">抱歉注册失败</h1>
	<h1 style="color:green" align="center">3秒后自动跳转到首页,如未跳转<a href="index.jsp">请点击该链接</a></h1>
	<%
		response.setHeader("Refresh","3;URL=index.jsp");
	%>
</body>
</html>