<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.peisia.db.Board"%>
<%@page import="java.util.ArrayList"%>
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
글번호, 제목, 작성자<hr>

<%
    // 현재 페이지 번호를 가져옵니다. 기본값은 1입니다. 
    String pageNum=request.getParameter("page");
    if(pageNum==null){
        pageNum="1";
    }

    // Dao 객체 생성 (미리짜둔 코드에 입력한 번호 입력)
    Dao dao=new Dao();

    int totalPage = 0; // 총 페이지 수를 저장할 변수

    ArrayList<Dto> posts = null; // 게시글 리스트를 저장할 변수
    String searchWord=request.getParameter("word"); // 검색어를 가져옵니다.

    // 검색어가 없을 경우
    if(searchWord==null || searchWord.equals("null")) {
        posts=dao.list(pageNum); // 전체 게시글 리스트를 가져옵니다.
        totalPage = dao.getTotalPageCount(); // 전체 페이지 수를 계산합니다.
    } else { // 검색어가 있는 경우
        posts=dao.listSearch(searchWord, pageNum); // 검색어로 게시글 리스트를 가져옵니다.
        totalPage = dao.getSearchTotalPageCount(searchWord); // 검색 결과에 대한 전체 페이지 수를 계산합니다.
    }

    // 게시글 리스트를 출력합니다.
    for(int i=0; i<posts.size(); i=i+1) {
%>

<%=posts.get(i).no%> <!-- 글 번호 출력 -->
<a href="read.jsp?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a> <!-- 제목을 클릭하면 읽기 페이지로 이동 -->
<%=posts.get(i).id%> <!-- 작성자 출력 -->
<hr>
<%
    }
%>

<a href="write.jsp">쓰기</a> <!-- 새 게시글 작성 페이지로 이동 -->

<hr><hr>

<%
    int nPageNum = Integer.parseInt(pageNum); // 페이지 번호를 정수로 변환

    // 블럭 처리: 전체 블럭 수 계산
    int totalBlock = (int)Math.ceil((double) totalPage / Board.PAGE_LINK_AMOUNT); // 전체 블럭 수

    // 현재 블럭 번호 계산
    int currentBlockNo = (int)Math.ceil((double)nPageNum / Board.PAGE_LINK_AMOUNT);

    // 블럭 시작 페이지 번호 계산
    int blockStartNo = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT + 1;

    // 블럭 끝 페이지 번호 계산
    int blockEndNo = currentBlockNo * Board.PAGE_LINK_AMOUNT;
    // 블럭 끝 페이지 번호가 총 페이지 수를 초과할 경우 처리
    if(blockEndNo > totalPage) {
        blockEndNo = totalPage;
    }

    // 이전/다음 블럭 처리 초기화
    int prevPage = 0;
    int nextPage = 0;

    // 이전 블럭 이동 가능 여부
    boolean hasPrev = true; // 기본값 true로 설정
    if(currentBlockNo == 1) { // 현재 블럭이 1번이면
        hasPrev = false; // 이전 블럭 이동 불가능
    } else {
        prevPage = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT; // 이전 블럭의 마지막 페이지
    }

    // 다음 블럭 이동 가능 여부
    boolean hasNext = true; // 기본값 true로 설정
    if(currentBlockNo < totalBlock) { // 현재 블럭이 마지막 블럭보다 작으면
        nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT + 1; // 다음 블럭의 첫 페이지
    } else {
        hasNext = false; // 다음 블럭 이동 불가능
    }

    // 이전 블럭 링크 출력
    if(hasPrev) {
        if(searchWord==null) { // 일반 리스트일 경우
%>
            <a href="list.jsp?page=<%=prevPage%>">🐿️이전블럭가기🐿️</a>
<%
        } else { // 검색어로 검색한 리스트
%>
            <a href="list.jsp?page=<%=prevPage%>&word=<%=searchWord%>">🐿️이전블럭가기🐿️</a>
<%
        }
    }

    // 현재 블럭의 페이지 링크 출력
    for(int i=blockStartNo; i<=blockEndNo; i++) {
        if(nPageNum == i) {
%>
            🌰<%=i %>🌰 <!-- 현재 페이지 표시 -->
<%
        } else {
            if(searchWord==null) { // 일반 리스트일 경우
%>
                🌰<a href="list.jsp?page=<%=i %>"><%=i %></a>🌰 <!-- 페이지 링크 -->
<%
            } else { // 검색어로 검색한 리스트
                String urlEncodedSearchWord = URLEncoder.encode(searchWord, StandardCharsets.UTF_8.toString()); // 검색어 URL 인코딩
%>
                🌰<a href="list.jsp?page=<%=i %>&word=<%=urlEncodedSearchWord%>"><%=i %></a>🌰 <!-- 페이지 링크 -->
<%
            }
        }
    }

    // 다음 블럭 링크 출력
    if(hasNext) {
        if(searchWord==null) { // 일반 리스트일 경우
%>
            <a href="list.jsp?page=<%=nextPage%>">🐿️다음블럭가기🐿️</a>
<%
        } else { // 검색어로 검색한 리스트
%>
            <a href="list.jsp?page=<%=nextPage%>&word=<%=searchWord%>">🐿️다음블럭가기🐿️</a>
<%
        }
    }
%>

<!-- 검색 폼 -->
<form action="list.jsp">
    <input name="word"> <!-- 검색어 입력 필드 -->
    <input type="submit" value="검색"> <!-- 검색 버튼 -->
</form>

<a href="list.jsp">list로</a> <!-- 리스트 페이지로 이동 -->

</body>
</html>
