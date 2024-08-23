package com.rpc03;

public class Main {
	public static void main(String[] args) {
		int a = 2147483647;
		long b = a;
//		long b = 2147483647;
//		int a = b; 큰걸 작은걸로 할꺼면 자동변환 x // a=(int)b; 하면 꾸겨 넣어지는 개념이라 부숴짐

		System.out.println(b);
	}

}
