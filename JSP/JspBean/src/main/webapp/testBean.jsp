<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--
  1. JSP Bean 사용
  JSP에서 Bean을 사용하여 데이터를 처리할 때는 <jsp:useBean> 태그를 사용하여 Bean을 선언하고,
  <jsp:setProperty> 태그로 속성을 설정하고, <jsp:getProperty> 태그로 속성 값을 출력합니다.
  
  아래는 "test_bean"이라는 이름으로 com.peisia.jsp.bean.test.Bean 클래스를 사용할 것입니다.
  이 Bean은 예시로 'name'과 'number'라는 두 속성을 가질 것입니다.
-->

<!-- 2. JSP Bean 선언 -->
<jsp:useBean id="test_bean" class="com.peisia.jsp.bean.test.Bean" scope="page" />

<!--
  <jsp:useBean> 태그는 `test_bean`이라는 id로 `com.peisia.jsp.bean.test.Bean` 클래스를 사용한다고 정의합니다.
  - id="test_bean": JSP 페이지 내에서 이 Bean을 참조할 때 사용할 이름입니다.
  - class="com.peisia.jsp.bean.test.Bean": 사용할 Java 클래스의 경로입니다.
  - scope="page": 이 Bean의 범위를 'page'로 설정하여, 해당 페이지 내에서만 사용하도록 제한합니다.
  이 범위는 현재 페이지에서만 Bean이 사용될 수 있다는 의미입니다.
-->

<!-- 3. Bean 속성 설정 -->
<jsp:setProperty name="test_bean" property="name" /> <!-- name - cat -->
<jsp:setProperty name="test_bean" property="number" />

<!--
  <jsp:setProperty> 태그는 Bean의 속성값을 설정하는 데 사용됩니다.
  위 코드는 "test_bean" Bean의 `name` 속성과 `number` 속성을 설정하려고 시도합니다.
  그러나 이 코드는 `name`과 `number` 값을 전달하지 않고 있습니다. 기본적으로 request 파라미터에서 값을 가져옵니다.
  예를 들어, URL에 `name=cat`와 `number=123`이 있다면, 이 값들이 Bean의 속성에 설정됩니다.
-->

<!-- 4. Bean 속성 값 출력 -->
<jsp:getProperty name="test_bean" property="name" />
<jsp:getProperty name="test_bean" property="number" />

<!--
  <jsp:getProperty> 태그는 Bean의 속성 값을 출력합니다.
  위 코드는 `test_bean`의 `name` 속성과 `number` 속성을 각각 출력합니다.
  예를 들어, `name`이 "cat"이고, `number`가 123이라면, 출력은 다음과 같습니다:
  - "cat"
  - "123"
-->

</body>
</html>
