<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet"  href ="common.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
NO|TITLE|CONTENT|ID
<hr>

<%
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cats","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select*from cat_board");
	while(rs.next()){
		String num = rs.getString("num");
		String title = rs.getString("title");
		String content = rs.getString("content");
		String id = rs.getString("id");
		%>
		<%=num%><a href="read.jsp?num=1"><%=title %></a><%=id %><br>
		<% 
	}
	
}catch(Exception e){
	e.printStackTrace();
}
%>
</body>
</html>