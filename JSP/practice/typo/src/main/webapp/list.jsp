<%@page import="java.net.URLEncoder"%>
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
int currentBlockNo = (int)Math.ceil((double)nPageNum/Board.PAGE_LINK_AMOUNT);
//3 블럭 페이지 번호 구하기 전블록의 번호 * 전블럭의 전체 피이지 수 에 +1 한 값 출력
int blockStartNo = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT +1;
//4블럭 페이지 마지막 번호 구하기
int blockEndNo = currentBlockNo * Board.PAGE_LINK_AMOUNT;

// 12 까직 밖에 없는데 15라 넘겨 버릴경우에  totalPage 값을 강제로 변환
// 블럭 마지막 페이지 번호가 전체 페이지 마지막 번호보다 큰경우 = 블럭 마지막 번호를 페이지 마지막 번호로 저장하는 것으로 출력하기 
if(blockEndNo > totalPage){
	blockEndNo = totalPage;
}
//5 <이전/ 다음> 다른 페이지로 넘어가는 변수를 초기화 하는 값
int prevPage = 0;
int nextPage = 0;

//6 이전 / 다음 관련된 코드 정리
boolean hasPrev = true; // 이전 블럭으로 가는 기능 저장 값을 초기화하는것
if(currentBlockNo == 1){
	hasPrev = false;
	//1페이지에 사용자가 있을때 이전이라는 블럭 (카드)를 사용불가 상태로 변환

}else{
	hasPrev = true;
//6페이지에 있을때 전 페이지인 1부터 5 가있는 블럭으로 이동하는거
prevPage = (currentBlockNo-1)*Board.PAGE_LINK_AMOUNT;
}
boolean hasNext = true;
if(currentBlockNo < totalBlock){
	hasNext = true;
	nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT +1;
}else{
	hasNext = false;
}
//7이전 처리 // 이전 블럭 이동이 가능할경우 미리 계산된 블럭 이동시 페이지 번호를 링크에 전달
if(hasPrev){
if(searchWord == null){
%>
<a href ="list.jsp?page=<%=prevPage%>">이전 페이지 가기</a>
<%
}else{
%>
<a href = "list.jsp?page=<%=prevPage%>&word=<%=searchWord%>">이전 블럭 가기</a>
<%
 }
}
//8현재 블럭의 페이지 시작번호와 끝번호를 이용하여 반복문의 시작값 긑값을 정하고 이를 출력
for(int i=blockStartNo;i<=blockEndNo;i++){
	if(nPageNum==i){
	%>
	<%=i%>
	<%
}else{
	if(searchWord == null){
%>
<a href="list.jsp?page=<%=i%>"><%=i%></a>
<%

	}else{
		String urlEncodedSearchWord = URLEncoder.encode(searchWord,"UTF-8");
	%>
	🌰<a href="list.jsp?page=<%=i %>&word=<%=urlEncodedSearchWord%>"><%=i %></a>🌰
	<%
		}
	}
}
if(hasNext){
	if(searchWord==null){
		%>
		<a href="list.jsp?page=<%=nextPage%>">다음 블럭</a>
<%	}else{
	%>
	<a href="list.jsp?page=<%=nextPage%>&word=<%=searchWord%>">🐿️다음블럭가기🐿️</a>
	<%
}
}	

%>
<form action="list.jsp">
	<input name="word">
	<input type="submit" value="검색">
</form>
<a href="write.jsp">쓰기</a><a href="list.jsp">list로</a>
</body>
</html>