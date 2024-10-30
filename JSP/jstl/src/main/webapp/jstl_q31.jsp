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
// for(int i=0;i<3;i++){
	
// }
// for(String s:str1){
// 	sysout s....
// }

 	String str1[] = {"고양이","개","너굴맨"};

%>

<c:set var="arr" value="<%=str1 %>" />


<!-- 향상for문 식 반복 -->
<c:forEach var="i" items="${arr}" begin="0" end="1" step="1">
	${i}<br>
</c:forEach>
<hr>
<c:forEach var="i" items="${arr}">
	${i}<br>
</c:forEach>

<!-- 일반 반복 -->
<c:forEach var="x" begin="0" end="3">
	${x}<br>
</c:forEach>
	
</body>
</html>



