package com.peisia.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Board {
	// 게시판별 게시물 저장 객체
	private static Map<String, List<Post>> boardPosts;
	private static String currentBoard;
	private static double currentRotateX = 0;
	private static double currentRotateY = 0;

	static {
		boardPosts = new HashMap<>();
		boardPosts.put("index", new ArrayList<>());
		boardPosts.put("dog", new ArrayList<>());
		boardPosts.put("cat", new ArrayList<>());
	}

	public static void setCurrentBoard(HttpServletRequest request) {
		String path = request.getRequestURI();
		currentBoard = path.substring(path.lastIndexOf('/') + 1).replace(".html", "");
		if (currentBoard.isEmpty()) {
			currentBoard = "index";
		}
	}

	public static void handleSubmit(HttpServletRequest request) {
		String title = request.getParameter("post-title");
		String content = request.getParameter("post-content");
		String videoUrl = request.getParameter("post-video");

		String videoId = getYoutubeVideoId(videoUrl);
		String embedUrl = "https://www.youtube.com/embed/" + videoId;

		boolean isDuplicate = boardPosts.values().stream().flatMap(List::stream)
				.anyMatch(post -> post.getTitle().equals(title) || post.getVideoUrl().equals(embedUrl));

		if (isDuplicate) {
			throw new IllegalArgumentException("이미 동일한 제목이나 동영상 URL을 가진 게시물이 존재합니다.");
		}

		Post post = new Post(title, content, embedUrl, System.currentTimeMillis(), currentBoard);
		boardPosts.get(currentBoard).add(post);

		// 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("boardPosts", boardPosts);
	}

	public static void loadPosts(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object savedPosts = session.getAttribute("boardPosts");
		if (savedPosts != null) {
			boardPosts = (Map<String, List<Post>>) savedPosts;
		}
	}

	private static String getYoutubeVideoId(String url) {
		Pattern pattern = Pattern.compile("^.*(youtu.be/|v/|u/\\w/|embed/|watch\\?v=|\\&v=)([^#\\&\\?]*).*");
		Matcher matcher = pattern.matcher(url);
		return matcher.matches() && matcher.group(2).length() == 11 ? matcher.group(2) : null;
	}

	public static void deletePost(int postIndex, String board) {
		boardPosts.get(board).remove(postIndex);
	}

	public static List<Post> getPosts() {
		List<Post> postsToRender = new ArrayList<>();

		if ("admin".equals(currentBoard)) {
			boardPosts.values().forEach(postsToRender::addAll);
		} else {
			postsToRender.addAll(boardPosts.get(currentBoard));
		}

		postsToRender.sort((a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp()));

		return postsToRender;
	}

	@Data
	@AllArgsConstructor
	public static class Post {
		private String title;
		private String content;
		private String videoUrl;
		private long timestamp;
		private String board;
	}
}