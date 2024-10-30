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
	Cookie cookie = new Cookie("myCookie", "Apple");
	cookie.setMaxAge(60);	// 초 단위. 1분
	//cookie.setValue("Melone");
	response.addCookie(cookie);
%>

쿠키 구웠음<br>
쿠키 내용은 <a href="tasteCookie.jsp">여기로!!!</a>


</body>
</html>



