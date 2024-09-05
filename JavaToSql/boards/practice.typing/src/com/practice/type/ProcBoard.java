package com.practice.type;

import java.sql.SQLException;

import com.display.Disp;
import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcBoard {
	void run() {
		Disp.showTitle();
		Db.dbInit();
		Cw.wn("전체 글 수: " + Db.getPostCount());
		loop: while (true) {
			Db.dbPostCount();
			Disp.showMainMenu();
			String cmd = Ci.r("명령 입력:");
			switch (cmd) {
			case "1":
				ProcList.run();
				break;
			case "2":
				String readNo = Ci.r("Plz enter the Reading No");
				try {
					Db.result = Db.st.executeQuery("select * from board where b_no =" + readNo);
					Db.result.next(); // 결과 값에서 하나씩 빼기 더이상 없을 경우 false로 리턴
					String title = Db.result.getString("b_title");
					String content = Db.result.getString("b_text");
					Cw.wn(title);
					Cw.wn(content);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
