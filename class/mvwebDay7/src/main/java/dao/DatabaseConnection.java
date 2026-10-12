package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	private DatabaseConnection() {}

	/*
	 * Class.forName("com.mysql.cj.jdbc.Driver"); 的意思是：依照類別名稱，載入 MySQL 的 JDBC 驅動程式。
	*/
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("找不到 MySQL JDBC 驅動程式", e);
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
