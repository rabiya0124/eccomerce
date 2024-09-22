package check.checks.Repositories;

import check.checks.Model.Orders;
import check.checks.Model.timedate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface crud2 extends JpaRepository<Orders,Integer> {

   @Query(value = "SELECT SUM(orderamount) FROM orders", nativeQuery = true)
  Double findtotalrevenue();

//
   @Query(value = "SELECT Count(orderid) FROM orders WHERE orderdatetime > now() - interval 30 day", nativeQuery = true)
    Integer findtotalordermonthly();
//
  @Query (value = "SELECT Count(orderdatetime) FROM orders WHERE orderdatetime > now() - interval 24 hour", nativeQuery = true)
   Integer findtotalordersdaily();
//
@Query (value = "SELECT SUM(orderamount) FROM orders WHERE orderdatetime > now() - interval 30 day",nativeQuery = true)


    Double findmonthlyrevenue();

@Query ( value = "SELECT * FROM orders WHERE orderdatetime > now() - interval 30 day", nativeQuery = true )
List<Orders> recentorders();

@Query (value = "SELECT * fROM orders",nativeQuery = true)
List<Orders> allorders();

List<Orders> findByOrderid(String orderid);
List<Orders> findByUsername(String username);

 //@Query("From Orders Where orderdatetime.")
 //List Findtotalrevenue(LocalDateTime a);




}



