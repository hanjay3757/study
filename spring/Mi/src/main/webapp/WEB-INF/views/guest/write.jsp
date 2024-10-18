<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" /><!-- el변수 cp에 경로저장 -->
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cp}/resources/common.css" >
    <title>Home</title>
</head>
<body>

	<form action="/guest/write" method="post">	<!-- todo: http://localhost:8080/guest/write 부분 해결 -->
		<textarea rows="3" name='btext'></textarea>
		<input type="submit" value="글쓰기">
	</form>
<a href="http://localhost:8080/java">홈으로 돌아가기</a>
</body>
</html>