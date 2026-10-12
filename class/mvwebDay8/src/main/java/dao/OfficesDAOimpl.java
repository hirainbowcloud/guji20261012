package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfficesDAOimpl implements OfficesDAO {

	// 注意：offices 表欄名為駝峰式（officeCode, addressLine1, postalCode...）
	private Offices mapOff(ResultSet resultSet) throws SQLException {
		return new Offices(
				resultSet.getString("officeCode"), // 主鍵
		        resultSet.getString("city"),
		        resultSet.getString("phone"),
		        resultSet.getString("addressLine1"),
		        resultSet.getString("addressLine2"),
		        resultSet.getString("state"),
		        resultSet.getString("country"),
		        resultSet.getString("postalCode"),
		        resultSet.getString("territory")
		);
	}
	@Override
	public boolean insertOffices(Offices offices) {
		String sql = "INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {
			 	statement.setString(1, offices.getOfficeCode());
		        statement.setString(2, offices.getCity());
		        statement.setString(3, offices.getPhone());
		        statement.setString(4, offices.getAddressLine1());
		        statement.setString(5, offices.getAddressLine2());
		        statement.setString(6, offices.getState());
		        statement.setString(7, offices.getCountry());
		        statement.setString(8, offices.getPostalCode());
		        statement.setString(9, offices.getTerritory());
		        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
		        return statement.executeUpdate() > 0;

		    } catch (SQLException e) {
		        throw new RuntimeException("新增辦公室資料失敗", e);
		    }
	}

	@Override
	public boolean updateOffices(Offices offices) {
	    String sql = "UPDATE offices SET city = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?";

	    // 使用 try-with-resources 自動關閉連線與資源
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, offices.getCity());
	        statement.setString(2, offices.getPhone());
	        statement.setString(3, offices.getAddressLine1());
	        statement.setString(4, offices.getAddressLine2());
	        statement.setString(5, offices.getState());
	        statement.setString(6, offices.getCountry());
	        statement.setString(7, offices.getPostalCode());
	        statement.setString(8, offices.getTerritory());
	        statement.setString(9, offices.getOfficeCode());
	        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
	        return statement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        throw new RuntimeException("更新辦公室資料失敗", e);
	    }
	}

	@Override
	public boolean delOffices(String officeCode) {
		 String sql = "DELETE FROM offices WHERE officeCode = ?";
		 try(Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)){
			 statement.setString(1, officeCode);
			 return statement.executeUpdate() > 0;
		 }catch(SQLException e) {
			 throw new RuntimeException("刪除辦公室資料失敗", e);
		 }
	}

	@Override
	public List<Offices> findAll() {
		List<Offices> list = new ArrayList<>();
		String sql = "SELECT officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory FROM offices";
		try (Connection connection = DatabaseConnection.getConnection();
			 Statement statement = connection.createStatement(); //沒有參數時
			 ResultSet resultSet = statement.executeQuery(sql)){
		      while (resultSet.next()) {
		            list.add(mapOff(resultSet));
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("查詢辦公室清單失敗", e);
		    }
		    return list;
	}

	@Override
	public Offices findByOfficeCode(String officeCode) {
	    String sql = "SELECT officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory FROM offices WHERE officeCode = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, officeCode);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            return resultSet.next() ? mapOff(resultSet) : null;
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("依編號查詢辦公室失敗: " + officeCode, e);
	    }
	}

}
