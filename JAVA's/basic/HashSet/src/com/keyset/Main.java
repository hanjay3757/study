package com.keyset;

import java.util.HashMap;
import java.util.Map;

public class Main {
	// HashMap 생성 및 요소 추가
	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("Apple", 40);
		map.put("Banana", 10);
		map.put("Cherry", 30);

		// entrySet()을 사용하여 모든 키-값 쌍을 순회하고 출력
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + " = " + value);
		}
	}
}