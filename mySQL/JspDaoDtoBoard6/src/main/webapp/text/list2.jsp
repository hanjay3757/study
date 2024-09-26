<%@page import="com.peisia.db.Board"%>
<%@page import="com.peisia.db.Dto"%>
<%@page import="java.util.ArrayList"%>
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
글번호 | 제목 | 작성자 <hr>
<%
String pageNum=request.getParameter("page");
if(pageNum==null){
	pageNum="1";
}
Dao dao = new Dao();
int totalPage = 0;
ArrayList<Dto>posts = null;

String searchWord = request.getParameter("word");
if(searchWord==null || searchWord.equals("null")){
	posts = dao.list(pageNum);
	totalPage = dao.getTotalPageCount();
}else{
	posts=dao.listSearch(searchWord,pageNum);
	totalPage = dao.getSearchTotalPageCount(searchWord);
}
for(int i=0; i<posts.size();i++){
%>
	<%=posts.get(i).no%>
	<a href ="read2.jsp?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
<%=posts.get(i).id%>
<hr>
<%
}
int nPageNum = Integer.parseInt(pageNum);
int totalBlock = (int)Math.ceil((double)totalPage/Board.PAGE_LINK_AMOUNT);
int currentBlockNo = (int)Math.ceil((double)nPageNum/Board.PAGE_LINK_AMOUNT);
int blockStartNo = (currentBlockNo -1)*Board.PAGE_LINK_AMOUNT+1;
int blockEndNo = currentBlockNo *Board.PAGE_LINK_AMOUNT;
if(blockEndNo>totalPage){
	blockEndNo = totalPage;
}
int prevPage = 0;
int nextPage = 0;

boolean hasPrev = true;
if(currentBlockNo == 1){
	hasPrev = false;
}else{
	hasPrev = true;
	prevPage = (currentBlockNo - 1)*Board.PAGE_LINK_AMOUNT;
}
boolean hasNext = true;
if(currentBlockNo <totalBlock){
	hasNext =true;
	nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT +1;
}else{
	hasNext = false;
}
if(hasPrev){
	if(searchWord==null){
		%>
<a href = "list2.jsp?page=><%=prevPage%>">이전블럭가기</a>
<%
	}else{
%>
<a href="list2.jsp?page=<%=prevPage%>&word=<%=searchWord%>">이전블럭가기</a>
<%
	}
}
for(int i = blockStartNo; i<=blockEndNo;i++){
	if(nPageNum ==i){
		%>
		<%=i%>
<% 	}else{
	if(searchWord==null){
%>
<a href="list2.jsp?page=<%=i%>"><%=i%></a>
<%
	}else{
		String urlEncodedSearchWord = java.net.URLEncoder.encode(searchWord,"UTF-8");
%>
		<a href="list2.jsp?page=<%=i%>&word=<%=urlEncodedSearchWord%>"><%=i%></a>
<%     }	
	}
}
if(hasNext){
	if(searchWord==null){
		%>
		<a href = "list2.jsp?page=<%=nextPage%>">다음</a>
		<%
	}else{
		%>
		<a href = "list2.jsp?page=<%=nextPage%>&word=<%=searchWord%>">다음쓰</a>
		<%
		
	}
}
%>
<form action = "list.jsp">
<input name = "word">
<input type="submit" value="검색">
</form>
<a href = "write2.jsp">쓰기</a> <a href="list2.jsp">list</a>
</body>
</html>