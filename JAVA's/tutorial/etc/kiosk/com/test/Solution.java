public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("a,b 입력 : ");
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (a >= 1 && a <= 100 && b >= 1 && b <= 100) {
			int c = a + b;

			System.out.println(a + " + " + b + " = " + c);
			// System.out.print(n + " is "+(n % 2 == 0 ? "even" : "odd"));
		}
	}
}

greenriverdev1028

@gmail.com
추가적인 출력으로
인한 오류 인듯합니다-->System.out.print("a,b 입력 : ");

greenriverdev1028@gmail.com―2024.04.12 22:00
댓글을 입력하세요.1
개의 답변 4 tel 4 tel 2024.2.1616:14 System.out.print("a,b 입력 : ");
// ???????