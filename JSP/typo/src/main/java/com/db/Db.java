package com.db;

public class Db {
	static public String DB_JDBC_DRIVER_PACKAGE_PATH = "com.mysql.cj.jdbc.Driver";

	static private String DB_NAME = "my_cat";
	static private String DB_URL_MYSQL = "jdbc:mysql://localhost:3306" + DB_NAME;
	static public String DB_URL = DB_URL_MYSQL;
	static public String DB_ID = "root";
	static public String DB_PW = "root";
	public static final String TABLE_PS_BOARD_FREE = "PS_BOARD_FREE";
}
