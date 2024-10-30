<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String id = request.getParameter("id");

    Connection con = null;
    PreparedStatement pst = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
        
        // Use PreparedStatement for security and efficiency
        String sql = "INSERT INTO your_table_name (title, content, id) VALUES (?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, title);
        pst.setString(2, content);
        pst.setString(3, id);
        
        int resultCount = pst.executeUpdate(); // Execute the update
        
        if(resultCount == 1) {
            System.out.println("==== 글쓰기 성공");
        } else {
            System.out.println("==== 글쓰기 실패");
        }
    } catch (Exception e) {        
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    response.sendRedirect("list.jsp"); // Redirect to list page
%>
