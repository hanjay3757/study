<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!-- 0. 웹 애플리케이션의 루트 경로(컨텍스트 경로) 를 가져와서 링크에 다 연결해줘야 함     -->
<!-- 1. 0을 위한 준비. jstl core 태그 선언     -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 2. 0을 위한 준비. el 태그로 가져올 수 있는데 이걸 더 짧게 찍기위해 변수 대입함.     -->
<c:set var="cp" value="${pageContext.request.contextPath}" /><!-- el변수 cp에 경로저장 -->

<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="${cp}/test/getOnePlusTwo">1+2 결과 보러 가기</a>
<a href="${cp}/test/updateVisitantCount">문1</a>
<a href="${cp}/test/insertDoodle">문2</a>
<a href="${cp}/test/delTest">문3</a>
<a href="${cp}/video/getVideo">실험용</a>

<hr><hr>
<a href="${cp}/guest/getList">[방명록]</a>
</body>
</html>
