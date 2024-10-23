package com.peisia.c.board;

import java.util.Scanner;

public class Board {
	//gc 
	void run() {
		System.out.println("1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료");
		Scanner sc = new Scanner(System.in);
		while(true) {
			String cmd = sc.next();
			System.out.println(cmd);
			switch(cmd) {
			case "1":
				//todo
				//리스트
				System.out.println("1임");
				break;
			case "2":
				//todo
				//읽기
				System.out.println("2임");
				break;
			case "3":
				System.out.println("쓰기임");
				//todo
				//쓰기
				System.out.println("제목:");
				String title = sc.next();
				System.out.println("내용:");
				String con = sc.next();
				System.out.println("글쓴이:");
				String writer = sc.next();
				Post p = new Post(title,con,writer);
				p.info();
				
				
				
				break;
			case "4":
				//todo
				//삭제
				System.out.println("4임");
				break;
			case "5":
				//todo
				//5.수정
				System.out.println("5임");
				break;
			case "e":
				//todo
				//e.종료
				System.out.println("종료임");
				break;
			default:
				break;
			}
			
		}
		
	}
}
