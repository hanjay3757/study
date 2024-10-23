<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="test_bean" class="com.peisia.q23.Bean" scope="page" />

	<jsp:setProperty name="test_bean" property="*" /> <!-- name = cat, nuber = 1111 이 한방에 들어감. 이 한줄로. -->
	
	<jsp:getProperty name="test_bean" property="name" />
	<jsp:getProperty name="test_bean" property="number" />

<hr>
<fieldset>
	<legend>일반 jsp 객체로 접근해서 찍기</legend>
	<%=test_bean.getName()%> <hr>
	<%=test_bean.getNumber()%> <hr>
</fieldset>
<fieldset>
	<legend>q26. EL을 써서 출력</legend>
	EL로 찍기: ${test_bean.name} <hr>
	EL로 찍기: ${test_bean.number} <hr>
</fieldset>




</body>
</html>