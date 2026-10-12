package dao;

import java.util.List;

public interface SuppliersDAO 
{
	boolean insertSuppliers(Suppliers suppliers);
	boolean updateSuppliers(Suppliers suppliers);
	boolean delSuppliers(int supId);
	List<Suppliers> findAll();
	Suppliers findBySupId(int supId);
}
