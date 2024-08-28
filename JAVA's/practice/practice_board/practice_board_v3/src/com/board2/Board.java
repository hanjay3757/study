package com.board2;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	//// 외부에서 접근하는 변수들이 아니므로 (FM은 특별한 경우 제외하고는 다 멤버변수 private) private 처리
	private ArrayList<Post> posts = new ArrayList<Post>();
	private int count = 0;

	//// 같은 패키지내의 클래스 Main에서 호출되므로 default 여도 충분하므로 그대로 둠
	void run() {
		System.out.println("간단 게시판 v0.0.8");
		Scanner sc = new Scanner(System.in);
		x: while (true) {
			System.out.println("1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료");
			String cmd = sc.next();
			System.out.println(cmd);
			switch (cmd) {
			case "1": // 리스트
				System.out.println("==== 리스트 ====");
				for (Post p : posts) {

//					p.info();
					System.out.println(p);
				}
				System.out.println("==== ==== ====");
				break;
			case "2": // 읽기
				System.out.println("읽으실 글 번호를 입력해주세요:");
				int selectNo = sc.nextInt();
				System.out.println("2임");
				for (Post p : posts) {
					if (p.getNo() == selectNo) {
						p.infoForRead();
					}
				}
				break;
			case "3": // 쓰기
				System.out.println("쓰기임");
				System.out.println("제목:");
				String title = sc.next();
				System.out.println("내용:");
				String con = sc.next();
				System.out.println("글쓴이:");
				String writer = sc.next();
				count = count + 1;

				Post p = new Post(count, title, con, writer);
				posts.add(p);

				break;
			case "4": // 삭제
				System.out.println("4임");
				System.out.println("삭제하실 글 번호를 입력해주세요:");
				int selectNoForDel = sc.nextInt();
				System.out.println("2임");
				int searchIndex = 0;
				for (int i = 0; i < posts.size(); i = i + 1) {
					Post pp = posts.get(i);

					if (pp.getNo() == selectNoForDel) {
						System.out.println("찾았다");
						searchIndex = i;
						break;
					}
				}
				posts.remove(searchIndex);
				break;
			case "5":
				// 5.수정
				System.out.println("==== 수정 ====");
				System.out.println("수정하실 글 번호를 입력해주세요:");
				int selectNo3 = sc.nextInt();
				for (Post p3 : posts) {
					if (p3.getNo() == selectNo3) {
						System.out.println("=================");
						System.out.println(p3.getContent());
						System.out.println("=================");
						System.out.println("바꿀 내용입력하세요");
						String newContent = sc.next();
//						p3.content = newContent;
						p3.setContent(newContent);
						break;
					}
				}
				break;
			case "e": // e.종료
				System.out.println("종료임");
				break x;
			default:
				break;
			}
		}
	}
}