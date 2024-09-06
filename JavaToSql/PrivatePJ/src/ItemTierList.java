import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemTierList {
	// 데이터베이스 연결 정보
	private static final String DB_URL = "jdbc:mysql://localhost:3306/my_cat"; // 데이터베이스 URL
	private static final String DB_USER = "root"; // 데이터베이스 사용자명
	private static final String DB_PASSWORD = "root"; // 데이터베이스 비밀번호

	// 데이터베이스 연결 및 Statement 객체
	private Connection con; // 데이터베이스 연결 객체
	private Statement st; // SQL 문을 실행할 Statement 객체

	public static void main(String[] args) {
		// ItemTierList 클래스의 인스턴스 생성
		ItemTierList itemTierList = new ItemTierList();
		try {
			// 데이터베이스 연결
			itemTierList.connect();

			// 테이블이 없으면 생성
			itemTierList.createTable();

//			// 예시 아이템 추가
//			itemTierList.addItem("Sword of Destiny", "S");
//			itemTierList.addItem("Shield of Valor", "A");
//			itemTierList.addItem("Helmet of Wisdom", "B");
//			itemTierList.addItem("Boots of Speed", "C");
//			itemTierList.addItem("Ring of Strength", "D");

			// 아이템 티어 목록 조회 및 출력
			itemTierList.printItemTierList();
		} catch (SQLException e) {
			// SQL 예외 처리
			System.out.println(e.getMessage());
		} finally {
			// 연결 종료
			itemTierList.disconnect();
		}
	}

	// 데이터베이스 연결 메서드
	public void connect() throws SQLException {
		// 데이터베이스 연결이 열려 있지 않거나 닫힌 경우
		if (con == null || con.isClosed()) {
			// 데이터베이스에 연결
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Statement 객체 생성
			st = con.createStatement();
		}
	}

	// 데이터베이스 연결 종료 메서드
	public void disconnect() {
		try {
			// Statement 객체가 열려 있으면 닫기
			if (st != null && !st.isClosed()) {
				st.close();
			}
			// Connection 객체가 열려 있으면 닫기
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// SQL 예외 처리
			System.out.println("Error closing connection: " + e.getMessage());
		}
	}

	// 테이블 생성 메서드
	public void createTable() throws SQLException {
		// Items 테이블 생성 SQL 쿼리
		String sql = "CREATE TABLE IF NOT EXISTS Items (" + "id INT AUTO_INCREMENT PRIMARY KEY," + // id 열은 자동 증가하는 기본 키
				"name VARCHAR(255) NOT NULL," + // name 열은 255자의 문자열이며 NULL 값을 허용하지 않음
				"tier VARCHAR(10) NOT NULL)"; // tier 열은 10자의 문자열이며 NULL 값을 허용하지 않음
		try (Statement stmt = con.createStatement()) {
			// SQL 쿼리 실행
			stmt.execute(sql);
		}
	}

	// 아이템 추가 메서드
	public void addItem(String name, String tier) throws SQLException {
		// 아이템 추가 SQL 쿼리
		String sql = "INSERT INTO Items(name, tier) VALUES(?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL 쿼리의 ? 자리 표시자를 실제 값으로 설정
			pstmt.setString(1, name); // name 값 설정
			pstmt.setString(2, tier); // tier 값 설정
			// 쿼리 실행
			pstmt.executeUpdate();
		}
	}

	// 아이템 티어 목록 조회 및 출력 메서드
	public void printItemTierList() throws SQLException {
		// 아이템 티어 목록 조회 SQL 쿼리
		String sql = "SELECT name, tier FROM Items";
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			// 결과 출력
			System.out.println("Item Tier List:");
			while (rs.next()) {
				// 결과 집합에서 name과 tier 값을 추출
				String name = rs.getString("name");
				String tier = rs.getString("tier");
				// 콘솔에 출력
				System.out.println(tier + ": " + name);
			}
		}
	}
}
