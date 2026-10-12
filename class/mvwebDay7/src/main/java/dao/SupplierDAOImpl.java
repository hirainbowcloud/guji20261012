package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements supplierDAO {
	

	private suppliers mapSupplier(ResultSet resultSet) throws SQLException {
		return new suppliers(
			resultSet.getInt("SUP_ID"),
			resultSet.getString("SUP_NAME"),
			resultSet.getString("STREET"),
			resultSet.getString("CITY"),
			resultSet.getString("STATE"),
			resultSet.getString("ZIP")
		);
	}

	@Override
	public boolean insertSupplier(suppliers supplier) throws SQLException {
		String sql = "INSERT INTO suppliers (SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, supplier.getSupId());
			statement.setString(2, supplier.getSupName());
			statement.setString(3, supplier.getStreet());
			statement.setString(4, supplier.getCity());
			statement.setString(5, supplier.getState());
			statement.setString(6, supplier.getZip());
			return statement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean deleteSupplier(int supId) throws SQLException {
		String sql = "DELETE FROM suppliers WHERE SUP_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, supId);
			return statement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean updateSupplier(suppliers supplier) throws SQLException {
		String sql = "UPDATE suppliers SET SUP_NAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ? WHERE SUP_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, supplier.getSupName());
			statement.setString(2, supplier.getStreet());
			statement.setString(3, supplier.getCity());
			statement.setString(4, supplier.getState());
			statement.setString(5, supplier.getZip());
			statement.setInt(6, supplier.getSupId());
			return statement.executeUpdate() > 0;
		}
	}
	
	/*ResultSet 是什麼？
	ResultSet 是 JDBC 執行 SELECT 查詢後回傳的資料表格（結果集）。

	resultSet.next() 在做什麼？
	next() 負責兩件事：
	往下移到下一列（Row）。
	回傳布林值 (true / false)：

	因為你是用主鍵查詢（WHERE SUP_ID = ?），結果最多只會有一筆或零筆：
	第一次呼叫 resultSet.next() 時，如果有找到該 ID 的資料，會移到第一筆，並回傳 true。
	如果找不到該 ID，直接回傳 false。
	*/

	@Override
	public suppliers getSupplierById(int supId) throws SQLException {
		String sql = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM suppliers WHERE SUP_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, supId);
			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next() ? mapSupplier(resultSet) : null;
			}
		}
	}
	
	/*
	 * connection.prepareStatement(sql)：先把帶有問號 ?（佔位符）的 SQL 模板送到資料庫進行語法解析與預先編譯。
	 * statement.setInt(1, supId)：再把具體的變數值（supId）填入第 1 個 ? 裡。
	 * statement.executeQuery()：資料庫直接帶入參數執行，不需要再傳入 sql 字串。
	 * */
	
	/*
	 * connection.createStatement()：只建立一個空白的執行通道，資料庫此時完全不知道你要跑什麼 
	 * SQL。statement.executeQuery(sql)：把完整寫死的 SQL 字串傳進去，資料庫收到後當場進行「解析語法編譯 執行」。
	 * 
	 * */
	

	@Override
	public List<suppliers> getAllSuppliers() throws SQLException {
		String sql = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM suppliers ORDER BY SUP_ID";
		List<suppliers> list = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
			 Statement statement = connection.createStatement(); //沒有參數時
			 ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				list.add(mapSupplier(resultSet));
			}
		}
		return list;
	}
	
}
