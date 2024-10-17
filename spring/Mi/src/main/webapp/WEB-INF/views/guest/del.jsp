<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>글 목록</title> <!-- 웹 페이지의 제목을 "글 목록"으로 설정 -->
</head>
<body>
    <h1>방명록 목록</h1> <!-- 웹 페이지 상단에 "방명록 목록"이라고 크게 표시 -->

    <table>
        <thead>
            <tr>
                <th>번호</th> <!-- 글 번호를 표시할 칼럼 -->
                <th>제목</th> <!-- 글 제목을 표시할 칼럼 -->
                <th>작성자</th> <!-- 글 작성자를 표시할 칼럼 -->
                <th>삭제</th> <!-- 삭제 버튼을 표시할 칼럼 -->
            </tr>
        </thead>
        <tbody>
            <!-- list라는 변수를 반복하여 각 항목을 하나씩 보여주는 코드 -->
            <c:forEach var="item" items="${list}">
                <tr>
                    <td>${item.bno}</td> <!-- 글 번호를 표시 -->
                    <td><a href="/guest/read?bno=${item.bno}">${item.title}</a></td> 
                    <!-- 글 제목을 클릭하면 해당 글을 읽을 수 있는 링크로 만들어줌 -->
                    <td>${item.writer}</td> <!-- 글 작성자를 표시 -->
                    <td>
                        <!-- 삭제 링크를 만들어주고, 클릭하면 삭제 여부를 묻는 확인 창이 뜨도록 설정 -->
                        <a href="/guest/del?bno=${item.bno}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                        <!-- 삭제 링크 클릭 시 글 번호(bno)를 함께 보내고, 확인 창을 띄운 후 삭제 진행 -->
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 다른 페이지로 이동할 수 있는 링크들 -->
    <a href="/guest/write">글쓰기</a> <!-- 글을 새로 쓸 수 있는 링크 -->
    <a href="http://localhost:8080/">홈으로 돌아가기</a>
</body>
</html>
