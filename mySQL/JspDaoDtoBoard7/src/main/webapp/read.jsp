<%@page import="com.peisia.db.Dto"%>
<%@page import="com.peisia.db.Dao"%>
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
String no=request.getParameter("no");
String id = (String)session.getAttribute("loginId");
// 로그인 표식을하고 딴페이지에서 로그인되었다 표식
Dao dao=new Dao();
Dto d=dao.read(no);
%>
<!-- session 접속 물림 = 각 접속마다 딴컴접속 가능 -->
<!-- 처리를 톰캣에서 관리 -->
<%=d.no%>
<%=d.id%>
<%=d.title%>
<hr>
<%=d.text%>

<a href="ServletDel?no=<%=no%>">삭제</a>
<a href="edit.jsp?no=<%=no%>">수정</a>
<a href="list.jsp">리스트로</a>

</body>
</html>