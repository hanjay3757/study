<%@ page import="java.util.*" %>
<%@ page import="java.util.*,java.sql.*" %>  <!-- 필요한 Java 패키지 임포트 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <!-- 페이지의 인코딩 설정 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">  <!-- HTML 페이지에서 사용할 문자 인코딩 설정 -->
<title>Insert title here</title>
</head>
<body>

<!-- 1. 파라미터 'name' 출력 -->
1<br>
${param.name}  <!-- EL 표현식을 사용하여 'name' 파라미터 값을 출력 -->

<br><br>	
<!-- 2. 파라미터 'age' 출력 -->
2<br>
${param.age}   <!-- EL 표현식을 사용하여 'age' 파라미터 값을 출력 -->

<!-- EL(Express Language)을 이용하면 request, session, application 객체에 저장된 파라미터를 쉽게 출력할 수 있다. -->
<!-- 예시: http://example.com/yourpage?name=JohnDoe&age=30 -->
<!-- 위 URL로 요청이 오면 ${param.name}은 'JohnDoe', ${param.age}는 '30'을 출력한다. -->

</body>
</html>
