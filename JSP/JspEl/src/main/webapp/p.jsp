<%@page import="com.peisia.jsp.el.test.Cat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String xx = request.getParameter("x");
%>

<%=xx %>

x1: ${param.x}
<hr>
x2: ${param["x"]}

</body>
</html>