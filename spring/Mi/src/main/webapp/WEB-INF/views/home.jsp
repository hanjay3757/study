<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" /><!-- el변수 cp에 경로저장 -->
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cp}/resources/common.css" >
    <title>Home</title>
</head>
<body>

<h1>사용자가 선택할 수 있는 메뉴</h1>

<a href="${cp}/guest/getList?currentPage=1">[방명록]</a>
<a href="${cp}/guest/del?btext=1">TEXT로 검색</a>
</body>
</html>
