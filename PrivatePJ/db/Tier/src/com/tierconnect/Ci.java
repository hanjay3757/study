package com.tierconnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ci {
	static Scanner sc = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	static public String r() {
		return sc.next();
	}

	static public String r(String comment) {
		Cw.w(comment + ":");
		return sc.next();
	}

}
