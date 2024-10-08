<%@page import="com.db.Board"%>
<%@page import="com.db.Dao"%>
<%@page import="com.db.Dto"%>
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
글번호 | 제목 | 작성자 <hr>
<%
String pageNum = request.getParameter("page");
if(pageNum == null){
	pageNum="1";
}
Dao dao = new Dao();
int totalPage = 0;
ArrayList<Dto> posts = null;
String searchWord = request.getParameter("world");
if(searchWord ==null||searchWord.equals("null")){
	posts = dao.list(pageNum);
	totalPage = dao.getTotalPageCount();
}else{
	posts = dao.listSearch(searchWord, pageNum);
	totalPage = dao.getSearchTotalPageCount(searchWord);
}
for(int i = 0; i<posts.size();i++){
%>
<%=posts.get(i).no %>	
<a href= "read.jsp?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
<%=posts.get(i).id%>	
<hr>
<%
}
int nPageNum = Integer.parseInt(pageNum);
//블럭 총 갯수 구하기
int totalBlock = (int)Math.ceil((double)totalPage/Board.PAGE_LINK_AMOUNT);
//블럭 번호 구하기 현재 페이지 번호 / 블럭당 페이지 수 = 결과값 소수점은 올림 처리 현재 몇번째인지 확인
int currrentBlockNo = (int)Math.ceil((double)nPageNum/Board.PAGE_LINK_AMOUNT);
//블럭 페이지 번호 구하기 전블록의 번호 * 전블럭의 전체 피이지 수 에 +1 한 값 출력
int blockStartNo = (currrentBlockNo - 1) * Board.PAGE_LINK_AMOUNT +1;

%>
</body>
</html>