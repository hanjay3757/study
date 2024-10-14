<%@page import="com.peisia.jsp.board.dto.Dto"%>
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
Dto dto = (Dto)request.getAttribute("post");	//글 가져오기
String category = request.getParameter("category");
%>

카테고리:<%=category%>
<hr>
<form action="/board/edit_proc">
	<input type="hidden" name="category" value="<%=category%>">
	<input type="hidden" name="no" value="<%=dto.no%>"> 
	<input name="title" value="<%=dto.title%>">
	<input name="text" value="<%=dto.text%>">
	<input type="submit" value="수정">
</form>
<a href="/board/list?category=<%=category%>">리스트로</a> <a href="/">홈으로</a>
</body>
</html>