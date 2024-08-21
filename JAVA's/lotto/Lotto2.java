import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lotto2 {
	public static void main(String[] args) {
		// 번호의 범위
		int min = 1;
		int max = 45;
		// 추첨할 번호 개수
		int count = 6;

		// 중복되지 않는 번호를 저장할 Set
		Set<Integer> uniqueNumbers = new HashSet<>();
		Random random = new Random();

		// 중복되지 않는 번호 생성
		while (uniqueNumbers.size() < count) {
			int number = random.nextInt(max - min + 1) + min;
			uniqueNumbers.add(number);
		}

		// 배열로 변환
		Integer[] r = uniqueNumbers.toArray(new Integer[0]);

		// 번호 출력
		System.out.println();
		System.out.print("추첨번호: ");
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
	}
}
