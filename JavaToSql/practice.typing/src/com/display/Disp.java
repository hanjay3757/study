package com.display;

import com.util.Cw;

public class Disp {
	static private String SITE_NAME = "MovieTalk";
	static private String VERSION = " v0.0.0";
	static private String FEAT = " sm.JWH";

	static public void entranceTitle() {
		Cw.line();
		Cw.dot();
		Cw.space(22);
		Cw.w(SITE_NAME);
		Cw.w(VERSION);
		Cw.w(FEAT);
		Cw.space(22);
		Cw.dot();
		Cw.wn();
		Cw.line();
	}
}
