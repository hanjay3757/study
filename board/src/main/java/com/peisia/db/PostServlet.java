package com.peisia.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/my_cat";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String videoUrl = request.getParameter("videoUrl");
		String board = request.getParameter("board");

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "INSERT INTO posts (title, content, videoUrl, board, timestamp) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setString(3, videoUrl);
				ps.setString(4, board);
				ps.setLong(5, System.currentTimeMillis());

				int resultCount = ps.executeUpdate();
				response.setStatus(
						resultCount > 0 ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String board = request.getParameter("board");
		response.setContentType("application/json");

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement("SELECT * FROM posts WHERE board = ?")) {
			ps.setString(1, board);
			ResultSet rs = ps.executeQuery();

			StringBuilder jsonBuilder = new StringBuilder("[");
			while (rs.next()) {
				jsonBuilder.append("{").append("\"id\":").append(rs.getInt("id")).append(",").append("\"title\":\"")
						.append(rs.getString("title")).append("\",").append("\"content\":\"")
						.append(rs.getString("content")).append("\",").append("\"videoUrl\":\"")
						.append(rs.getString("videoUrl")).append("\",").append("\"board\":\"")
						.append(rs.getString("board")).append("\",").append("\"timestamp\":")
						.append(rs.getLong("timestamp")).append("},");
			}
			if (jsonBuilder.length() > 1)
				jsonBuilder.setLength(jsonBuilder.length() - 1); // 마지막 쉼표 제거
			jsonBuilder.append("]");
			PrintWriter out = response.getWriter();
			out.print(jsonBuilder.toString());
			out.flush();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("id"));

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement("DELETE FROM posts WHERE id = ?")) {
			ps.setInt(1, postId);
			int resultCount = ps.executeUpdate();
			response.setStatus(resultCount > 0 ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
