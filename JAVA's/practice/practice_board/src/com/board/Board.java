package com.board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	ArrayList<Post> posts = new ArrayList<Post>();
	int count = 0;

	void run() {
		Scanner sc = new Scanner(System.in);
		x: while (true) {
			System.out.println("[1.리스트/ 2.읽기 / 3. 쓰기/ 4. 삭제/ 5. 수정/ e.종료]");
			String cmd = sc.next();
			System.out.println(cmd);
			switch (cmd) {
			case "1":
				System.out.println("======리스트=======");
				for (Post p : posts) {
					p.info();
				}
				break;
			case "2":
				System.out.println("======읽으실 글 번호를 입력해 주세요=======");
				int selectNo = sc.nextInt();
				System.out.println("2번");
				for (Post p : posts) {
					if (p.no == selectNo) {
						p.infoForRead();
					}
				}

				break;
			case "3":
				System.out.println("쓰기");
				System.out.println("제목 : ");
				String title = sc.next();
				System.out.println("내용 : ");
				String content = sc.next();
				System.out.println("글쓴이 : ");
				String writer = sc.next();
				count = count + 1;
				Post p = new Post(count, title, content, writer);
				posts.add(p);
				break;

			case "4":
				System.out.println("삭제할 번호를 입력해 주세용");
				int selectNoForDel = sc.nextInt();
				int searchIndex = -1;
				String s = String.format("바꿀 번호 : %s", selectNoForDel);
				System.out.println(s);
				for (int i = 0; i < posts.size(); i++) {
					Post pp = posts.get(i);
					if (pp.no == selectNoForDel) {
						System.out.println("check");
						searchIndex = i;
						break;
					}
				}
				System.out.println(searchIndex);
				posts.remove(searchIndex);
				break;
			case "5":
				System.out.println("++++++++수정++++++++");
				int selectNo4 = sc.nextInt();
				String s2 = String.format("바꿀 번호 : %s", selectNo4);
				System.out.println(s2);
				for (Post p3 : posts) {
					if (p3.no == selectNo4) {
						System.out.println("변경 내용");
						String newContent = sc.next();
						p3.content = newContent;
						break;
					}

				}
				break;
			case "e":
				System.out.println("종료");
				break x;
			default:
				System.out.println("장난 ㄴㄴ");
				break;
			}

		}
		sc.close();
	}
}
