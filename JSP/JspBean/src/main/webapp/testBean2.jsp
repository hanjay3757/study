<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="test_bean" class="com.peisia.jsp.bean.test.Bean" scope="page" />

	<jsp:setProperty name="test_bean" property="*" /> <!-- name = cat, nuber = 1111 이 한방에 들어감. 이 한줄로. -->
	
	<jsp:getProperty name="test_bean" property="name" />
	<jsp:getProperty name="test_bean" property="number" />

</body>
</html>