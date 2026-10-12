package dao;

import java.util.List;

public interface CoffeesDAO 
{
	boolean insertCoffees(Coffees coffees);
	boolean updateCoffees(Coffees coffees);
	boolean delCoffees(String cofName);
	List<Coffees> findAll();
	Coffees findByCofName(String cofName);
}
