package dao;

import java.util.List;

public interface OfficesDAO
{
	boolean insertOffices(Offices offices);
	boolean updateOffices(Offices offices);
	boolean delOffices(String officeCode);
	List<Offices> findAll();
	Offices findByOfficeCode(String officeCode);
}
