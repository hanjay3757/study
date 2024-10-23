<%@ page import="java.util.*" %>
<%@ page import="java.util.*,java.sql.*" %>

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
	request.setAttribute("x","1");
	request.setAttribute("y","2");
	request.getRequestDispatcher("el_add_2.jsp").forward(request, response);
	//response.sendRedirect("el_temp_2.jsp");
%>

</body>
</html>



