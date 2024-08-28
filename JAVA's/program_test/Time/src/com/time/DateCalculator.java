package com.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateCalculator {
	public static void main(String[] args) {
		// 날짜 형식을 정의합니다. yyyy-MM-dd 형식으로 입력받을 것입니다.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Scanner scanner = new Scanner(System.in);

		LocalDate date = null;

		// 유효한 날짜가 입력될 때까지 반복합니다.
		while (date == null) {
			System.out.print("날짜를 입력하세요 (yyyy-MM-dd): ");
			String dateInput = scanner.nextLine();
			try {
				// 사용자가 입력한 문자열을 LocalDate 객체로 변환합니다.
				// 예를 들어, "2024-08-28"을 LocalDate로 변환합니다.
				date = LocalDate.parse(dateInput, formatter);
			} catch (DateTimeParseException e) {
				// 입력된 날짜가 형식에 맞지 않거나 유효하지 않은 경우 예외가 발생합니다.
				// 이 경우, 올바른 형식으로 다시 입력하도록 요청합니다.
				System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 입력해 주세요.");
			}
		}

		// 일수를 추가할지 빼기를 선택합니다.
		System.out.print("일수를 더할까요 (더하기: + / 빼기: -)? ");
		String operation = scanner.nextLine();

		// 일수 입력을 받으며 정수로 변환합니다.
		int days = 0;
		while (true) {
			System.out.print("일수를 입력하세요: ");
			try {
				// 사용자가 입력한 문자열을 정수로 변환합니다.
				// 예를 들어, "10"을 10으로 변환합니다.
				days = Integer.parseInt(scanner.nextLine());
				break; // 올바른 정수가 입력되면 루프를 종료합니다.
			} catch (NumberFormatException e) {
				// 입력된 값이 정수가 아닌 경우 예외가 발생합니다.
				// 이 경우, 올바른 정수를 다시 입력하도록 요청합니다.
				System.out.println("올바른 정수를 입력해 주세요.");
			}
		}

		// 날짜에 일수를 더하거나 빼는 작업을 수행합니다.
		LocalDate resultDate = date;
		if (operation.equals("+")) {
			// 연산자가 "+"인 경우, 입력된 일수를 현재 날짜에 추가합니다.
			// 예를 들어, "2024-08-28"에 10일을 추가하면 "2024-09-07"이 됩니다.
			resultDate = date.plusDays(days);
		} else if (operation.equals("-")) {
			// 연산자가 "-"인 경우, 입력된 일수를 현재 날짜에서 빼습니다.
			// 예를 들어, "2024-08-28"에서 10일을 빼면 "2024-08-18"이 됩니다.
			resultDate = date.minusDays(days);
		} else {
			// 잘못된 연산자가 입력된 경우 프로그램을 종료합니다.
			System.out.println("잘못된 연산자입니다. 프로그램을 종료합니다.");
			scanner.close(); // Scanner 객체를 닫아 리소스를 정리합니다.
			return; // 프로그램을 종료합니다.
		}

		// 최종적으로 계산된 날짜를 출력합니다.
		System.out.println("계산된 날짜: " + resultDate);

		// Scanner 객체를 닫아 리소스를 정리합니다.
		scanner.close();
	}
}
