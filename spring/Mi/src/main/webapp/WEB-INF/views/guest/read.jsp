<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% --%>
// 	GuestDto read = (GuestDto)request.getAttribute("read");
// 	long bno = read.getBno();
// 	String btext = read.getBtext();
<%-- %>	 --%>

<!-- 글본문 -->
<%-- 글번호:<%=bno %> --%>
<%-- 글내용:<%=btext %> --%>
<!-- el함수 ㄴ 위에꺼랑 같은거 -->
글 읽기 
<hr>
글번호:${read.bno}
<hr>
글내용:${read.btext}
</body>
</html>