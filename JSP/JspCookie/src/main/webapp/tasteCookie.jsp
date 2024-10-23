<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
    // 쿠키 배열을 가져옵니다. request.getCookies() 메서드를 사용하여 쿠키들을 가져옵니다.
    Cookie [] cookies = request.getCookies();

    // 쿠키 배열의 길이를 출력합니다. 쿠키가 몇 개 있는지 확인할 수 있습니다.
    System.out.println("쿠키길이:"+cookies.length);

    // 쿠키 배열이 null이 아니라면 쿠키들을 반복하여 처리합니다.
    if(cookies != null){
        // 쿠키 배열을 순회하면서
        for(int i = 0; i < cookies.length; i++){
            // 만약 쿠키의 이름이 "myCookie"와 같다면 해당 쿠키의 값을 출력합니다.
            if(cookies[i].getName().equals("myCookie")){
%>
                <!-- 쿠키 이름과 값을 출력합니다. -->
                쿠키 이름: <%=cookies[i].getName() %><br>
                쿠키 값: <%=cookies[i].getValue() %><br>
<%                
            }
        }
    }
%>

<!-- 홈으로 가는 링크를 제공합니다. -->
<a href="index.html">홈으로</a>
<hr>

<!-- EL(Expression Language)을 사용하여 "myCookie"라는 이름의 쿠키 값에 접근하여 출력합니다. -->
<!-- EL에서 cookie.myCookie.value는 "myCookie"라는 이름의 쿠키 객체의 값을 가져옵니다. -->
EL 로 쿠키 키 myCookie 의 값 찍어보기 :
${cookie.myCookie.value}

</body>
</html>
