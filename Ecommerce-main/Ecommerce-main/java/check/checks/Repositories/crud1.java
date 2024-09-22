package check.checks.Repositories;

import check.checks.Model.adminadd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface crud1 extends JpaRepository<adminadd,Integer> {


    adminadd findByName(String a);
  //  void deletebyName(String a);
}
