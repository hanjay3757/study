<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cp" value="${pageContext.request.contextPath}" /> <!-- EL 변수 cp에 경로 저장 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cp}/resources/common.css" />
    <title>Home</title>
</head>
<body>
    <h1>방명록</h1>
    <h2>글목록</h2>

    <table>
        <tr>
            <td>번호</td>
            <td>내용</td>
        </tr>

        <!-- JSTL을 사용해 list 객체 반복 -->
     <c:forEach var="guest" items="${list}">
    <tr>
        <!-- 번호 출력: guest 객체의 bno 속성을 출력 -->
        <td>${guest.bno}</td>
        
        <!-- 텍스트와 링크 출력: guest 객체의 btext 속성을 링크로 출력, 클릭 시 해당 게시글을 볼 수 있는 URL로 이동 -->
        <td><a href="/guest/read?bno=${guest.bno}">${guest.btext}</a></td>
    </tr>
</c:forEach>

    </table>

    <br><br>

    <!-- 글쓰기 페이지로 이동 -->
    <a href="/guest/write">새글 쓰기</a>
    <a href="${cp}/">홈으로 돌아가기</a>
</body>
</html>
