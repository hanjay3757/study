package claculator;
class Solution {
	public String solution(String my_string, int s, int e) {
		String result = "";

		// 결과 문자열에 시작 인덱스 s 이전의 문자열을 추가
		result += my_string.substring(0, s);

		// s부터 e까지의 문자열을 뒤집어서 추가
		for (int i = e; i >= s; i--) {
			result += my_string.charAt(i);
		}

		// 결과 문자열에 끝 인덱스 e 이후의 문자열을 추가
		result += my_string.substring(e + 1);

		return result;
	}
}