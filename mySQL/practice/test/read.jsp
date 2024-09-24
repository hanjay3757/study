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
<title>Insert title here</title>
<link rel = "stylesheet" href="common.css">
</head>
<body>
글번호 | 제목 | 글내용 | ID
<hr>
<%
String readNum = request.getParameter("num");
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cats","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from cat_board where num="+readNum);
	rs.next();
	String num = rs.getString("num");
	
}catch(Exception e){
	e.printStackTrace();
}
%>
<a href="delproc.jsp?num=<%=readNum%>">글삭제하기</a>
<a href="write.jsp">글쓰기</a>
<a href="list.jsp">리스트로 </a>

</body>
</html>