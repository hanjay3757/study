<%@ page import="java.util.*" %>
<%@ page import="java.util.*,java.sql.*" %>

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

<%
	String id = request.getParameter("id");
	if(id!=null){
%>
<%=id %>

el:
${param.id}



<c:if test="${param.id != null}">
	id: <b>${param.id}</b>
</c:if>
<c:if test="${param.id == ''}">
	<b>손님</b>
</c:if>

<%
		switch(id){
		case "1":
			String c = "노랑";
			break;
			default:
		}
	}			
%>

<c:choose>
	<c:when test="${param.color == 'yellow' }">
		<c:set var="c" value="노랑" />
	</c:when>
	<c:when test="${param.color == 'blue' }">
		<c:set var="c" value="파랑" />
	</c:when>
	<c:when test="${param.color == 'orange' }">
		<c:set var="c" value="주황" />
	</c:when>
	<c:otherwise>
		<c:set var="c" value="검정" />
	</c:otherwise>
</c:choose>
	
님이 좋아하는 색상은 ${c} 입니다.	
	
</body>
</html>



