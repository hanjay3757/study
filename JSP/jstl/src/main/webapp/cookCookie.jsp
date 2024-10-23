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
	Cookie cookieA = new Cookie("cookieA", "Apple");
	cookieA.setMaxAge(10);	// 초 단위. 1분
	//cookieA.setValue("Melone");
	response.addCookie(cookieA);
	
	
	Cookie cookieX = new Cookie("cookieX", "Grape");
	cookieX.setMaxAge(30);	// 초 단위. 1분
	response.addCookie(cookieX);
	
	Cookie cookieEternal = new Cookie("cookieEternal", "안썩는쿠키");
// 	cookieX.setMaxAge(30);	// 초 단위. 1분
	response.addCookie(cookieEternal);
%>

쿠키 구웠음<br>
쿠키 내용은 <a href="tasteCookie.jsp">여기로!!!</a>


</body>
</html>



