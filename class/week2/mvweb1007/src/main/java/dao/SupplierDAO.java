package dao;

import java.util.List;

public interface SupplierDAO {
    int insert(Supplier supplier);
    int update(Supplier supplier);
    int delete(int supId);
    Supplier findById(int supId);
    List<Supplier> findAll();
}
