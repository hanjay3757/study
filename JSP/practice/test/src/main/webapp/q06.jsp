<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- q07.jsp 페이지를 동적으로 포함하고, 파라미터를 전달합니다 -->
<!-- q07로이동  -->
<jsp:include page="q07.jsp" flush="true">
    <jsp:param name="x" value="1" />
    <jsp:param name="y" value="2" />
</jsp:include>

<p>야옹</p>

</body>
</html>



