<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.Posts" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Community Board</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <nav id="top_menu">
            <div class="logo">
                <h1>Community</h1>
            </div>
            <div class="nav-links">
                <div class="dropdown">
                    <a href="index.jsp" class="nav-item">일반게시판</a>
                    <div class="dropdown-content">
                        <a href="dog.jsp">댕댕이</a>
                        <a href="cat.jsp">야옹이</a>
                    </div>
                </div>
                <a href="#" class="nav-item">홈</a>
                <a href="#" class="nav-item">공지사항</a>
                <a href="#" class="nav-item">로그인</a>
                <a href="#" class="nav-item highlight">회원가입</a>
                <a href="admin.jsp" class="nav-item">관리자</a>
            </div>
        </nav>
    </header>

    <div class="ss">
        <section class="post-form-section">
            <form action="posts" method="post" class="post-form">
                <div class="form-header"></div>
                <div class="form-group">
                    <input type="text" name="title" placeholder="제목을 입력하세요" required>
                    <textarea name="content" placeholder="내용을 입력하세요" rows="1" required></textarea>
                    <input type="url" name="videoUrl" placeholder="YouTube 영상 URL을 입력하세요" required>
                    <input type="hidden" name="board" value="<%=request.getParameter("board")%>">
                </div>
                <button type="submit" class="submit-btn">게시물 등록</button>
            </form>
        </section>
    </div>

    <main class="container" id="posts-container">
        <%
            String DB_URL = "jdbc:mysql://localhost:3306/my_cat";
            String DB_USER = "root";
            String DB_PASSWORD = "root";
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String board = request.getParameter("board");
                
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM posts WHERE board = ? ORDER BY timestamp DESC");
                pstmt.setString(1, board);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String videoUrl = rs.getString("videoUrl");
                    long timestamp = rs.getLong("timestamp");
                    int id = rs.getInt("id");
        %>
                    <article class="post" data-id="<%=id%>">
                        <h2><%=title%></h2>
                        <p><%=content%></p>
                        <div class="video-container">
                            <iframe src="<%=videoUrl.replace("watch?v=", "embed/")%>" 
                                    frameborder="0" 
                                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                    allowfullscreen>
                            </iframe>
                        </div>
                        <div class="post-footer">
                            <span class="timestamp"><%=new Date(timestamp)%></span>
                            <button onclick="deletePost(<%=id%>)" class="delete-btn">삭제</button>
                        </div>
                    </article>
        <%
                }
                rs.close();
                pstmt.close();
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        %>
    </main>

    <footer>
        <div class="footer-content">
            <div class="footer-info">
                <p>&copy; 2024 Community Board. All rights reserved.</p>
            </div>
            <div class="footer-links">
                <a href="#">이용약관</a>
                <a href="#">개인정보처리방침</a>
                <a href="#">문의하기</a>
            </div>
        </div>
    </footer>

    <script>
        function deletePost(id) {
            if(confirm('정말로 이 게시물을 삭제하시겠습니까?')) {
                fetch('posts?id=' + id, {
                    method: 'DELETE'
                }).then(response => {
                    if(response.ok) {
                        location.reload();
                    } else {
                        alert('게시물 삭제에 실패했습니다.');
                    }
                });
            }
        }
    </script>
</body>
</html>
