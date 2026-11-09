package demo.usercart.model;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem,Integer> {
     public List<OrderItem> findByOrderId(long id);
}
