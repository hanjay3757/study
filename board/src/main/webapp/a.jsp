<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>폼 제출 페이지</title>
</head>
<body>
    먀옹
    <form id="myForm" action="a.jsp" method="post">
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required>
        
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>
        
        <button type="submit">제출</button>
    </form>
    <div id="b"></div>
    <script src="tt.js" defer></script>
</body>
</html>
