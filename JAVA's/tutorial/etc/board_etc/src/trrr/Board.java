package trrr;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	// gc

	ArrayList<Post> posts = new ArrayList<Post>();

	int count = 0;

	void run() {

		Scanner sc = new Scanner(System.in);
		x: while (true) {
			System.out.println("1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료");
			String cmd = sc.next();
			System.out.println(cmd);
			switch (cmd) {
			case "1": // 리스트
				System.out.println("==== 리스트 ====");
				for (Post p : posts) {
					p.info();
				}
				System.out.println("==== ==== ====");
				break;
			case "2": // 읽기
				System.out.println("읽으실 글 번호를 입력해주세요:");
				int selectNo = sc.nextInt();
				System.out.println("2임");
				for (Post p : posts) {
					if (p.no == selectNo) {
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
				count = count + 1; // 페이지 수 증가
				Post p = new Post(count, title, con, writer);
				posts.add(p);
				break;
			case "4":
				// 삭제
				System.out.println("4임");
				System.out.println("삭제할 글 번호를 입력해주세요:");
				int selectNoForDel = sc.nextInt();
//				posts.remove(selectNoForDel - 1);
//				posts.remove(0);
//				int searchIndex = 0;
//				for (Post p2 : posts) {
//					if (p2.no == selectNoForDel) {
////					posts.remove(selectNoForDel - 1);
//						break;
//3					}
//					searchIndex = searchIndex + 1;
//
//				}
//				posts.remove(searchIndex);
				int searchIndex = -1;
				for (int i = 0; i < posts.size(); i++) {
					Post pp = posts.get(i);
					if (pp.no == selectNoForDel) {
						System.out.println("check");
						searchIndex = i;
						break;
					}
				}
				posts.remove(searchIndex);
				break;

			case "5":

				// 5.수정
				System.out.println("=========수정===========");
				int selectNo3 = sc.nextInt();
				for (Post p3 : posts) {
					if (p3.no == selectNo3) {
						System.out.println("바꿀내용");
						String newContent = sc.next();
						p3.content = newContent;
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
//package com.peisia.c.board;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Board {
//	//gc 
//	
//	ArrayList<Post> posts = new ArrayList<Post>();
//	
//	int count = 0;
//	
//	void run() {
//		
//		Scanner sc = new Scanner(System.in);
//		x:
//		while(true) {
//			System.out.println("1.리스트 2.읽기 3.쓰기 4.삭제 5.수정 e.종료");
//			String cmd = sc.next();
//			System.out.println(cmd);
//			switch(cmd) {
//			case "1":	//리스트
//				System.out.println("==== 리스트 ====");
//				for(Post p:posts) {
//					p.info();
//				}
//				System.out.println("==== ==== ====");
//				break;
//			case "2":	//읽기
//				System.out.println("읽으실 글 번호를 입력해주세요:");
//				int selectNo = sc.nextInt();
//				System.out.println("2임");
//				for(Post p:posts) {
//					if(p.no == selectNo) {
//						p.infoForRead();
//					}
//				}
//				break;
//			case "3":	//쓰기
//				System.out.println("쓰기임");
//				System.out.println("제목:");
//				String title = sc.next();
//				System.out.println("내용:");
//				String con = sc.next();
//				System.out.println("글쓴이:");
//				String writer = sc.next();
//				count = count + 1;
//				Post p = new Post(count,title,con,writer);
//				posts.add(p);
//				break;
//			case "4":
//				//삭제
//				System.out.println("4임");
//				System.out.println("삭제하실 글 번호를 입력해주세요:");
//				int selectNoForDel = sc.nextInt();
//				System.out.println("2임");
//				
//				
////				posts.remove(0);
////				posts.remove(selectNoForDel-1);
//				
////				int searchIndex = 0;
////				for(Post p2:posts) {
////					if(p2.no == selectNoForDel) {
////						
////						break ;
////					}
////					searchIndex = searchIndex + 1;
////				}	
////				
////				posts.remove(searchIndex);
//				
//				int searchIndex = 0;
//				for(int i=0;i<posts.size();i=i+1) {
//					Post pp = posts.get(i);
//					
//					if(pp.no == selectNoForDel) {
//						System.out.println("찾았다");
//						searchIndex = i;
//						break;
//					}
//				}
//				posts.remove(searchIndex);
//				
//				
//				break;
//			case "5":
//				//todo
//				//5.수정
//				System.out.println("5임");
//				break;
//			case "e":	//e.종료
//				System.out.println("종료임");
//				break x;
//			default:
//				break;
//			}
//			
//		}
//		
//	}
//}
