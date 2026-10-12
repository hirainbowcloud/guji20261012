package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoffeesDAOimpl implements CoffeesDAO {
	
	private Coffees mapCoff(ResultSet resultSet) throws SQLException {
		return new Coffees(
				resultSet.getString("COF_NAME"), // 改為大寫加底線，和資料庫一樣
		        resultSet.getInt("SUP_ID"),       // 改為大寫加底線
		        resultSet.getBigDecimal("price"),
		        resultSet.getInt("sales"),
		        resultSet.getInt("total")
		);
	}
	@Override
	public boolean insertCoffees(Coffees coffees) {
		String sql = "INSERT INTO coffees (COF_NAME, SUP_ID, price, sales, total) VALUES (?, ?, ?, ?, ?)";
		 try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {
			 	statement.setString(1, coffees.getCofName());
		        statement.setInt(2, coffees.getSupId());
		        statement.setBigDecimal(3, coffees.getPrice());
		        statement.setInt(4, coffees.getSales());
		        statement.setInt(5, coffees.getTotal());		        		        
		        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
		        return statement.executeUpdate() > 0;
		        
		    } catch (SQLException e) {
		        throw new RuntimeException("新增咖啡資料失敗", e);
		    }
	}

	@Override
	public boolean updateCoffees(Coffees coffees) {	   
	    String sql = "UPDATE coffees SET SUP_ID = ?, price = ?, sales = ?, total = ? WHERE COF_NAME = ?";
	    
	    // 使用 try-with-resources 自動關閉連線與資源
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {	        
	        statement.setInt(1, coffees.getSupId());
	        statement.setBigDecimal(2, coffees.getPrice());
	        statement.setInt(3, coffees.getSales());
	        statement.setInt(4, coffees.getTotal());
	        statement.setString(5, coffees.getCofName());	        
	        // 在 try 內部執行並回傳（受影響列數大於 0 代表更新成功）
	        return statement.executeUpdate() > 0;	        
	    } catch (SQLException e) {
	        throw new RuntimeException("更新咖啡資料失敗", e);
	    }
	}

	@Override
	public boolean delCoffees(String cofName) {
		 String sql = "DELETE FROM coffees WHERE COF_NAME = ?";
		 try(Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)){
			 statement.setString(1, cofName);
			 return statement.executeUpdate() > 0;
		 }catch(SQLException e) {
			 throw new RuntimeException("刪除咖啡資料失敗", e);
		 }
	}

	@Override
	public List<Coffees> findAll() {
		List<Coffees> list = new ArrayList<>();
		String sql = "SELECT COF_NAME,SUP_ID,price,sales,total FROM coffees";
		try (Connection connection = DatabaseConnection.getConnection();
			 Statement statement = connection.createStatement(); //沒有參數時
			 ResultSet resultSet = statement.executeQuery(sql)){		        
		      while (resultSet.next()) {
		            list.add(mapCoff(resultSet));
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("查詢咖啡清單失敗", e);
		    }		    
		    return list;		
	}

	@Override
	public Coffees findByCofName(String cofName) {
	    String sql = "SELECT COF_NAME, SUP_ID, price, sales, total FROM coffees WHERE COF_NAME = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {	        
	        statement.setString(1, cofName);	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            return resultSet.next() ? mapCoff(resultSet) : null;
	        }
	        
	    } catch (SQLException e) {
	        throw new RuntimeException("依名稱查詢咖啡失敗: " + cofName, e);
	    }
	}

}
