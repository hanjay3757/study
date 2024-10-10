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
//posts 변수를 선언하고 초기화
ArrayList<Dto> posts = null;

//request에서 "posts"라는 속성을 가져옵니다. 이 속성은 Object 타입으로 반환됩니다.
Object postsAttribute = request.getAttribute("posts");

//postsAttribute가 ArrayList<?> 타입인지를 확인합니다.
//여기서 ?는 제네릭 타입을 나타내며, 실제 타입은 상관없이 ArrayList인지만 확인합니다.
if (postsAttribute instanceof ArrayList<?>) {

 // 타입 캐스팅 전에 Unchecked cast 경고를 suppress 하기 위해 @SuppressWarnings 사용
 // @SuppressWarnings("unchecked")는 컴파일러에게 이 부분에서 발생할 수 있는 경고를 무시하도록 지시합니다.
 // 실제로 ArrayList<Dto>로 캐스팅할 때는 아래와 같이 진행합니다.
 @SuppressWarnings("unchecked")  // Unchecked cast 경고를 억제합니다.
 
 // postsAttribute를 ArrayList<Dto>로 캐스팅합니다.
 ArrayList<Dto> castedPosts = (ArrayList<Dto>) postsAttribute;
 
 // 캐스팅한 결과를 posts 변수에 할당합니다.
 posts = castedPosts;
 
} else {
 // 만약 "posts" 속성이 ArrayList<Dto> 타입이 아니거나 존재하지 않는 경우에 대한 처리입니다.
 
 // 예시로 빈 ArrayList를 생성하여 posts 변수에 할당합니다.
 // 이는 "posts" 속성이 없거나 타입이 일치하지 않는 경우 기본값을 할당하는 방식입니다.
 posts = new ArrayList<>(); // 빈 리스트를 할당합니다.
}





for(int i=0;i<posts.size();i=i+1){
%>

<%=posts.get(i).no%>
<a href="/board/read?no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
<%=posts.get(i).id%>
<hr>
<%
}
%>
<a href="/write.jsp">쓰기</a>

<a href="/board/list">list로</a>
<a href="/">홈으로</a>

</body>
</html>