package claculator;

public class Main {

	public static void main(String[] args) {
		// xy 값 설정
		int xy = 36; // 예를 들어 xy를 36으로 설정합니다.

		// a와 b의 값을 찾습니다.
		findValues(xy);
	}

	// a(a - b) = xy 를 만족하는 a와 b의 값을 찾는 함수
	public static void findValues(int xy) {
		// a를 1부터 시작해서 충분히 큰 값까지 반복합니다.
		for (int a = 1; a <= 100; a++) { // a의 범위를 조정할 수 있습니다.
			// a(a - b) = xy 를 만족하는 b를 계산합니다.
			int numerator = xy + a * a; // a^2 + xy
			if (numerator % a == 0) { // b가 정수인지 확인
				int b = numerator / a - a; // b = (a^2 + xy) / a - a
				if (b >= 0) { // b가 음수일 경우는 제외
					System.out.println("a: " + a);
					System.out.println("b: " + b);
					System.out.println("a(a - b) = " + xy);
					System.out.println(); // 결과를 구분하기 위한 빈 줄
				}
			}
		}
	}
}