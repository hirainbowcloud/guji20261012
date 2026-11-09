package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CoffeeDAOImpl implements CoffeeDAO{
    String url="jdbc:mysql://localhost:3306/classicmodels"; 
    String user="root";
    String password="1234";
    List<Coffee> data=new ArrayList<>();
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			System.out.println("Driver Error :"+ex.getMessage());
		}
	}
	@Override
	public int insert(Coffee c) {
		 String sql="insert into classicmodels.coffees(COF_NAME,SUP_ID,price,sales,total) values(?,?,?,?,?) ;";	   
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);
			   st.setString(1,c.getCofName());			   
			   st.setInt(2, c.getSupId());
			   st.setBigDecimal(3, c.getPrice());
			   st.setInt(4, c.getSales());
			   st.setInt(5, c.getTotal());
			   int r=st.executeUpdate();
			   cn.close();   
			   return r;
		   }catch(SQLException ex) {
			  System.out.println("findAll Error "+ex.getMessage());   
		   }	   
		   return -1;
	}

	@Override
	public int update(Coffee c) {
		 String sql="update classicmodels.coffees set SUP_ID=? ,price=? ,sales=? ,total=? where COF_NAME=? ;";	   
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);			   			   
			   st.setInt(1, c.getSupId());
			   st.setBigDecimal(2, c.getPrice());
			   st.setInt(3, c.getSales());
			   st.setInt(4, c.getTotal());
			   st.setString(5,c.getCofName());
			   int r=st.executeUpdate();
			   cn.close();   
			   return r;
		   }catch(SQLException ex) {
			  System.out.println("update Error "+ex.getMessage());   
		   }	   
		   return -1;
	}

	@Override
	public int delete(String name) {
		String sql="delete from classicmodels.coffees where COF_NAME=? ;";	   
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);	   			   
			   st.setString(1,name);
			   int r=st.executeUpdate();
			   cn.close();   
			   return r;
		   }catch(SQLException ex) {
			  System.out.println("delete Error "+ex.getMessage());   
		   }	   
		   return -1;
	}

	@Override
	public List<Coffee> findAll() {
		// TODO Auto-generated method stub
	   data=new ArrayList<>();
	   String sql="SELECT COF_NAME,SUP_ID,price,sales,total FROM classicmodels.coffees;";	   
	   try {
		   Connection cn=DriverManager.getConnection(url,user,password);
		   Statement st=cn.createStatement();
		   ResultSet rs=st.executeQuery(sql);
		   while(rs.next()) {
			   Coffee c1=new Coffee();
			   c1.setCofName(rs.getString("COF_NAME"));
			   c1.setSupId(rs.getInt("SUP_ID"));			  
			   c1.setPrice(rs.getBigDecimal("price"));
			   c1.setSales(rs.getInt("sales"));
			   c1.setTotal(rs.getInt("total"));
			   data.add(c1);
		   }
		   cn.close();   
	   }catch(SQLException ex) {
		  System.out.println("findAll Error "+ex.getMessage());   
	   }	   
	   return data;
	}

	@Override
	public Coffee findByName(String n) {
		 String sql="SELECT COF_NAME,SUP_ID,price,sales,total FROM classicmodels.coffees where COF_NAME=? ;";	   
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);
			   st.setString(1, n);
			   ResultSet rs=st.executeQuery();
			   rs.next();
				   Coffee c1=new Coffee();
				   c1.setCofName(rs.getString("COF_NAME"));
				   c1.setSupId(rs.getInt("SUP_ID"));			  
				   c1.setPrice(rs.getBigDecimal("price"));
				   c1.setSales(rs.getInt("sales"));
				   c1.setTotal(rs.getInt("total"));			
			  
			   cn.close();
			   return c1;
		   }catch(SQLException ex) {
			  System.out.println("findByName Error "+ex.getMessage());   
		   }	 
		   return null;
	}
  
}
