package com.practice;

import java.util.ArrayList;

import com.kiosk.util.So;

public class test {
	public static void main(String[] args) {
		// 게임 오브젝트들 로딩

		Character c = new Character("고양이", 3, 100);

		Character x = new Character("고양이", 3, 100);

		GameObj go;

		// 아버지 - 자식
		// 형 변환
		// type 변환
		// Character 클래스가 GameObj 로 바꼈다.
		// >> 다 같은 얘기

		go = (Character) x; // 자동형변환, 묵시적 타입 변환
		// 여기서
		// 괄호 + 클래스명 + 괄호 + 변수
		// 이 명령어가 형변환 명령어임.

		// 1.아버지 양복 훔쳐입어서(형변환) 아버지 객체로 취급이됨.

		// 2.아버지로 변신했기 때문에 자기 자신의 행동을 못함
//		System.out.println(go.hp);

		// 3.자기꺼를 하려면 다시 변신 풀어야함.

//		Character cc = go;	//에러
		Character cc = (Character) go; // 이렇게 자기 형으로 형변환해서 돌아가야됨

		System.out.println(cc.hp); // 그래서 돌아가고 난 다음엔 자기꺼 쓸 수 있게됨.

		Sword s = new Sword("단검", 2, 100, 50, 70);
		Sword l = new Sword("장검", 3, 150, 100, 70);

		// 할아버지형 리스트에 손자,아들,본인 다 넣을수 있음. (상속의 중요 특성)
		ArrayList<GameObj> gs = new ArrayList<GameObj>();
		gs.add(c); // 이때에도 자동 형변환이 일어남.
		gs.add(s); // 이때에도 자동 형변환이 일어남.
		gs.add(l); // 이때에도 자동 형변환이 일어남.
		for (GameObj o : gs) {
			So.ln(o.name);
		}
	}

}
