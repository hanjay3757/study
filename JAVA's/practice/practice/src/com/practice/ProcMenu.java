package com.practice;

import java.util.Scanner;

import com.c.board.display.Disp;
import com.util.Ci;
import com.util.Cw;

public class ProcMenu {

	static Scanner a = new Scanner(System.in);

	static void run() {
		Disp.menuMain();
		loop: while (true) {
			String cmd = Ci.r("명령");
			switch (cmd) {
			case "1":
				ProcMenuList.run();
				break;
			case "2":
				ProcMenuRead.run();
				break;
			case "3":
				ProcMenuWrite.run();
				break;
			case "4":
				ProcMenuDel.run();
				break;
			case "5":
				ProcMenuUpdate.run();
				break;
			case "e":
				System.out.println("종료하시겠습니까?");
				String input = a.nextLine();
				System.out.println("입력한 문자열: " + input);
				System.out.println("프로그램 종료");
				break loop;
			default:
				Cw.wn("장난질 out");
				break;
			}
		}
	}
}
