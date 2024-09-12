<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="common.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글번호 | 제목 | 글내용 | 작성자id
<hr>
<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from cat_board");
		while(rs.next()){
			String num = rs.getString("num");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String id = rs.getString("id");

%>

			<%=num %><a href = "read.jsp?num = 1"><%=title  %></a><%=id  %><br>
		<%-- 객체의 상태를 읽을 때
Java에서는 객체의 상태를 읽기 위해 getter 메서드를 사용합니다. 예를 들어, 객체의 private 멤버 변수에 접근하여 값을 반환할 때 사용됩니다. 일반적으로 getter 메서드는 다음과 같은 경우에 사용됩니다:

객체의 상태를 외부에서 읽을 필요가 있을 때
변수에 직접 접근을 제한하고 캡슐화를 유지할 때 --%>
<%			
		}
		
		
	} catch (Exception e) {		
		e.printStackTrace();
	}	
%>
</body>
</html>