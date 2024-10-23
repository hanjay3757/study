<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String cookieName = "myCookie";
Cookie cookie = new Cookie(cookieName, "apple");
cookie.setMaxAge(60);
cookie.setValue("Melone");
response.addCookie(cookie);

%>
쿠키 구웠음 <br>
쿠키 내용은 <a href= "tasteCookie.jsp">여기</a>
</body>
</html>