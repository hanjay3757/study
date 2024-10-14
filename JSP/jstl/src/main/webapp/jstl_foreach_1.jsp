<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<String> k = new ArrayList<>();
	k.add("개");
	k.add("닭");
	
	request.setAttribute("k",k);
%>

<jsp:forward page="jstl_foreach_2.jsp"></jsp:forward>

	
</body>
</html>



