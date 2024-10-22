<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="cp" value ="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href= "${cp}/resources/common.css"/>
<title>Insert title here</title>
</head>
<body>
<table>
        <tr>
            <td>번호</td>
            <td>내용</td>
        </tr>
<c:forEach var = "guest" items="${list}">
 <tr>
 <td>${guest.bno}</td>
   <td><a href="/guest/read?bno=${guest.bno}">${guest.btext}</a></td>
   </tr>
   </c:forEach>
</body>
</html>