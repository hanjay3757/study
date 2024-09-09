package com.tierconnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BoardT {
	static Scanner sc = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void run() {
		Db.dbInit();
		System.out.println("DB connect");

	}
}
