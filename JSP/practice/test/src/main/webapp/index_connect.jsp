<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ArrayList<String>ss = new ArrayList<>();
ss.add("cat");
ss.add("dog");
ss.add("raccoon");
for(int i=0; i<3; i++){
	%>
<%= ss.get(i) %>
<% }

%>
</body>
</html>