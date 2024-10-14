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
	Cookie [] cookies = request.getCookies();

	System.out.println("쿠키길이:"+cookies.length);
	if(cookies!=null){
		for(int i=0; i<cookies.length; i++){
// 			if(cookies[i].getName().equals("cookieA")){
%>
			쿠키 이름: <%=cookies[i].getName() %>
			쿠키 값: <%=cookies[i].getValue() %>
			<hr>

<%				
// 			}
		}
	}
%>
<a href="index.html">홈으로</a>
<hr>
<fieldset>
	<legend>q26. EL을 써서 출력</legend>
	쿠키 cookieA 의 값(value) : ${cookie.cookieA.value}
	<hr>
	쿠키 cookieX 의 값(value) : ${cookie.cookieX.value}
	<hr>
	쿠키 cookieEternal 의 값(value) : ${cookie.cookieEternal.value}
</fieldset>
<hr>

</body>
</html>