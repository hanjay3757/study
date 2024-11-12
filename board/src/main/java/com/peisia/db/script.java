package com.peisia.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class script {
	// 게시판별 게시물 저장 객체
	private static Map<String, List<Posts>> boardPosts = new HashMap<>();

	// 게시물 추가
	public static void addPost(String board, Posts post) {
		if (!boardPosts.containsKey(board)) {
			boardPosts.put(board, new ArrayList<>());
		}
		boardPosts.get(board).add(post);
	}

	// 게시물 삭제
	public static void deletePost(String board, int index) {
		if (boardPosts.containsKey(board)) {
			boardPosts.get(board).remove(index);
		}
	}

	// 게시물 조회
	public static List<Posts> getPosts(String board) {
		return boardPosts.getOrDefault(board, new ArrayList<>());
	}

	// YouTube 동영상 ID 추출
	public static String getYoutubeVideoId(String url) {
		String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	// 게시물 중복 체크
	public static boolean isDuplicatePost(String title, String videoUrl) {
		return boardPosts.values().stream().flatMap(List::stream)
				.anyMatch(post -> post.getTitle().equals(title) || post.getVideoUrl().equals(videoUrl));
	}
}
