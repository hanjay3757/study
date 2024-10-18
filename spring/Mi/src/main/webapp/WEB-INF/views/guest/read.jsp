<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" /><!-- el변수 cp에 경로저장 -->
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cp}/resources/common.css" >
    <title>Home</title>
</head>
<body>

글 읽기 
<hr>
글번호:${read.bno}
<hr>
글내용:${read.btext}
</body>
<a href="http://localhost:8080/java">홈으로 돌아가기</a>
</html>