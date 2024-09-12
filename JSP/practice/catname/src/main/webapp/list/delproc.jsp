<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String delNum = request.getParameter("num");
try{ 
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cats","root","root");
	Statement st = con.createStatement();
	String sql = "delete from cat_board where num="+delNum;
	
	System.out.println("전송하려는 sql"+sql);
	int resultCount = st.executeUpdate(sql);
	if(resultCount ==1){
	System.out.println("삭제성공");
	}else{
	System.out.println("삭제실패");
	}
}catch(Exception e){
e.printStackTrace();	
}
response.sendRedirect("list.jsp");


%>
</html>