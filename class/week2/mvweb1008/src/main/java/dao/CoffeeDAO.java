package dao;
import java.util.*;
public interface CoffeeDAO {
   int insert(Coffee c);
   int update(Coffee c);
   int delete(String name);
   List<Coffee> findAll();
   Coffee findByName(String n);
}
