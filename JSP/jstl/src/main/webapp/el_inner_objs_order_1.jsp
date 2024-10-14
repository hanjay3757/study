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
//주석 풀어가면서 테스트. 단, 세션은 브라우저가 켜있으면 유지되므로 주의.
// invalidate 호출해서 날릴 필요 있는 경우 구분해서 처리 할 것.

// 	request.setAttribute("key","밸-리퀘");
 	session.setAttribute("key","밸-세션");
 	
// 	session.invalidate();
	application.setAttribute("key","밸-앱");

%>
<jsp:forward page="el_inner_objs_order_2.jsp"></jsp:forward>
	
</body>
</html>



