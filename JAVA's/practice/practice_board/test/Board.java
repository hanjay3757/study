package com.board.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.board.Post;

public class Board {
	ArrayList<Posts> posts = new ArrayList<>();
	int count = 0;

	void run() {
		Scanner ac = new Scanner(System.in);
		xx: while (true) {
			System.out.println("[1. List/ 2. Read/ 3. Write/4. Delete/5. Edit/e. Exit]");
			String cmd = ac.next();
			switch (cmd) {

			case "1":
				System.out.println("===========LIST============");
				for (Posts p : posts) {
					p.info();
				}
				System.out.println("============================");
				break;
			case "2":
				System.out.println("[Page reading]");
				int selectNo = ac.nextInt();
				String a = String.format("READING : %s", selectNo);
				System.out.println(a);
				for (Posts q : posts) {
					if (q.no == selectNo) {
						q.infoForRead();
					}
				}
				break;
			case "3":
				System.out.println("=====Writing======");
				System.out.println("++++++title+++++++");
				String title = ac.next();
				System.out.println("+=+=+=+=Content+=+=+=+");
				String content = ac.next();
				System.out.println("-+-+-+Writer+-+-+-");
				String writer = ac.next();
				count = count + 1;
				Post px = new Post(count, title, content, writer);
				posts.add(px);
				break;
			//
			case "4":
				System.out.println("``````DELETE```````");
				System.out.println("choose Delete No.");
				int selectNoDelete = ac.nextInt();
				String bs = String.format("Page No. %s", selectNoDelete);
				System.out.println(bs);
				int searchIndex = -1;
				for (int i = 0; i < posts.size(); i++) {
					Posts pp = posts.get(i);
					if (pp.no == selectNoDelete) {
						searchIndex = i;
						break;
					}
				}
				System.out.println("Are you sure you want to delete?");
				System.out.println("1.True/2.False");
				String ad = ac.next();
				switch (ad) {
				case "1":
					posts.remove(searchIndex);
					break;
				case "2":
					break;
				}
				break;
			// todo edit error
			case "5":
				System.out.println("VVVVVEDITVVVVVVV");
				int select5 = ac.nextInt();
				String zc = String.format("Edit.no.%s ", select5);
				System.out.println(zc);
				for (Posts p3 : posts) {
					if (p3.no == select5) {
						String newContents = ac.next();
						p3.content = newContents;
						break;
					}
				}
				break;
			case "e":
				System.out.println("EXIT");
				break xx;
			}
		}
		ac.close();
	}
}
