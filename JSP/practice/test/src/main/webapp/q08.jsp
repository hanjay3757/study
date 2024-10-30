<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<a href='q08_1.jsp'>페이지1</a>
<a href='q08_2.jsp'>페이지2</a>

<%
    int x = 0;
    int y = 0;
    try {
        x = Integer.parseInt(request.getParameter("x"));
        //매개변수를 가져와라  1이면 1을 가져오삼  x=1이랑 같음
        y = Integer.parseInt(request.getParameter("y"));
    } catch (NumberFormatException e) {
        // Handle the error, e.g., show a message or set default values
       
        return;
    }
%>

<%=x + y%>
