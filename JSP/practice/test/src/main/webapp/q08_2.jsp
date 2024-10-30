<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 그냥 바로 이동  개1이든 개2이든 적은 내용같은거 싹다 안나옴 --%>
<jsp:forward page="q08.jsp">	
	<jsp:param name="x"  value="11" />
	<jsp:param name="y"  value="22" />
</jsp:forward>

개2



</body>
</html>