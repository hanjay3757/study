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
	boolean hasCookie = false;

	Cookie [] cookies = request.getCookies();
	if(cookies!=null){
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("countCookie")){
				hasCookie = true;
				
				String countString = cookies[i].getValue();
%>
				쿠키 이름: <%=cookies[i].getName() %>
				쿠키 값: <%=countString %>
				<br>
				<%=countString %> 번째 방문!
<%				
				
				//쿠키 갱신
				int count = Integer.parseInt(countString) + 1;
				Cookie cookie = new Cookie("countCookie", count+"");
				response.addCookie(cookie);				
			}
		}
	}
	
	if(hasCookie == false){
		Cookie cookie = new Cookie("countCookie", "1");
		//cookie.setMaxAge(60);	// 초 단위. 1분
		response.addCookie(cookie);
%>		
		쿠키 이름: <%=cookie.getName() %>
		쿠키 값: <%=cookie.getValue() %>
		<br>
		<%=cookie.getValue() %> 번째 방문!
		
<%		
		cookie = new Cookie("countCookie", "2");
		response.addCookie(cookie);		
	}
	
%>
</body>
</html>