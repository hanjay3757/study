package delete;

public class Disp {
	String x = "x";

	final static String DOT = "*";
	final static int DOT_COUNT = 48;

	public static void line() {
		for (int i = 0; i < DOT_COUNT; i++) {
			Cw.w(DOT);
		}
		CW.wn();
	}

	public static void title() {
		line();
		dot(10);
		Cw.w("고양이 카페 (v." + Kiosk.VERSION + "by sm.jwh)");
		dot(10);
		Cw.wn();
		line();
	}

	public static void dot(int n) {
		for (int i = 0; i < n; i++) {
			Cw.w(DOT);
		}
	}
}
