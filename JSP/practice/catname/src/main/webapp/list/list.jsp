<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-리스트</title>

</head>
<body>
게시판-리스트
<a href="index.jsp">홈으로 이동</a>
<!-- 주석은 컨트롤+쉬프트+c -->
<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cats", "root", "root");
		Statement st = con.createStatement();
		System.out.println("접속 잘됐음");
	} catch (Exception e) {		
		e.printStackTrace();
	}	
%>
</body>
</html>