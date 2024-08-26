package com.board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	ArrayList<Post> posts = new ArrayList<>();
	int count = 0;

	void run() {
		Scanner sc = new Scanner(System.in);
		xx: while (true) {
			System.out.println("[1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료]");
			String cm = sc.next();
			System.out.println(cm);
			// 유저가 치는 키보드 내용
			switch (cm) {
			// 친 숫자에 따라서 번호 재생
			case "1":
				// 리스트
				System.out.println("==LIST==");
				for (Post p : posts) {
					p.info();
				}
				System.out.println("==== ===== =====");
				break;
			//
			case "2":
				// 글 내용 읽기
				System.out.println(" 글 내용 읽기 ");
				int selectNo = sc.nextInt();
				String a = String.format("게시판 번호: %s", selectNo);
				System.out.println(a);
				for (Post p1 : posts) {
					if (p1.no == selectNo) {
						p1.infoForRead();

					}
				}
				break;
			//
			case "3"://
				// 글 내용 적기
				System.out.println(" 글 쓰기 ");
				System.out.println(" 제목: ");
				String title = sc.next();
				System.out.println(" 내용: ");
				String content = sc.next();
				System.out.println(" 작가: ");
				String writer = sc.next();
				// 각 항목에 글 입력
				count = count + 1;
//글 게시판에 번호 지정
				Post p = new Post(count, title, content, writer);
				posts.add(p);
				// 새로쓴 글 내용들 제목.내용.작가.페이지 번호를 Post 페이지에 전달해서 요소로 등록
				break;
//
			case "4":
				// 글 삭제 하기
				System.out.println("4.삭제");
				System.out.println("삭제 할 페이지 번호를 알려주세요");
				int selectNoForDelete = sc.nextInt();
				String b = String.format("삭제할 페이지 번호: ", selectNoForDelete);
				System.out.println(b);

				int searchIndex = -1;
				// 글 페이지 넘버 입력
				for (int i = 0; i < posts.size(); i++) {
					Post pp = posts.get(i);
					if (pp.no == selectNoForDelete) {
						System.out.println("check" + b);
						searchIndex = i;
						break;
					}
				}
				System.out.println("삭제하시겠습니까?");
				System.out.println("1.참 / 2.아니오");
				String ad = sc.next();
				switch (ad) {
				case "1":
					posts.remove(searchIndex);
					break;
				case "2":
					break;
				}
				break;
			case "5":

				System.out.println("============= 수정 ===============");
				int selectNo5 = sc.nextInt();
				String c = String.format("수정할 내용", selectNo5);
				System.out.println(c);
				for (Post p3 : posts) {
					if (p3.no == selectNo5) {
						String newContents = sc.next();
						p3.content = newContents;
						break;
					}
				}
				break;
			case "e":
				String enswer = sc.next();
				String end = String.format("종료");
				if (enswer.equals(end)) {
					System.out.println("~종료~");
				}
				break xx;
			case "r":
				break;
			default:
				System.out.println("장난 ㄴㄴ");
				break;
			}
		}
		sc.close();
	}
}