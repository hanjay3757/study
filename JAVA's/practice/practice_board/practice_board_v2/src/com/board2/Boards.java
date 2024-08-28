package com.board2;

import java.util.ArrayList;
import java.util.Scanner;

public class Boards {
	ArrayList<Post>posts = new ArrayListM\<>();
	int count = 0;

	void run() {
		System.out.println("간단 게시판 v0.0.7");
		Scanner sc = new Scanner(System.in);
		xx: while (true) {
			System.out.println("1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료");
			String cmd = sc.next();
			switch (cmd) {
			case "1":
				for (Post p : posts) {
					p.toString();
				}
				break;
			case "2":
				int selectNo = sc.nextInt();
				for (Post p : posts) {
					if (p.getNo() == selectNo) {
						p.infoForRead();
					}
				}
				break;
			case "3":
				System.out.println("title");
				String title = sc.next();
				System.out.println("content");
				String content = sc.next();
				System.out.println("writer");
				String writer = sc.next();
				count = count + 1;
				Post par = new Post(count, title, content, writer);
				posts.add(par);
				break;

			case "4":
				int Del = sc.nextInt();
				int Index = 0;
				for (int i = 0; i < posts.size(); i++) {
					Post ar = posts.get(i);
					if (ar.getNo() == Del) {
						Index = i;
						break;

					}
				}

			}
		}
	}
}
