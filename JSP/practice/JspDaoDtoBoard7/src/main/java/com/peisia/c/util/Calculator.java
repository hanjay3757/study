package com.peisia.c.util;

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("간단한 계산기입니다.");
		System.out.print("첫 번째 숫자를 입력하세요: ");
		double num1 = scanner.nextDouble();

		System.out.print("두 번째 숫자를 입력하세요: ");
		double num2 = scanner.nextDouble();

		System.out.print("연산을 선택하세요 (+, -, *, /): ");
		char operator = scanner.next().charAt(0);

		double result;

		switch (operator) {
		case '+':
			result = num1 + num2;
			System.out.println("결과: " + num1 + " + " + num2 + " = " + result);
			break;
		case '-':
			result = num1 - num2;
			System.out.println("결과: " + num1 + " - " + num2 + " = " + result);
			break;
		case '*':
			result = num1 * num2;
			System.out.println("결과: " + num1 + " * " + num2 + " = " + result);
			break;
		case '/':
			if (num2 != 0) {
				result = num1 / num2;
				System.out.println("결과: " + num1 + " / " + num2 + " = " + result);
			} else {
				System.out.println("오류: 0으로 나눌 수 없습니다.");
			}
			break;
		default:
			System.out.println("유효하지 않은 연산자입니다.");
			break;
		}

		scanner.close();
	}
}
