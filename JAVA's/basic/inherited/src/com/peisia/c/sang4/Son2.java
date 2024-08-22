package com.peisia.c.sang4;

public class Son2 extends Father {
	// @ 골뱅이 시리즈들을 어노테이션 이라고 함. 이거 안붙여도 오버라이딩 되긴함.
	// 근데 붙이면 함수명을 잘못쓰거나 한걸 에러 감지 해줌.
	@Override	
	void kimchi() {
//		super.kimchi();	//참고로 << 이거 쓰면 부모의 함수 그대로 실행해라 라는 뜻.
		System.out.println("감자 김치");
	}
	
}