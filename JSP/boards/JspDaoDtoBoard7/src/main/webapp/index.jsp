<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="list.jsp">자유게시판</a>



<%
	String loginId = (String)session.getAttribute("xxx");
%>

세션에서 꺼낸 id:
<%=loginId %>

<%
if(loginId == null){
%>

	<a href="reg.html">회원가입</a>
	<form action="login.jsp">
		<input name="id">
		<input name="pw">
		<input type="submit" value="로그인">
	</form>

<%
} else {

%>
	<a href="logout.jsp">로그아웃</a>

<%
}
%>


</body>
</html>