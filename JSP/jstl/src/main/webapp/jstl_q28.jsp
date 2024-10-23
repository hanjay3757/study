<%@ page import="java.util.*" %>
<%@ page import="java.util.*,java.sql.*" %>

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

<% //String str1 = "고양이"; %>
<% String str1 = null; %>

<c:out value="<%=str1 %>" default="야옹이" /><br>


<c:set var="str3" value="개" scope="page" />
<c:out value="${str3}" /><br>
	
</body>
</html>



