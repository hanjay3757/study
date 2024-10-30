<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- id = 변수명 class = 클래스경로 scope = 객체 유효 범위 지정(생략 시 디폴트값은 page 임)  -->
<%-- <jsp:useBean id="x" class="com.peisia.jsp.bean.test.Post" scope="page" /> --%>


<!-- 아래 문장으로 Post x; 객체가 생성됨 -->
<jsp:useBean id="x" class="com.peisia.jsp.bean.test.Post" />

<!-- 아래 문장으로 이전 페이지에서 넘어온 폼 데이터가 모두 Post 클래스 객체 x에
다 들어가게 된다. -->
<jsp:setProperty name="x" property="*"  />

<hr>
값 확인: 
<hr>
제목: <%=x.getTitle() %>
<hr>
작성자: <%=x.getId() %>
<hr>
내용: <%=x.getContent() %>
<hr>
<hr>
<hr>

EL로 찍기:<br> 
- EL 로 꺼내는 것도 가능함.<br> 
- 일반 폼을 ${param.title}식으로 꺼내는것과 달리 키값만 쓰는 방식으로 꺼내는게 가능해짐.
 
<hr>
제목: ${x.title}
<hr>
작성자: ${x.id}
<hr>
내용: ${x.content}

<a href="index.html">홈으로</a>
<hr>

</body>
</html>