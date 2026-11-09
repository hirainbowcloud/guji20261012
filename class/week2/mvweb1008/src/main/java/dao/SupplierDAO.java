package dao;
import java.util.*;
public interface SupplierDAO {
   int insert(Supplier s);
   int update(Supplier s);
   int delete(int id);
   List<Supplier> findAll();
   Supplier findById(int id);
}
