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
<title>게시판 - 글읽기</title>
</head>
<body>
<hr>
<%
String readNum = request.getParameter("num");
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cats","root","roor");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select*from cat_board where num="+readNum);
while(rs.next()){
	String num = rs.getString("num");
	String title = rs.getString("title");
	String content = rs.getString("content");
	String id = rs.getString("id");
	%>
	글번호:<%=num%><br>
	글제목:<%=title%><br>
	작성자id:<%=id%><br>
	글내용:<br>
	<%=content%>
	<%
	}
}catch(Exception e){
	e.printStackTrace();
}
%>
<a href ="delproc.jsp?num=<%=readNum %>">글삭제하기</a>
<a href ="modify.jsp?num=<%=readNum %>">글수정하기</a>

</body>
</html>