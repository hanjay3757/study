<%@page import="com.peisia.jsp.board.BoardListProcessor"%>
<%@page import="com.peisia.jsp.board.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.peisia.jsp.board.dto.Dto"%>
<%@page import="com.peisia.jsp.board.dao.DaoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글번호, 제목, 작성자<hr>
<%
BoardListProcessor blp = (BoardListProcessor)request.getAttribute("blp"); 
ArrayList<Dto> posts = blp.getPosts();


for(int i=0;i<posts.size();i=i+1){
%>

<%=posts.get(i).no%>
<a href="/board/read?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
<%=posts.get(i).id%>
<hr>
<%
}
%>
<hr><hr>
<%=blp.getHtmlPageList()%>
<hr><hr>
<a href="/write.jsp">쓰기</a>

<a href="/board/list">list로</a>
<a href="/">홈으로</a>


</body>
</html>