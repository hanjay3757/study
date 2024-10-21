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

<!-- <!-- 글 읽기 폼 --> -->
<!-- <h3>게시글 읽기</h3> -->
<!-- <form action="/guest/read" method="get"> -->
<!--     <label for="bno">게시글 번호 입력:</label> -->
<!--     <input type="text" id="bno" name="bno" required /> -->
<!--     <button type="submit">읽기</button> -->
<!-- </form>**JSP 페이지에서 Java 객체의 속성**, **리퀘스트 속성**, **세션 속성**, **애플리케이션 속성** 등에 접근할 수 있는 표현식을 제공

- **EL은 데이터 생성하지 않습니다**: EL은 이미 **서버에서 전달된 데이터**나 **객체**를 출력하거나 조회하는 역할만 합니다. 데이터를 **생성**하려면 서버 측에서 먼저 **생성**하고, **request**, **session**, **application** 등에 저장해야 합니다.
- **EL의 역할**: EL은 **JSP 페이지에서 동적 데이터를 출력**하는 방법입니다. 서버 측에서 데이터를 설정한 후, JSP에서 이 데이터를 **출력**하거나 **사용**할 수 있도록 도와줍니다. -->

<!-- <!-- 글 삭제 폼 --> -->
<!-- <h3>게시글 삭제</h3> -->
<!-- <form action="/guest/del" method="get" onsubmit="return confirm('정말 삭제하시겠습니까?');"> -->
<!--     <label for="del-bno">삭제할 게시글 번호:</label> -->
<!--     <input type="text" id="del-bno" name="bno" required /> -->
<!--     <button type="submit">삭제</button> -->
<!-- </form> -->

<!-- <!-- 글 수정 폼 --> -->
<!-- <h3>게시글 수정</h3> -->
<!-- <form action="/guest/modify" method="get"> -->
<!--     <label for="mod-bno">수정할 게시글 번호:</label> -->
<!--     <input type="text" id="mod-bno" name="bno" required /> -->
<!--     <button type="submit">수정하기</button> -->
<!-- </form> -->

<!-- <h3>글쓰기</h3> -->
<!-- <form action="/guest/write" method="get"> -->
<!--     <button type="submit" style="padding:10px; border-radius:5px; background-color:#007BFF; color:white;">글쓰기</button> -->
<!-- </form> -->

<!-- <!-- 방명록 보기 --> -->
<!-- <h3>방명록</h3> -->
<!-- <form action="/guest/getList" method="get"> -->
<!--     <button type="submit" style="padding:10px; border-radius:5px; background-color:#28a745; color:white;">방명록 보기</button> -->
<!-- </form> -->
<a href="${cp}/guest/write">새 글 쓰기</a>
</body>
</html>
