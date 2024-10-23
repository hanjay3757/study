<%@page import="com.peisia.jsp.board.dao.DaoBoard"%>
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
	String category = request.getParameter("category");
%>
<form action="/board/write">
	<input type="hidden" name="category" value="<%=category%>">
	<input name="title" placeholder="제목">
	<input name="id" placeholder="작성자id">
	<input name="text" placeholder="내용">
	<input type="submit" value="쓰기">
</form>
</body>
</html>