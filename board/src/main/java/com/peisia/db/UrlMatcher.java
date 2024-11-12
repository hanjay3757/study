package com.peisia.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMatcher {
	// YouTube 동영상 ID 추출
	public static String extractYoutubeId(String url) {
		String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	// URL 유효성 검사
	public static boolean isValidUrl(String url) {
		String pattern = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$";
		return Pattern.matches(pattern, url);
	}
}
