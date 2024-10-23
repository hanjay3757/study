<%@page import="java.net.URLEncoder"%>
<%@page import="com.db.Board"%>
<%@page import="com.db.Dao"%>
<%@page import="com.db.Dto"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
    /* 기본적인 스타일 */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        margin: 0;
    }

    .editor-container {
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 800px;
        border-radius: 8px;
    }

    .controls {
        margin-bottom: 15px;
    }

    label {
        margin-right: 10px;
    }

    select, input[type="color"] {
        margin-right: 20px;
    } // 색 변경 적용을 select에다가 집어넣음

    textarea {
        width: 100%;
        height: 300px;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: none;
    }

    .page-links {
        margin-top: 20px;
    }
</style>

</head>
<body>

<h2>글번호 | 제목 | 작성자</h2>
<hr>

<% 
// 페이지 번호를 가져옵니다. 기본값은 1
String pageNum = request.getParameter("page");
if(pageNum == null) {
    pageNum = "1";
}

// DAO 객체를 생성하여 DB에서 게시글 목록을 가져옵니다.
Dao dao = new Dao();
int totalPage = 0; // 전체 페이지 수
ArrayList<Dto> posts = null; // 게시글 목록
String searchWord = request.getParameter("word"); // 검색어를 가져옵니다.

// 검색어가 없으면 모든 게시글을 가져오고, 있으면 검색된 게시글만 가져옵니다.
if(searchWord == null || searchWord.equals("null")) {
    posts = dao.list(pageNum);
    totalPage = dao.getTotalPageCount();
} else {
    posts = dao.listSearch(searchWord, pageNum);
    totalPage = dao.getSearchTotalPageCount(searchWord);
}

// 게시글 목록을 출력합니다.
for(int i = 0; i < posts.size(); i++) {
%>
    <%=posts.get(i).no%> | 
    <a href="read.jsp?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a> | 
    <%=posts.get(i).id%>
    <hr>
<%
// 반복문 끝
}

// 페이지 블록 처리를 위한 변수들
int nPageNum = Integer.parseInt(pageNum); // 현재 페이지 번호
int totalBlock = (int)Math.ceil((double)totalPage / Board.PAGE_LINK_AMOUNT); // 전체 블록 수
int currentBlockNo = (int)Math.ceil((double)nPageNum / Board.PAGE_LINK_AMOUNT); // 현재 블록 번호
int blockStartNo = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT + 1; // 블록 시작 페이지 번호
int blockEndNo = currentBlockNo * Board.PAGE_LINK_AMOUNT; // 블록 끝 페이지 번호

// 블록 끝 페이지 번호가 전체 페이지 수를 넘지 않도록 수정
if(blockEndNo > totalPage) {
    blockEndNo = totalPage;
}

// 이전/다음 블록 링크 계산을 위한 변수들
int prevPage = 0;
int nextPage = 0;
boolean hasPrev = currentBlockNo > 1; // 이전 블록이 존재하는지 여부
boolean hasNext = currentBlockNo < totalBlock; // 다음 블록이 존재하는지 여부

// 이전 블록 페이지 번호 계산
if(hasPrev) {
    prevPage = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT;
}

// 다음 블록 페이지 번호 계산
if(hasNext) {
    nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT + 1;
}

// 이전 블록 링크 출력
if(hasPrev) {
    if(searchWord == null) {
%>
        <a href="list.jsp?page=<%=prevPage%>">이전 페이지</a>
<%
    } else {
%>
        <a href="list.jsp?page=<%=prevPage%>&word=<%=searchWord%>">이전 블록</a>
<%
    }
}

// 페이지 번호 출력
for(int i = blockStartNo; i <= blockEndNo; i++) {
    if(nPageNum == i) {
        // 현재 페이지는 숫자만 표시
%>
        <%=i%>
<%
    } else {
        // 다른 페이지는 링크를 생성
        if(searchWord == null) {
%>
            <a href="list.jsp?page=<%=i%>"><%=i%></a>
<%
        } else {
            String urlEncodedSearchWord = URLEncoder.encode(searchWord, "UTF-8");
%>
            <a href="list.jsp?page=<%=i%>&word=<%=urlEncodedSearchWord%>"><%=i%></a>
<%
        }
    }
}

// 다음 블록 링크 출력
if(hasNext) {
    if(searchWord == null) {
%>
        <a href="list.jsp?page=<%=nextPage%>">다음 블록</a>
<%
    } else {
%>
        <a href="list.jsp?page=<%=nextPage%>&word=<%=searchWord%>">다음 블록</a>
<%
    }
}
%>

<!-- 검색 폼 -->
<form action="list.jsp">
    <input name="word" placeholder="검색어 입력">
    <input type="submit" value="검색">
</form>

<!-- 스타일 변경을 위한 폼 -->
<div class="editor-container">
    <div class="controls">
        <!-- 글꼴 선택 드롭다운 -->
        <label for="font-family">글꼴 선택:</label>
        <select id="font-family">
            <option value="Arial">Arial</option>
            <option value="Verdana">Verdana</option>
            <option value="Courier New">Courier New</option>
            <option value="Tahoma">Tahoma</option>
        </select>

        <!-- 글자 크기 선택 드롭다운 -->
        <label for="font-size">글자 크기:</label>
        <select id="font-size">
            <option value="16px">16px</option>
            <option value="18px">18px</option>
            <option value="20px">20px</option>
            <option value="24px">24px</option>
        </select>

        <!-- 글자 색 선택 -->
        <label for="font-color">글자 색:</label>
        <input type="color" id="font-color" value="#000000">
    </div>

    <!-- 글 작성 텍스트 영역 -->
    <textarea id="editor" placeholder="글을 작성하세요..."></textarea>
</div>

<!-- 글쓰기 버튼 -->
<a href="write.jsp">글쓰기</a>
<a href="list.jsp">목록으로</a>

<script>
// 스타일 변경 이벤트 처리
document.getElementById('font-family').addEventListener('change', function() {
    document.getElementById('editor').style.fontFamily = this.value;
});
document.getElementById('font-size').addEventListener('change', function() {
    document.getElementById('editor').style.fontSize = this.value;
});
document.getElementById('font-color').addEventListener('input', function() {
    document.getElementById('editor').style.color = this.value;
});
</script>
</body>
</html>
