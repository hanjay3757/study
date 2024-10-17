<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>
    Hello world!  
</h1>

<p>The time on the server is ${serverTime}.</p>

<!-- 사용자가 bno 값을 입력할 수 있는 폼 추가 -->
<form action="/guest/read" method="get">
    <label for="bno">게시글 번호 입력:</label>
    <input type="text" id="bno" name="bno" />
    <button type="submit">읽기</button>
</form>

<!-- 게시글 삭제 폼 추가 -->
<form action="/guest/del" method="get" onsubmit="return confirm('정말 삭제하시겠습니까?');">
    <label for="del-bno">삭제할 게시글 번호:</label>
    <input type="text" id="del-bno" name="bno" />
    <button type="submit">삭제</button>
</form>

<!-- 다른 링크들 -->
<a href="/test/getOnePlusTwo">1+2 확인하러가기</a><br>
<a href="/guest/getList">방명록</a><br>
<a href="/guest/write">글쓰기</a>
<a href="/guest/del?bno=${item.bno}" onclick="return confirm('정말 삭제하시겠습니까?');">글삭제?</a>

<a href="/guest/modify?bno=1">글수정</a>

</body>
</html>
