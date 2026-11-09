package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//SupplierDAO Interface 實作程式 (JDBC + MySQL)
public class SupplierDAOImpl implements SupplierDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("找不到 MySQL Driver", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private Supplier mapRow(ResultSet rs) throws SQLException {
        Supplier s = new Supplier();
        s.setSupId(rs.getInt("SUP_ID"));
        s.setSupName(rs.getString("SUP_NAME"));
        s.setStreet(rs.getString("STREET"));
        s.setCity(rs.getString("CITY"));
        s.setState(rs.getString("STATE"));
        s.setZip(rs.getString("ZIP"));
        return s;
    }

    @Override
    public int insert(Supplier supplier) {
        String sql = "INSERT INTO classicmodels.SUPPLIERS (SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplier.getSupId());
            ps.setString(2, supplier.getSupName());
            ps.setString(3, supplier.getStreet());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getState());
            ps.setString(6, supplier.getZip());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insert Supplier 失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public int update(Supplier supplier) {
        String sql = "UPDATE classicmodels.SUPPLIERS SET SUP_NAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ? WHERE SUP_ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, supplier.getSupName());
            ps.setString(2, supplier.getStreet());
            ps.setString(3, supplier.getCity());
            ps.setString(4, supplier.getState());
            ps.setString(5, supplier.getZip());
            ps.setInt(6, supplier.getSupId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("update Supplier 失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(int supId) {
        String sql = "DELETE FROM classicmodels.SUPPLIERS WHERE SUP_ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("delete Supplier 失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public Supplier findById(int supId) {
        String sql = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM classicmodels.SUPPLIERS WHERE SUP_ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("findById Supplier 失敗: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Supplier> findAll() {
        String sql = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM classicmodels.SUPPLIERS ORDER BY SUP_ID";
        List<Supplier> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("findAll Supplier 失敗: " + e.getMessage(), e);
        }
    }
}
