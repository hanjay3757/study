<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-글쓰기</title>
<link rel="stylesheet"  href="common.css">
</head>
<body><%
	String editNum = request.getParameter("num"); %>
글번호:<%=editNum %><br>
<!-- action은 ? 사용못해서 자세한 설정값들 사용해야함 -->
	<input name="num" type="hidden" value=<%=editNum%>>
<form action = "writeproc.jsp" method = "get">
글제목:<input name="title"><br>
작성자id:<input name="id"><br>
내용:<textarea name="content" rows="5" cols="50"></textarea>




</form>
</body>
</html>