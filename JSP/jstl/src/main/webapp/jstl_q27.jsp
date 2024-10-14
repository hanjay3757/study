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
    String str1 = null;
    request.setAttribute("str1", str1); // str1 값을 request에 설정
%>

<%-- Java 코드에서 처리된 값 출력 --%>
<%
    if (str1 != null) {
        out.println(str1);  // str1 값이 null이 아니면 출력
    } else {
        out.println("야옹이");  // str1 값이 null이면 "야옹이" 출력
    }
%>

<%-- JSTL을 사용하여 출력 --%>
<c:out value="${str1}" default="야옹이" /><br>

</body>
</html>
