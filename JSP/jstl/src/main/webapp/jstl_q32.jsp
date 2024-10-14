<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:url var="url1" value="https://www.google.co.kr" >
	<c:param name="q" value="고양이" />
	<c:param name="safe" value="off" />
</c:url>

<a href="${url1}"> 구글에서 고양이 검색 </a>

	
</body>
</html>



