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
<a href="list.jsp">홈으로 이동</a>

<%
	try {
		//class.forname .추가해야함
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
		Statement st = con.createStatement();
		System.out.println("접속 잘됐음");
	} catch (Exception e) {		
		e.printStackTrace();
	}	
%>
</body>
</html>