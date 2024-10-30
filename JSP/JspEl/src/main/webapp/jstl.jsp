<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${param.id != null}" >
	<b>${param.id}</b>
</c:if>

<c:choose>
	<c:when test="${param.id == 'yellow'}" >
		<c:set var="c" value="노란색" />
	</c:when>
	<c:when test="${param.id == 'pink'}" >
		<c:set var="c" value="핑크" />
	</c:when>
	<c:otherwise>
		<c:set var="c" value="검정" />
	</c:otherwise>
</c:choose>

고른 색상은: ${c}

</body>
</html>