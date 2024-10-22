<%@page import="com.peisia.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" /><!-- el변수 cp에 경로저장 -->
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cp}/resources/common.css" >
    <title>Home</title>
</head>
</head>
<body>
    <h2>글 수정</h2>
    <p>여기 진짜 오나???</p>
    
    <%
        GuestDto read = (GuestDto)request.getAttribute("read");
        long bno = read.getBno();
        String btext = read.getBtext();
    %>  

  <p>글번호: ${bno}</p>

<form action="/guest/modify" method="post">
    <input type="hidden" name="bno" value="${bno}">
    
    <!-- 글 내용을 수정할 수 있는 텍스트 영역 (textarea) -->
    <textarea name="btext" rows="10" cols="50">${btext}</textarea>
    
    <br><br>
    <a href="/guest/getList">글 리스트</a>
    <a href="/guest/modify?bno=${bno}">글 수정</a>
    <input type="submit" value="수정하기">
</form>

    </form>
</body>
</html>
