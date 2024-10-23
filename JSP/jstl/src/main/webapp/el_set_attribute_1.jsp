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
 	request.setAttribute("cat_name","kitty");
%>

<jsp:forward page="el_set_attribute_2.jsp"></jsp:forward>


	
</body>
</html>



