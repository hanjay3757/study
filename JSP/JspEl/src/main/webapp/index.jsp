<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="reg.html">[q19]회원가입</a>
<hr>
<a href="login.html">[q20]로그인</a>
<hr>
<a href="cookCookie.jsp">[q21]쿠키 굽기</a>
<hr>
<a href="countCookie.jsp">[q22]쿠키 카운터</a>
<hr>
<a href="testBean.jsp?name=개&number=1">[q23]테스트 빈</a>
<hr>
<fieldset>
	<legend>[q24]빈</legend>
	<form action="testBean.jsp">
		이름<input name="name">
		넘버<input name="number">
		<input type="submit" value="빈 저장">
	</form>
</fieldset>
<fieldset>
	<legend>[q25]빈</legend>
	<form action="testBean2.jsp">
		이름<input name="name">
		넘버<input name="number">
		<input type="submit" value="빈 저장">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	</form>
</fieldset>
<fieldset>
	<legend>[q26]EL</legend>
	<form action="eltest.jsp" method="get">
		이름<input name="name">
		나이<input name="age">
		<input type="submit" value="el테스트">
	</form>
</fieldset>
</body>
</html>