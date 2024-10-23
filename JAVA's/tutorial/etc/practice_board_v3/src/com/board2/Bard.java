package com.board2;

import java.util.ArrayList;
import java.util.Scanner;

public class Bard {
	ArrayList<Post> posts = new ArrayList<>();
	int count = 0;

	void run() {
		System.out.println("암기 테스트");
		Scanner cs = new Scanner(System.in);
		xx: while (true) {
			System.out.println("1.List / 2.Read / 3.Write / 4.Delete / e.Exit");
			String cmd = cs.next();
			switch (cmd) {
			case "1":
				System.out.println("1.List");
				for (Post p : posts) {
					p.toString();
				}
				break;
			case "2":
				System.out.println("2.Read");
				int no = cs.nextInt();
				for (Post p1 : posts) {
					if (p1.getNo() == no) {
						p1.infoForRead();
					}

				}
				break;
			case "3":
				System.out.println("title");
				String title = cs.next();
				System.out.println("content");
				String content = cs.next();
				System.out.println("writer");
				String writer = cs.next();
				System.out.println("작성시간 : ");
				String writeTime = new java.text.SimpleDateFormat("yyy년 mm월 hh시 mm분 ss초").format(new java.util.Date());
				count = count + 1;
				Post par = new Post(count, title, content, writer, writeTime);
				posts.add(par);
				break;
			case "4":
				int Del = cs.nextInt();
				int Cc = -1;
				for (int i = 0; i < posts.size(); i++) {
					Post test = posts.get(i);
					if (test.getNo() == Del) {
						Cc = i;
						break;
					}
				}
				posts.remove(Cc);
				break;
			case "e":
				break xx;
			}
		}
		cs.close();
	}
}
