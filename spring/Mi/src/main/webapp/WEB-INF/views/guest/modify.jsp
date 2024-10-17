<%@page import="com.peisia.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
    <h2>글 수정</h2>
    <p>여기 진짜 오나???</p>
    
    <%
        GuestDto read = (GuestDto)request.getAttribute("read");
        long bno = read.getBno();
        String btext = read.getBtext();
    %>  

    <p>글번호: <%=bno%></p>
    
    <form action="/guest/modify" method="post">
        <input type="hidden" name="bno" value="<%=bno %>" >
        
        <!-- 글 내용을 수정할 수 있는 텍스트 영역 (textarea) -->
        <textarea name="btext" rows="10" cols="50"><%=btext %></textarea>
        
        <br><br>
        <input type="submit" value="수정하기">
    </form>
</body>
</html>
