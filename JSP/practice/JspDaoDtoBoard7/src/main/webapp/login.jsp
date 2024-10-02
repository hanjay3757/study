<%@page import="com.peisia.db.Dao"%>
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
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	Dao dao = new Dao();
	String loginId = dao.login(id,pw);
	
	//todo
	//세션처리
	session.setAttribute("loginId", loginId);
	
	response.sendRedirect("index.jsp");
%>

</body>
</html>