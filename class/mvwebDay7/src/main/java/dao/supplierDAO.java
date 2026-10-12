package dao;

import java.sql.SQLException;
import java.util.List;

public interface supplierDAO {

	boolean insertSupplier(suppliers supplier) throws SQLException;

	boolean deleteSupplier(int supId) throws SQLException;

	boolean updateSupplier(suppliers supplier) throws SQLException;

	suppliers getSupplierById(int supId) throws SQLException;

	List<suppliers> getAllSuppliers() throws SQLException;
}
