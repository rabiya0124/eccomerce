package check.checks.Repositories;

import check.checks.Model.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface cartcrud extends JpaRepository<cart,Integer> {

   List<cart> findByUsername(String username);
   cart findByUsernameAndName(String username,String name);

   void deleteByUsernameAndName(String username,String name);

   @Query(value = "Select SUM(totalamount) from cart where username=:username ",nativeQuery = true)
   Double totalamount(String username);
   @Query(value = "Select SUM(quantity) from cart where username=:username",nativeQuery = true)
   Integer items(String username);

   void deleteByUsername(String username);
}
