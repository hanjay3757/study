<%@page import="com.peisia.db.Dto2"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.peisia.db.Daos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글번호, 제목, 작성자 <hr>

<%
String pageNum = request.getParameter("page");
if(pageNum==null){
	pageNum="1";
}
Daos daos = new Daos();
int count = daos.getPostCount();
int totalPage =0;
if(count % 3 == 0){
	totalPage = count/3;
}else{
	totalPage = count/3 +1;
}
ArrayList<Dto2> posts=daos.list(pageNum);
for(int i=0;i<posts.size();i=i+1){
%>
<%=posts.get(i).no%>
<a href="read.jsp?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
<%=posts.get(i).id%>
<hr>
<%
}
%>
<hr>



<%
for(int i=1;i<=totalPage;i=i+1){
%>

<a href="list.jsp?page=<%=i%>"><%=i%></a>

<%
}
%>
<hr>
<fieldset id="link">
	<legend>링크</legend>
	<a href="write.jsp">쓰기</a>
</fieldset>
<hr>
<fieldset id="log">
	<legend>로그</legend>
전체 글 수: <%=count %><br>
전체 페이지 수: <%=totalPage %><br>
현재 페이지 번호: <%=pageNum %><br>
</fieldset>


</body>
</html>