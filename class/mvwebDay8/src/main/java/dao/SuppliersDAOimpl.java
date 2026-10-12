package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDAOimpl implements SuppliersDAO {
	
	private Suppliers mapSupp(ResultSet resultSet) throws SQLException {
		return new Suppliers(
				resultSet.getInt("SUP_ID"), // 主鍵
		        resultSet.getString("SUP_NAME"),
		        resultSet.getString("STREET"),
		        resultSet.getString("CITY"),
		        resultSet.getString("STATE"),
		        resultSet.getString("ZIP")
		);
	}
	@Override
	public boolean insertSuppliers(Suppliers suppliers) {
		String sql = "INSERT INTO suppliers (SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) VALUES (?, ?, ?, ?, ?, ?)";
		 try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {
			 	statement.setInt(1, suppliers.getSupId());
		        statement.setString(2, suppliers.getSupName());
		        statement.setString(3, suppliers.getStreet());
		        statement.setString(4, suppliers.getCity());
		        statement.setString(5, suppliers.getState());
		        statement.setString(6, suppliers.getZip());		        		        
		        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
		        return statement.executeUpdate() > 0;
		        
		    } catch (SQLException e) {
		        throw new RuntimeException("新增供應商資料失敗", e);
		    }
	}

	@Override
	public boolean updateSuppliers(Suppliers suppliers) {	   
	    String sql = "UPDATE suppliers SET SUP_NAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ? WHERE SUP_ID = ?";
	    
	    // 使用 try-with-resources 自動關閉連線與資源
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {	        
	        statement.setString(1, suppliers.getSupName());
	        statement.setString(2, suppliers.getStreet());
	        statement.setString(3, suppliers.getCity());
	        statement.setString(4, suppliers.getState());
	        statement.setString(5, suppliers.getZip());
	        statement.setInt(6, suppliers.getSupId());	        
	        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
	        return statement.executeUpdate() > 0;	        
	    } catch (SQLException e) {
	        throw new RuntimeException("更新供應商資料失敗", e);
	    }
	}

	@Override
	public boolean delSuppliers(int supId) {
		 String sql = "DELETE FROM suppliers WHERE SUP_ID = ?";
		 try(Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)){
			 statement.setInt(1, supId);
			 return statement.executeUpdate() > 0;
		 }catch(SQLException e) {
			 throw new RuntimeException("刪除供應商資料失敗", e);
		 }
	}

	@Override
	public List<Suppliers> findAll() {
		List<Suppliers> list = new ArrayList<>();
		String sql = "SELECT SUP_ID,SUP_NAME,STREET,CITY,STATE,ZIP FROM suppliers";
		try (Connection connection = DatabaseConnection.getConnection();
			 Statement statement = connection.createStatement(); //沒有參數時
			 ResultSet resultSet = statement.executeQuery(sql)){		        
		      while (resultSet.next()) {
		            list.add(mapSupp(resultSet));
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("查詢供應商清單失敗", e);
		    }		    
		    return list;		
	}

	@Override
	public Suppliers findBySupId(int supId) {
	    String sql = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM suppliers WHERE SUP_ID = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {	        
	        statement.setInt(1, supId);	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            return resultSet.next() ? mapSupp(resultSet) : null;
	        }
	        
	    } catch (SQLException e) {
	        throw new RuntimeException("依編號查詢供應商失敗: " + supId, e);
	    }
	}

}
