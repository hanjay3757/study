<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 처리</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    // 입력값 검증
    if (id == null || id.trim().isEmpty() || pw == null || pw.trim().isEmpty()) {
        out.println("<script>alert('아이디와 비밀번호를 모두 입력해주세요.'); history.back();</script>");
        return;
    }

    Connection con = null;
    PreparedStatement ps = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
        
        // SQL 문 준비
        String sql = "INSERT INTO member (id, password) VALUES (?, ?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, pw); // 비밀번호는 해시 처리하는 것이 좋음

        // 데이터베이스에 삽입
        int resultCount = ps.executeUpdate();
        
        if (resultCount > 0) {
            response.sendRedirect("index.html");
        } else {
            out.println("<script>alert('회원가입에 실패했습니다.'); history.back();</script>");
        }
        
    } catch (Exception e) {
        out.println("<script>alert('시스템 오류가 발생했습니다.'); history.back();</script>");
        e.printStackTrace();
    } finally {
        // 자원 해제
        if (ps != null) try { ps.close(); } catch (Exception e) {}
        if (con != null) try { con.close(); } catch (Exception e) {}
    }
%>
</body>
</html>
