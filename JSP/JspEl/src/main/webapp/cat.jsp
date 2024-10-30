<%@page import="com.peisia.jsp.el.test.Cat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% --%>
// 	Cat cat = (Cat)request.getAttribute("kitty");
// 	String name = cat.name;
// 	int age = cat.age;
<%-- %> --%>


<%-- 키티 이름 : <%=name %> --%>
<!-- <hr> -->
<%-- 키티 나이 : <%=age %> --%>
<hr>
<hr>
<hr>
키티 객체 :${kitty}
<hr>
키티 이름 :${kitty.name}
<hr>
키티 나이 :${kitty.age}
<hr>
x찍기 : ${x}


</body>
</html>